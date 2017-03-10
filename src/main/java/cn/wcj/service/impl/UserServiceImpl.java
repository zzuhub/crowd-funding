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
 * 用户Service层
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
 * 刚想起来批量增加用户的办法(同时增加角色,其实可以通过一层循环完成,增加不了算我输)
 * 
 * 
 * 
 * 伪代码: for(:) .....
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
		Map<String, Object> map=new HashMap<String, Object>();   //保存中间表数据
		map.put("userId", userDAO.findNextID())  ;   //user_id
		//1.向t_user表增加数据
		count+=userDAO.doCreate(entity)  ;
		List<Integer> list=new ArrayList<>()  ;     //保存角色ID
		//2.向t_user_role表增加数据
		for(Role role : entity.getRoles())    //添加角色ID
			              list.add(role.getRoleId()) ;
		map.put("list", list)   ;
		count+=userDAO.doCreateAfter(map)   ;
		return count  ;
	}


	@Override
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ; 
		count+=userDAO.doRemovePre(id)   ;  //1.删除t_user_role
		count+=userDAO.doRemove(id)      ;  //2.删除t_user
		return count ;
	}

	@Override
	public Integer doUpdate(User entity) throws Exception {
		Integer count=0 ;
		//1.删除t_user_role表对应的数据(doUpdatePre)
		count+=userDAO.doUpdatePre(entity.getUserId())   ;
		//2.更新t_user表(doUpdate)
		count+=userDAO.doUpdate(entity)  ;
		//3.批量增加t_user_role表(doCreateAfter)
		Map<String, Object> map=new HashMap<>();
		map.put("userId", entity.getUserId())   ;        //存入单值userId
		List<Integer> list=new ArrayList<>()    ;       //存储全部单值roleId
		for(Role role : entity.getRoles())
			       list.add(role.getRoleId())   ;       //存入单值roleId
		map.put("list", list)     ;                    //存储全部单值roleId
		count+=userDAO.doCreateAfter(map)       ;
		return count  ;
	}

    /*
     * (non-Javadoc)
     * @see cn.wcj.service.impl.BaseServiceImpl#doBatchCreate(java.util.List)
     * 
     * 
     * 这种批量策略只是一种折中策略,实在没办法,因为受制于自动增长序列,还有一种解决办法就是使用
     * UUID,依靠Java生成UUID这样可以批量增加而数据字典不用受制于依赖表的主键
     * 
     * 但是使用了UUID在MyCAT集群MySQL时分片会有点蛋疼,所以我后期会研究使用了UUID之后MySQL集群分片
     * 的策略
     * 
     * 
     * 
     */
	@Override
	public Integer doBatchCreate(List<User> entities) throws Exception {
		Integer count=0 ;
		for(User user : entities){
			Map<String, Object> map=new HashMap<>()  ;   //保存用户ID和角色ID列表(list)
			map.put("userId",userDAO.findNextID())      ;   //放入单值userId
			//1.增加t_user表
			count+=userDAO.doCreate(user)   ;
			//2.增加t_user_role表
			List<Integer> list=new ArrayList<>()     ;   //角色ID列表
			for(Role role : user.getRoles())
				             list.add(role.getRoleId())     ;  //添加单值角色ID
			map.put("list", list)  ;  //放入全部角色ID
			count+=userDAO.doCreateAfter(map)  ;   //最终批量向t_user_role表增加数据
		}
		return count    ;
	}


	@Override
	public User findByName(String name) throws Exception {
		User user=null ;
		user=this.userDAO.findByName(name)  ;   //根据用户名称查询用户
		return user    ;
	}


	@Override
	public Set<String> findRoleNamesByName(String name) throws Exception {
		Set<String> roleNameSet=new HashSet<>()  ;   //保存角色名称
		List<String> roleNameList = this.userDAO.findRoleNamesByName(name)  ;  //封装原生的角色名称列表
		roleNameSet.addAll(roleNameList)  ;   //将原生的角色名称列表添加到Set集合中,交给Shiro框架的Realm完成授权
		return roleNameSet ;  //返回角色名称列表
	}


	@Override
	public Set<String> findPermissionNamesByName(String name) throws Exception {
		Set<String> permissionNameSet=new HashSet<>()   ;   //保存权限名称列表
		List<String> permissionNameList=this.userDAO.findPermissionNamesByName(name)  ;  //原生权限名称列表
		permissionNameSet.addAll(permissionNameList)  ;   //封装原声权限名称列表 
		return permissionNameSet    ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  
	  
	  
	  
	  
	  
	
}
