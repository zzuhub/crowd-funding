package cn.wcj.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.User;
/**
 * 
 * 刚想起来批量增加用户的方法
 * 
 * 
 * @author WangCJ
 * 
 * 
 * 
 *
 */
@Repository("userDAO")
public interface IUserDAO extends IBaseDAO<User, Integer> {
	
	//list--->roleId    userId----->userId
	Integer doCreateAfter(Map<String,Object> map)throws Exception ;
	
	Integer findNextID()throws Exception   ;
	
	//在删除t_user表对应的数据之前,先删除依赖的数据表(总共7张表,8条SQL)
	Integer doRemovePre(Integer userId) throws Exception ;
	
	//在更新t_user表之前,先把t_user_role表对应的数据删除
	Integer doUpdatePre(Integer userId)throws Exception   ;
	
	//根据用户名查询用户信息
	//根据用户名查询用户信息,放心,前台通过Ajax完成了用户名唯一性的保证
	User findByName(String name)throws Exception   ;

	//根据用户名称查询角色名称列表
	List<String> findRoleNamesByName(String name)throws Exception  ;
	
	//根据用户名查询权限名称列表
	List<String> findPermissionNamesByName(String name)throws Exception   ;
	
	
	
	
	
}
