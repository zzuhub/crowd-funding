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
 * �����γ�V1ģ��,��������������쳣��
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
		Map<String, Object> map = this.roleService.findAll(currentPage, lineSize, keyWord) ; //�õ���ҳ��Ϣ������
		PageInfo<Role> pageInfo = (PageInfo<Role>) map.get("pageInfo") ;  //��ҳ��Ϣ
		List<Role> roles = (List<Role>) map.get("data") ;   //��ɫ�б�(��Ȩ��)
		JSONObject all=new JSONObject() ;   //�������յ�JSON�ַ���
		all.put("allRecords", pageInfo.getTotal())   ;
        all.put("kw", keyWord)   ;   //�ش��ؼ���
        all.put("currentSize", pageInfo.getSize())  ;  //��ǰҳ������
        JSONArray array=new JSONArray()  ;  //�洢ȫ����ɫ
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
	
	private static final String LIST_PAGE="admin/role_list"  ;   //�б�ҳ
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		    return LIST_PAGE ;
	}
	
	private static final String EDIT_PAGE="admin/role_edit"  ;  //�༭ҳ
	
	@RequestMapping("/toEditPage/{roleId}")
	public ModelAndView toEditPage(@PathVariable("roleId") Integer roleId)throws Exception{
		ModelAndView editView=new ModelAndView(EDIT_PAGE)   ;
		Role role=this.roleService.findByID(roleId) ;  //���ҽ�ɫ,��Ȩ��
		editView.addObject("role", role)   //֧����ʽ��� 
		         .addObject("allPermissions", 
		        		     this.permissionService.findAll(1, 0, "").get("data") )  ;  //����ȫ��Ȩ��
		return editView ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doRemove",produces = "text/html;charset=UTF-8")
	public String doRemove(@RequestParam("roleId") Integer roleId)throws Exception{
		   Integer count = this.roleService.doRemove(roleId) ;
		   String jsonStatus=count>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;  //����״̬��
		   return jsonStatus  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doUpdate",produces = "text/html;charset=UTF-8")
	public String doUpdate(@RequestParam("perms[]")Integer[] perms ,
			               @RequestParam("roleId") Integer roleId ,
			               @RequestParam("name")String name ,
			               @RequestParam("note")String note)throws Exception{
		   //��������
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
		   Role role = this.roleService.findByID(roleId)   ;  //����ID��ѯ
		   return JSONObject.fromObject(role).toString()   ;   //��Java����ת�����ַ��� 
	}
	
	private static final String DETAIL_PAGE="admin/role_detail" ;   //����ҳ��,��IFrame��ʾ
	
	@RequestMapping("/toDetailPage/{roleId}")
	public ModelAndView toDetailPage(@PathVariable("roleId")Integer roleId)throws Exception{
		   ModelAndView detailView=new ModelAndView(DETAIL_PAGE)  ;   //������ͼ
		   Role role = this.roleService.findByID(roleId) ;    //���ҽ�ɫ
		   detailView.addObject("role",role) ;  //�����ѯ�Ľ�ɫ(��Ȩ��)
		   return detailView  ;
	}
	
	private static final String INSERT_PAGE="admin/role_insert"   ;  //����ҳ��
	@RequestMapping(value="/toInsertPage",produces = "text/html;charset=UTF-8")
	public ModelAndView toInsertPage()throws Exception{
		   ModelAndView insertView=new ModelAndView(INSERT_PAGE) ;   //����ҳ��
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
		   //��������,��Ȼʹ��AJAX�򻯲���SpringMVC��Ҫ����һЩ��������ȡAJAX�Ļ���
		   Role role=new Role()  ;
		   role.setName(name);
		   role.setNote(note);
		   role.setPermissions(CommonUtil.genPermissionList(perms));
		   return this.roleService.doCreate(role)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON ;
	}
	

}
