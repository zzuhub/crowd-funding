package cn.wcj.handler;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.wcj.entity.Permission;
import cn.wcj.entity.Role;
import cn.wcj.entity.User;
import cn.wcj.service.IRoleService;
import cn.wcj.service.IUserService;
import cn.wcj.util.CommonUtil;
import cn.wcj.util.Contants;
import cn.wcj.util.DataUtil;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("unchecked")
@RequestMapping("/User")
@Controller
public class UserHandler implements IBaseHandler<Permission> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUserService userService   ;  //用户服务层 

	@RequestMapping("/doLogin")
	public String doLogin(User user,HttpServletRequest request)throws Exception{
		   String page=INDEX_PAGE   ;
		   String name = user.getName();  //用户名
		   String password = user.getPassword(); //密码
		   Subject subject = SecurityUtils.getSubject() ;
		   UsernamePasswordToken token = new UsernamePasswordToken(name, password); //令牌
		   HttpSession session = request.getSession();
		   try{  //出现异常,全部统一定向回登录页
			       subject.login(token);      //登录
			       String ip=request.getRemoteAddr() ;   //获取IP
			       Date date=new Date();    //登录时间
			       User currentUser = this.userService.findByName(name) ;
			       currentUser.setLastIP(ip)  ;   //设置IP
			       currentUser.setLastLogin(date);
			       currentUser.setLoginErr(0) ;   //设置登录密码错误数归零
			       this.userService.doUpdate(currentUser) ;   //更新用户登录信息
			       session.setAttribute("user", currentUser);
			       if(session.getAttribute("errMsg")!=null) //判断之前的登录操作是否存在错误信息,如果存在就移除
			    	             session.removeAttribute("errMsg")   ; //移除之前错误信息  
		   }catch(UnknownAccountException e1){
			       session.setAttribute("errMsg", "用户名未知,请重新登陆!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(LockedAccountException e2){
			       session.setAttribute("errMsg", "对不起,该用户已锁定!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(IncorrectCredentialsException e3){
			       User currentUser = this.userService.findByName(name) ;
			       Integer errCount=currentUser.getLoginErr()  ; //获取密码错误数
			       if(errCount>3){   //密码错误>=4
			    	   currentUser.setStatus(0) ;   //锁定用户  1:可用  0:锁定
			    	   session.setAttribute("errMsg", "密码多次输错,用户已锁定") ;
			       }else{
			    	   currentUser.setLoginErr(++errCount) ;   //增加错误数
			    	   session.setAttribute("errMsg", "密码错误,请重新登陆!") ;
			       }
			       this.userService.doUpdate(currentUser)  ;    //更新用户出错和状态
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(Exception e4){
			       session.setAttribute("errMsg", "系统出现异常,请重新登陆!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }
		   return page  ;
	}
    
	//昨天的模板已经配置出来,后来只需要修改之前的模板代码就可以了
    //返回JSON格式的数据,但是前台组织数据的时候尽量使用JS-Array传参,因为参数实在比较多
	@Override
	@ResponseBody
	@RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	public String findAll(@RequestParam("cp")Integer currentPage, 
			              @RequestParam("ls")Integer lineSize, 
			              @RequestParam("kw")String keyWord) throws Exception {
		Map<String, Object> map = this.userService.findAll(currentPage, lineSize, keyWord) ;  //分页信息、数据
		PageInfo<User> pageInfo = (PageInfo<User>) map.get("pageInfo") ;  //分页信息
		List<User> users=(List<User>) map.get("data")  ;   //用户信息
		JSONObject all=new JSONObject() ;   //保存最终的JSON字符串
		all.put("allRecords", pageInfo.getTotal())   ;
        all.put("kw", keyWord)   ;   //回传关键词
        all.put("currentSize", pageInfo.getSize())  ;  //当前页数据量
        //因为业务涉及到时间存储到JSON,必须手工配置每一个JSON,返回满足前台AJAX要求的数据
        JSONArray array=new JSONArray() ;
        for(User user : users){
        	 JSONObject obj=new JSONObject() ;    //保存每一个JSON对象
     	     obj.put("userId", user.getUserId()) ;
     	     obj.put("name", user.getName()) ;
     	     obj.put("password", user.getPassword()) ;
     	     obj.put("lastLogin",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLastLogin())) ;//格式化登录时间
     	     obj.put("status", user.getStatus())  ;  //状态
     	     obj.put("loginErr", user.getLoginErr()) ;
     	     obj.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateTime()))  ;
     	     obj.put("lastIP", user.getLastIP()==null?"":user.getLastIP())  ;
     	     obj.put("roleSize", user.getRoles().size()) ;
     	     array.add(obj)  ; //添加JSON对象 
        }
        all.put("allUsers", array) ;  //放入JSON串
		return all.toString()  ;   //返回JSON串
	}

	private static final String LIST_PAGE="admin/user_list"  ;
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		   return LIST_PAGE  ;   //跳转到列表页 
	}
	
	private static final String DETAIL_PAGE="admin/user_detail" ;   //详情页面,在IFrame显示
	
	@RequestMapping("/toDetailPage/{userId}")
	public ModelAndView toDetailPage(@PathVariable("userId")Integer userId)throws Exception{
		   return new ModelAndView(DETAIL_PAGE).addObject("user", this.userService.findByID(userId))  ;   //用户信息
	}
	
	private static final String INSERT_PAGE="admin/user_insert" ;   //增加页面
	
	@Autowired
	private IRoleService roleService  ;
	
	@RequestMapping("/toInsertPage")
	public ModelAndView toInsertPage()throws Exception{
		   ModelAndView insertView=new ModelAndView(INSERT_PAGE);
		   //查出全部角色并且回传 
		   insertView.addObject("allRoles", roleService.findAll(1, 0, "").get("data"));
		   return insertView ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findByName",produces = "text/html;charset=UTF-8")
	public String findByName(@RequestParam("name")String name)throws Exception{
		   return this.userService.findByName(name)==null?Contants.FAILURE_STATUS_JSON:Contants.SUCCESS_STATUS_JSON ;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/doCreate",produces = "text/html;charset=UTF-8")
	public String doCreate(@RequestParam("name")String name,
			               @RequestParam("password")String password,
			               @RequestParam("roles[]")Integer []roles)throws Exception{
		   List<Role> roleList = CommonUtil.genRoleList(roles);   //角色列表
		   User user=new User()  ;
		   user.setName(name);
		   user.setPassword(DataUtil.encrypt(Contants.ALGORITHM_MD5, password, name, Contants.COMMON_HASH_ITERATIONS));
		   user.setRoles(roleList);
		   return this.userService.doCreate(user)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doUpdate",produces = "text/html;charset=UTF-8")
	public String doUpdate(@RequestParam("userId")Integer userId,
			               @RequestParam("status")Integer status,
			               @RequestParam("roles[]")Integer[] roles)throws Exception{
		List<Role> roleList = CommonUtil.genRoleList(roles);   //角色列表
		User user=this.userService.findByID(userId)  ;
		//只需要修改状态码和角色列表即可
		user.setStatus(status);
		user.setRoles(roleList);
		return this.userService.doUpdate(user)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
	}
	
	
	private static final String EDIT_PAGE="admin/user_edit"  ;   //编辑页面 
	
	@RequestMapping("/toEditPage/{userId}")
	public ModelAndView toEditPage(@PathVariable("userId")Integer userId)throws Exception{
		   ModelAndView editView=new ModelAndView(EDIT_PAGE)  ;
		   editView.addObject("user", this.userService.findByID(userId))      //查询用户信息
		           .addObject("allRoles", this.roleService.findAll(1, 0, "").get("data")) ;    //全部角色信息
		   return editView  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findById",produces = "text/html;charset=UTF-8")
	public String findById(@RequestParam("userId")Integer userId)throws Exception{
		   User user = this.userService.findByID(userId);
      	   JSONObject obj=null ;    //保存每一个JSON对象
      	   if(null!=user){
	      	   obj=new JSONObject()  ;   
	 	       obj.put("userId", user.getUserId()) ;
	 	       obj.put("name", user.getName()) ;
	 	       obj.put("password", user.getPassword()) ;
	 	       obj.put("lastLogin",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLastLogin())) ;//格式化登录时间
	 	       obj.put("status", user.getStatus())  ;  //状态
	 	       obj.put("loginErr", user.getLoginErr()) ;
	 	       obj.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateTime()))  ;
	 	       obj.put("lastIP", user.getLastIP()==null?"":user.getLastIP())  ;
	 	       obj.put("roleSize", user.getRoles().size()) ;
      	   }
 	       return obj==null?null:obj.toString()   ;
	}
	
	@ResponseBody
	@RequestMapping("/doRemove")
	public String doRemove(@RequestParam("userId")Integer userId)throws Exception{
		  return this.userService.doRemove(userId)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON ;
	}
	
	//下面所有的模块针对的是普通用户的操作
	
	private static final String EDIT_PASS_PAGE="user/edit_pass"   ;   //编辑密码页
	
	@RequestMapping("/toEditPassPage/{userId}")
	public ModelAndView toEditPassPage(@PathVariable("userId")Integer userId)throws Exception{
		   return new ModelAndView(EDIT_PASS_PAGE).addObject("user", this.userService.findByID(userId))   ;  //根据ID查找
	}
	
	@ResponseBody
	@RequestMapping(value="/findByIdAndPass",produces = "text/html;charset=UTF-8")
	public String findByIdAndPass(User user)throws Exception{
		  return this.userService.findByIdAndPass(user)!=null?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON ;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/doUpdatePassword",produces = "text/html;charset=UTF-8")
	public String doUpdatePassword(User user)throws Exception{
		   return this.userService.doUpdatePassword(user)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON   ;
	}
	
	private static final String USER_DETAIL_PAGE="/user/user_detail";
	
	@RequestMapping("/toUserDetailPage/{userId}")
	public ModelAndView toUserDetailPage(@PathVariable("userId")Integer userId)throws Exception{
		   return new ModelAndView(USER_DETAIL_PAGE).addObject("user", this.userService.findByID(userId))  ;  //用户信息
	}
	
	
}
