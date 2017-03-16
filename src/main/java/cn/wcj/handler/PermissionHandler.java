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

import cn.wcj.entity.Permission;
import cn.wcj.service.IPermissionService;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("unchecked")
@RequestMapping("/Permission")
@Controller
public class PermissionHandler implements IBaseHandler<Permission>{

	private static final long serialVersionUID = 1L;
	
	private static final String LIST_PAGE="admin/permission_list"  ;

	@Autowired
	private IPermissionService permissionService   ;
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		  return LIST_PAGE   ;
	}
	
	@Override
	@ResponseBody
    @RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	public String findAll(@RequestParam(value="cp",defaultValue="1")Integer currentPage,
			              @RequestParam(value="ls",defaultValue="10")Integer lineSize,
			              @RequestParam(value="kw",defaultValue="")String keyWord)throws Exception{
		   String json=null ;
		   Map<String, Object> map = this.permissionService.findAll(currentPage, lineSize,keyWord)  ;
		   System.out.println(map);
		   List<Permission> permissions=(List<Permission>)map.get("data");
		   PageInfo<Permission> pageInfo=(PageInfo<Permission>) map.get("pageInfo")   ;
		   JSONObject all=new JSONObject()   ;
           all.put("allRecords", pageInfo.getTotal())   ;
           all.put("kw", keyWord)   ;   //回传关键词
           all.put("currentSize", pageInfo.getSize())  ;  //当前页数据量
           JSONArray array=new JSONArray()  ;  //存储全部权限
           for(Permission permission : permissions){
        	     JSONObject obj=new JSONObject()  ;
        	     obj.put("permissionId", permission.getPermissionId()) ;
        	     obj.put("name", permission.getName()) ;
        	     obj.put("note", permission.getNote()) ;
        	     array.add(obj)  ;
           }
           all.put("allPermissions", array) ;
           json=all.toString();
		   return json   ;
	}
	
	@ResponseBody
	@RequestMapping("/findById/{permissionId}")
	public String findById(@PathVariable("permissionId")Integer permissionId) throws Exception{
		  Permission permission = this.permissionService.findByID(permissionId)  ;
		  return JSONObject.fromObject(permission).toString()   ;   //返回JSON数据类型 
	}
	
	
	
	
	
}
