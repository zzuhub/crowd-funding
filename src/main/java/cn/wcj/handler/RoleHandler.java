package cn.wcj.handler;

import java.util.List;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.wcj.entity.Role;
import cn.wcj.service.IPermissionService;
import cn.wcj.service.IRoleService;
import cn.wcj.util.CommonUtil;
import cn.wcj.util.Contants;

import com.github.pagehelper.PageInfo;
/**
 * 终于形成V1模板,开发接下来变得异常简单
 * @author WangCJ
 *
 */
@SuppressWarnings("unchecked")
@RequestMapping("/Role")
@Controller
public class RoleHandler implements IBaseHandler<Role> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IRoleService roleService   ;
	
	@Autowired
	private IPermissionService permissionService   ;
	
	@ResponseBody
    @RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	@Override
	public String findAll(@RequestParam(value="cp",defaultValue="1")Integer currentPage,
                          @RequestParam(value="ls",defaultValue="10")Integer lineSize,
                          @RequestParam(value="kw",defaultValue="")String keyWord)
			                                                                throws Exception {
		String json=null ;
		Map<String, Object> map = this.roleService.findAll(currentPage, lineSize, keyWord) ; //得到分页信息和数据
		PageInfo<Role> pageInfo = (PageInfo<Role>) map.get("pageInfo") ;  //分页信息
		List<Role> roles = (List<Role>) map.get("data") ;   //角色列表(含权限)
		JSONObject all=new JSONObject() ;   //保存最终的JSON字符串
		all.put("allRecords", pageInfo.getTotal())   ;
        all.put("kw", keyWord)   ;   //回传关键词
        all.put("currentSize", pageInfo.getSize())  ;  //当前页数据量
        JSONArray array=new JSONArray()  ;  //存储全部角色
        for(Role role : roles){
      	     JSONObject obj=new JSONObject()  ;
      	     obj.put("roleId", role.getRoleId()) ;
      	     obj.put("name", role.getName()) ;
      	     obj.put("note", role.getNote()) ;
      	     obj.put("permissionSize", role.getPermissions().size()) ;
      	     array.add(obj)  ;
         }
        all.put("allRoles", array) ;
        json=all.toString();
		return json  ;
	}
	
	private static final String LIST_PAGE="admin/role_list"  ;   //列表页
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		    return LIST_PAGE ;
	}
	
	private static final String EDIT_PAGE="admin/role_edit"  ;  //编辑页
	
	@RequestMapping("/toEditPage/{roleId}")
	public ModelAndView toEditPage(@PathVariable("roleId") Integer roleId)throws Exception{
		ModelAndView editView=new ModelAndView(EDIT_PAGE)   ;
		Role role=this.roleService.findByID(roleId) ;  //查找角色,含权限
		editView.addObject("role", role)   //支持链式编程 
		         .addObject("allPermissions", 
		        		     this.permissionService.findAll(1, 0, "").get("data") )  ;  //放置全部权限
		return editView ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doRemove",produces = "text/html;charset=UTF-8")
	public String doRemove(@RequestParam("roleId") Integer roleId)throws Exception{
		   Integer count = this.roleService.doRemove(roleId) ;
		   String jsonStatus=count>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;  //设置状态码
		   return jsonStatus  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doUpdate",produces = "text/html;charset=UTF-8")
	public String doUpdate(@RequestParam("perms[]")Integer[] perms ,
			               @RequestParam("roleId") Integer roleId ,
			               @RequestParam("name")String name ,
			               @RequestParam("note")String note)throws Exception{
		   //配置数据
		   Role role=new Role()  ;
		   role.setRoleId(roleId);
		   role.setName(name);
		   role.setNote(note);
		   role.setPermissions(CommonUtil.genPermissionList(perms));
		   String jsonStatus=this.roleService.doUpdate(role)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON ;
		   return jsonStatus ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findById",produces = "text/html;charset=UTF-8")
	public String findById(@RequestParam("roleId")Integer roleId)throws Exception{
		   Role role = this.roleService.findByID(roleId)   ;  //根据ID查询
		   return JSONObject.fromObject(role).toString()   ;   //将Java对象转换成字符串 
	}
	
	private static final String DETAIL_PAGE="admin/role_detail" ;   //详情页面,在IFrame显示
	
	@RequestMapping("/toDetailPage/{roleId}")
	public ModelAndView toDetailPage(@PathVariable("roleId")Integer roleId)throws Exception{
		   ModelAndView detailView=new ModelAndView(DETAIL_PAGE)  ;   //创建视图
		   Role role = this.roleService.findByID(roleId) ;    //查找角色
		   detailView.addObject("role",role) ;  //保存查询的角色(含权限)
		   return detailView  ;
	}
	
	private static final String INSERT_PAGE="admin/role_insert"   ;  //增加页面
	@RequestMapping(value="/toInsertPage",produces = "text/html;charset=UTF-8")
	public ModelAndView toInsertPage()throws Exception{
		   ModelAndView insertView=new ModelAndView(INSERT_PAGE) ;   //增加页面
		   insertView.addObject("allPermissions", this.permissionService.findAll(1, 0, "").get("data"))  ;
		   return insertView  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findByName",produces = "text/html;charset=UTF-8")
	public String findByName(@RequestParam("name")String name)throws Exception{
		    return this.roleService.findByName(name)==null?Contants.FAILURE_STATUS_JSON:Contants.SUCCESS_STATUS_JSON ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doCreate",produces = "text/html;charset=UTF-8")
	public String doCreate(@RequestParam("perms[]")Integer[] perms ,
				            @RequestParam("name")String name ,
				            @RequestParam("note")String note)throws Exception{
		   //配置数据,既然使用AJAX简化操作SpringMVC就要牺牲一些代价来换取AJAX的华丽
		   Role role=new Role()  ;
		   role.setName(name);
		   role.setNote(note);
		   role.setPermissions(CommonUtil.genPermissionList(perms));
		   return this.roleService.doCreate(role)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON ;
	}
	

}
