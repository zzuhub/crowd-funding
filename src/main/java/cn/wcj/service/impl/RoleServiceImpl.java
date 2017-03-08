package cn.wcj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.IRoleDAO;
import cn.wcj.entity.Permission;
import cn.wcj.entity.Role;
import cn.wcj.service.IRoleService;
/**
 * 
 * 这是真正的角色服务层,所有的代码改写和调用全部在这里完成
 * 
 *@author WangCJ
 *
 *
 *@since 2017-03-08 08:56:52
 *
 *
 *注意:由于主键没用采用Java生成UUID,造成批量级联增加异常困难,
 *     解决办法是改写Entity,但是成本太大,再说没有SB会级联增加角色的业务
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> 
                             implements IRoleService {
    
	//注入DAO只为改写方法时使用
	@Autowired
	private IRoleDAO roleDAO  ;
	
	//重新配置DAO
	@Resource(name="roleDAO")
	@Override
	public void setDao(IBaseDAO<Role, Integer> dao) {
		super.setDao(dao);
	}

	//增加Role的方法需要改写
	@Override
	public Integer doCreate(Role entity) throws Exception {
		//1.获取下一个增长ID的值(IRoleDAO.findNextID())
		 Integer roleId = this.roleDAO.findNextID() ;
		//2.向t_role表增加数据(IRoleDAO.doCreate())
		Integer count=this.roleDAO.doCreate(entity)  ;   //增加后计数
		//3.向数据字典t_role_permission增加数据
		 Map<String,Object> map=new HashMap<>() ;       //保存角色ID和权限ID
		 map.put("roleId", roleId)  ;  //保存角色ID
		 List<Integer> list=new ArrayList<>()  ;  //保存全部权限ID
		 for(Permission permission : entity.getPermissions())
			                  list.add(permission.getPermissionId()) ;
		 map.put("list", list) ;  //放置全部权限ID
		 count=count+this.roleDAO.doCreateAfter(map) ;
		 return count   ;
	}

	//删除要同时删除两张表的数据
	@Override
	public Integer doRemove(Integer id) throws Exception {
	       Integer count= 0 ;
           //1.先删除t_role_permission表对应的数据
	       count+=this.roleDAO.doRemovePre(id)  ;  
	       //2.再删除t_role表对应的数据
	       count+=this.roleDAO.doRemove(id)  ;
	       return count   ;
	}

	@Override
	public Integer doUpdate(Role entity) throws Exception {
		Integer count=0 ;
		//1.删除t_role_permission对应的数据:doRemovePre()
		count+=this.roleDAO.doRemovePre(entity.getRoleId())    ;
		//2.修改t_role 
		count+=this.roleDAO.doUpdate(entity)   ;
		//3.向t_role_permission增加对应的数据
		Map<String,Object> map=new HashMap<>() ;       //保存角色ID和权限ID
		map.put("roleId", entity.getRoleId())  ;  //保存角色ID
		List<Integer> list=new ArrayList<>()  ;  //保存全部权限ID
		for(Permission permission : entity.getPermissions())
			                   list.add(permission.getPermissionId()) ;
		map.put("list", list) ;  //放置全部权限ID
		count+=this.roleDAO.doCreateAfter(map)  ;
		return count;
	}

	
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	

}
