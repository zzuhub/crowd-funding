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
 * ���������Ľ�ɫ�����,���еĴ����д�͵���ȫ�����������
 * 
 *@author WangCJ
 *
 *
 *@since 2017-03-08 08:56:52
 *
 *
 *ע��:��������û�ò���Java����UUID,����������������쳣����,
 *     ����취�Ǹ�дEntity,���ǳɱ�̫��,��˵û��SB�ἶ�����ӽ�ɫ��ҵ��
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> 
                             implements IRoleService {
    
	//ע��DAOֻΪ��д����ʱʹ��
	@Autowired
	private IRoleDAO roleDAO  ;
	
	//��������DAO
	@Resource(name="roleDAO")
	@Override
	public void setDao(IBaseDAO<Role, Integer> dao) {
		super.setDao(dao);
	}

	//����Role�ķ�����Ҫ��д
	@Override
	public Integer doCreate(Role entity) throws Exception {
		//1.��ȡ��һ������ID��ֵ(IRoleDAO.findNextID())
		 Integer roleId = this.roleDAO.findNextID() ;
		//2.��t_role����������(IRoleDAO.doCreate())
		Integer count=this.roleDAO.doCreate(entity)  ;   //���Ӻ����
		//3.�������ֵ�t_role_permission��������
		 Map<String,Object> map=new HashMap<>() ;       //�����ɫID��Ȩ��ID
		 map.put("roleId", roleId)  ;  //�����ɫID
		 List<Integer> list=new ArrayList<>()  ;  //����ȫ��Ȩ��ID
		 for(Permission permission : entity.getPermissions())
			                  list.add(permission.getPermissionId()) ;
		 map.put("list", list) ;  //����ȫ��Ȩ��ID
		 count=count+this.roleDAO.doCreateAfter(map) ;
		 return count   ;
	}

	//ɾ��Ҫͬʱɾ�����ű������
	@Override
	public Integer doRemove(Integer id) throws Exception {
	       Integer count= 0 ;
           //1.��ɾ��t_role_permission���Ӧ������
	       count+=this.roleDAO.doRemovePre(id)  ;  
	       //2.��ɾ��t_role���Ӧ������
	       count+=this.roleDAO.doRemove(id)  ;
	       return count   ;
	}

	@Override
	public Integer doUpdate(Role entity) throws Exception {
		Integer count=0 ;
		//1.ɾ��t_role_permission��Ӧ������:doRemovePre()
		count+=this.roleDAO.doRemovePre(entity.getRoleId())    ;
		//2.�޸�t_role 
		count+=this.roleDAO.doUpdate(entity)   ;
		//3.��t_role_permission���Ӷ�Ӧ������
		Map<String,Object> map=new HashMap<>() ;       //�����ɫID��Ȩ��ID
		map.put("roleId", entity.getRoleId())  ;  //�����ɫID
		List<Integer> list=new ArrayList<>()  ;  //����ȫ��Ȩ��ID
		for(Permission permission : entity.getPermissions())
			                   list.add(permission.getPermissionId()) ;
		map.put("list", list) ;  //����ȫ��Ȩ��ID
		count+=this.roleDAO.doCreateAfter(map)  ;
		return count;
	}

	
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	

}
