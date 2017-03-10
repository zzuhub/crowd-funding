package cn.wcj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.IUserDAO;
import cn.wcj.entity.Role;
import cn.wcj.entity.User;
import cn.wcj.service.IUserService;

/**
 * �û�Service��
 * 
 * 
 * 
 * @author WangCJ
 * 
 * 
 * @since 2017-03-09 19:56:38
 * 
 * 
 * 
 * �����������������û��İ취(ͬʱ���ӽ�ɫ,��ʵ����ͨ��һ��ѭ�����,���Ӳ���������)
 * 
 * 
 * 
 * α����: for(:) .....
 * 
 * 
 * 
 * 
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> 
                             implements IUserService {

	@Autowired
	private IUserDAO userDAO   ;

	@Resource(name="userDAO")
	@Override
	public void setDao(IBaseDAO<User, Integer> dao) {
		super.setDao(dao);
	}

	
	@Override
	public Integer doCreate(User entity) throws Exception {
		Integer count= 0;
		Map<String, Object> map=new HashMap<String, Object>();   //�����м������
		map.put("userId", userDAO.findNextID())  ;   //user_id
		//1.��t_user����������
		count+=userDAO.doCreate(entity)  ;
		List<Integer> list=new ArrayList<>()  ;     //�����ɫID
		//2.��t_user_role����������
		for(Role role : entity.getRoles())    //��ӽ�ɫID
			              list.add(role.getRoleId()) ;
		map.put("list", list)   ;
		count+=userDAO.doCreateAfter(map)   ;
		return count  ;
	}


	@Override
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ; 
		count+=userDAO.doRemovePre(id)   ;  //1.ɾ��t_user_role
		count+=userDAO.doRemove(id)      ;  //2.ɾ��t_user
		return count ;
	}

	@Override
	public Integer doUpdate(User entity) throws Exception {
		Integer count=0 ;
		//1.ɾ��t_user_role���Ӧ������(doUpdatePre)
		count+=userDAO.doUpdatePre(entity.getUserId())   ;
		//2.����t_user��(doUpdate)
		count+=userDAO.doUpdate(entity)  ;
		//3.��������t_user_role��(doCreateAfter)
		Map<String, Object> map=new HashMap<>();
		map.put("userId", entity.getUserId())   ;        //���뵥ֵuserId
		List<Integer> list=new ArrayList<>()    ;       //�洢ȫ����ֵroleId
		for(Role role : entity.getRoles())
			       list.add(role.getRoleId())   ;       //���뵥ֵroleId
		map.put("list", list)     ;                    //�洢ȫ����ֵroleId
		count+=userDAO.doCreateAfter(map)       ;
		return count  ;
	}

    /*
     * (non-Javadoc)
     * @see cn.wcj.service.impl.BaseServiceImpl#doBatchCreate(java.util.List)
     * 
     * 
     * ������������ֻ��һ�����в���,ʵ��û�취,��Ϊ�������Զ���������,����һ�ֽ���취����ʹ��
     * UUID,����Java����UUID���������������Ӷ������ֵ䲻�������������������
     * 
     * ����ʹ����UUID��MyCAT��ȺMySQLʱ��Ƭ���е㵰��,�����Һ��ڻ��о�ʹ����UUID֮��MySQL��Ⱥ��Ƭ
     * �Ĳ���
     * 
     * 
     * 
     */
	@Override
	public Integer doBatchCreate(List<User> entities) throws Exception {
		Integer count=0 ;
		for(User user : entities){
			Map<String, Object> map=new HashMap<>()  ;   //�����û�ID�ͽ�ɫID�б�(list)
			map.put("userId",userDAO.findNextID())      ;   //���뵥ֵuserId
			//1.����t_user��
			count+=userDAO.doCreate(user)   ;
			//2.����t_user_role��
			List<Integer> list=new ArrayList<>()     ;   //��ɫID�б�
			for(Role role : user.getRoles())
				             list.add(role.getRoleId())     ;  //��ӵ�ֵ��ɫID
			map.put("list", list)  ;  //����ȫ����ɫID
			count+=userDAO.doCreateAfter(map)  ;   //����������t_user_role����������
		}
		return count    ;
	}


	@Override
	public User findByName(String name) throws Exception {
		User user=null ;
		user=this.userDAO.findByName(name)  ;   //�����û����Ʋ�ѯ�û�
		return user    ;
	}


	@Override
	public Set<String> findRoleNamesByName(String name) throws Exception {
		Set<String> roleNameSet=new HashSet<>()  ;   //�����ɫ����
		List<String> roleNameList = this.userDAO.findRoleNamesByName(name)  ;  //��װԭ���Ľ�ɫ�����б�
		roleNameSet.addAll(roleNameList)  ;   //��ԭ���Ľ�ɫ�����б���ӵ�Set������,����Shiro��ܵ�Realm�����Ȩ
		return roleNameSet ;  //���ؽ�ɫ�����б�
	}


	@Override
	public Set<String> findPermissionNamesByName(String name) throws Exception {
		Set<String> permissionNameSet=new HashSet<>()   ;   //����Ȩ�������б�
		List<String> permissionNameList=this.userDAO.findPermissionNamesByName(name)  ;  //ԭ��Ȩ�������б�
		permissionNameSet.addAll(permissionNameList)  ;   //��װԭ��Ȩ�������б� 
		return permissionNameSet    ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  
	  
	  
	  
	  
	  
	
}
