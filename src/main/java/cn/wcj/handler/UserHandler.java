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
	private IUserService userService   ;  //�û������ 

	@RequestMapping("/doLogin")
	public String doLogin(User user,HttpServletRequest request)throws Exception{
		   String page=INDEX_PAGE   ;
		   String name = user.getName();  //�û���
		   String password = user.getPassword(); //����
		   Subject subject = SecurityUtils.getSubject() ;
		   UsernamePasswordToken token = new UsernamePasswordToken(name, password); //����
		   HttpSession session = request.getSession();
		   try{  //�����쳣,ȫ��ͳһ����ص�¼ҳ
			       subject.login(token);      //��¼
			       String ip=request.getRemoteAddr() ;   //��ȡIP
			       Date date=new Date();    //��¼ʱ��
			       User currentUser = this.userService.findByName(name) ;
			       currentUser.setLastIP(ip)  ;   //����IP
			       currentUser.setLastLogin(date);
			       currentUser.setLoginErr(0) ;   //���õ�¼�������������
			       this.userService.doUpdate(currentUser) ;   //�����û���¼��Ϣ
			       session.setAttribute("user", currentUser);
			       if(session.getAttribute("errMsg")!=null) //�ж�֮ǰ�ĵ�¼�����Ƿ���ڴ�����Ϣ,������ھ��Ƴ�
			    	             session.removeAttribute("errMsg")   ; //�Ƴ�֮ǰ������Ϣ  
		   }catch(UnknownAccountException e1){
			       session.setAttribute("errMsg", "�û���δ֪,�����µ�½!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(LockedAccountException e2){
			       session.setAttribute("errMsg", "�Բ���,���û�������!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(IncorrectCredentialsException e3){
			       User currentUser = this.userService.findByName(name) ;
			       Integer errCount=currentUser.getLoginErr()  ; //��ȡ���������
			       if(errCount>3){   //�������>=4
			    	   currentUser.setStatus(0) ;   //�����û�  1:����  0:����
			    	   session.setAttribute("errMsg", "���������,�û�������") ;
			       }else{
			    	   currentUser.setLoginErr(++errCount) ;   //���Ӵ�����
			    	   session.setAttribute("errMsg", "�������,�����µ�½!") ;
			       }
			       this.userService.doUpdate(currentUser)  ;    //�����û������״̬
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(Exception e4){
			       session.setAttribute("errMsg", "ϵͳ�����쳣,�����µ�½!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }
		   return page  ;
	}
    
	//�����ģ���Ѿ����ó���,����ֻ��Ҫ�޸�֮ǰ��ģ�����Ϳ�����
    //����JSON��ʽ������,����ǰ̨��֯���ݵ�ʱ����ʹ��JS-Array����,��Ϊ����ʵ�ڱȽ϶�
	@Override
	@ResponseBody
	@RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	public String findAll(@RequestParam("cp")Integer currentPage, 
			              @RequestParam("ls")Integer lineSize, 
			              @RequestParam("kw")String keyWord) throws Exception {
		Map<String, Object> map = this.userService.findAll(currentPage, lineSize, keyWord) ;  //��ҳ��Ϣ������
		PageInfo<User> pageInfo = (PageInfo<User>) map.get("pageInfo") ;  //��ҳ��Ϣ
		List<User> users=(List<User>) map.get("data")  ;   //�û���Ϣ
		JSONObject all=new JSONObject() ;   //�������յ�JSON�ַ���
		all.put("allRecords", pageInfo.getTotal())   ;
        all.put("kw", keyWord)   ;   //�ش��ؼ���
        all.put("currentSize", pageInfo.getSize())  ;  //��ǰҳ������
        //��Ϊҵ���漰��ʱ��洢��JSON,�����ֹ�����ÿһ��JSON,��������ǰ̨AJAXҪ�������
        JSONArray array=new JSONArray() ;
        for(User user : users){
        	 JSONObject obj=new JSONObject() ;    //����ÿһ��JSON����
     	     obj.put("userId", user.getUserId()) ;
     	     obj.put("name", user.getName()) ;
     	     obj.put("password", user.getPassword()) ;
     	     obj.put("lastLogin",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLastLogin())) ;//��ʽ����¼ʱ��
     	     obj.put("status", user.getStatus())  ;  //״̬
     	     obj.put("loginErr", user.getLoginErr()) ;
     	     obj.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateTime()))  ;
     	     obj.put("lastIP", user.getLastIP()==null?"":user.getLastIP())  ;
     	     obj.put("roleSize", user.getRoles().size()) ;
     	     array.add(obj)  ; //���JSON���� 
        }
        all.put("allUsers", array) ;  //����JSON��
		return all.toString()  ;   //����JSON��
	}

	private static final String LIST_PAGE="admin/user_list"  ;
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		   return LIST_PAGE  ;   //��ת���б�ҳ 
	}
	
	private static final String DETAIL_PAGE="admin/user_detail" ;   //����ҳ��,��IFrame��ʾ
	
	@RequestMapping("/toDetailPage/{userId}")
	public ModelAndView toDetailPage(@PathVariable("userId")Integer userId)throws Exception{
		   return new ModelAndView(DETAIL_PAGE).addObject("user", this.userService.findByID(userId))  ;   //�û���Ϣ
	}
	
	private static final String INSERT_PAGE="admin/user_insert" ;   //����ҳ��
	
	@Autowired
	private IRoleService roleService  ;
	
	@RequestMapping("/toInsertPage")
	public ModelAndView toInsertPage()throws Exception{
		   ModelAndView insertView=new ModelAndView(INSERT_PAGE);
		   //���ȫ����ɫ���һش� 
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
		   List<Role> roleList = CommonUtil.genRoleList(roles);   //��ɫ�б�
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
		List<Role> roleList = CommonUtil.genRoleList(roles);   //��ɫ�б�
		User user=this.userService.findByID(userId)  ;
		//ֻ��Ҫ�޸�״̬��ͽ�ɫ�б���
		user.setStatus(status);
		user.setRoles(roleList);
		return this.userService.doUpdate(user)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
	}
	
	
	private static final String EDIT_PAGE="admin/user_edit"  ;   //�༭ҳ�� 
	
	@RequestMapping("/toEditPage/{userId}")
	public ModelAndView toEditPage(@PathVariable("userId")Integer userId)throws Exception{
		   ModelAndView editView=new ModelAndView(EDIT_PAGE)  ;
		   editView.addObject("user", this.userService.findByID(userId))      //��ѯ�û���Ϣ
		           .addObject("allRoles", this.roleService.findAll(1, 0, "").get("data")) ;    //ȫ����ɫ��Ϣ
		   return editView  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findById",produces = "text/html;charset=UTF-8")
	public String findById(@RequestParam("userId")Integer userId)throws Exception{
		   User user = this.userService.findByID(userId);
      	   JSONObject obj=null ;    //����ÿһ��JSON����
      	   if(null!=user){
	      	   obj=new JSONObject()  ;   
	 	       obj.put("userId", user.getUserId()) ;
	 	       obj.put("name", user.getName()) ;
	 	       obj.put("password", user.getPassword()) ;
	 	       obj.put("lastLogin",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLastLogin())) ;//��ʽ����¼ʱ��
	 	       obj.put("status", user.getStatus())  ;  //״̬
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
	
	//�������е�ģ����Ե�����ͨ�û��Ĳ���
	
	private static final String EDIT_PASS_PAGE="user/edit_pass"   ;   //�༭����ҳ
	
	@RequestMapping("/toEditPassPage/{userId}")
	public ModelAndView toEditPassPage(@PathVariable("userId")Integer userId)throws Exception{
		   return new ModelAndView(EDIT_PASS_PAGE).addObject("user", this.userService.findByID(userId))   ;  //����ID����
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
		   return new ModelAndView(USER_DETAIL_PAGE).addObject("user", this.userService.findByID(userId))  ;  //�û���Ϣ
	}
	
	
}
