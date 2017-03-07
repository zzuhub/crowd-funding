package cn.wcj.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.IPermissionDAO;
import cn.wcj.entity.Permission;
import cn.wcj.service.IPermissionService;
/**
 * 这个是真正的Service层,Controller调用就调用这个类即可,千万别玩多态,
 * SB多态只会强制向下转型才能用到这个类的特殊方法
 * @author WangCJ
 *
 *@since 2017-03-07 10:51:16
 *
 */
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer>
		                           implements IPermissionService                {

	@Autowired
	private IPermissionDAO permissionDAO  ;
	
	//只有这种形式才能将DAO传到BaseServiceImpl中去
	@Resource(name="permissionDAO")
	@Override
	public void setDao(IBaseDAO<Permission, Integer> dao) {
		super.setDao(dao);
	}

	@Override
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;
		count+=this.permissionDAO.doRemovePre(id) ;   //删除数据字典
		count+=super.getDao().doRemove(id)  ;        //删除权限表对应的数据
		return count;
	}
	
	

     	 
	 
	 
	 
	
	
	
	

	  
	
	 
	
	
}
