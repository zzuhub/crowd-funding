package cn.wcj.service;

import java.util.Set;

import cn.wcj.entity.User;
/**
 * 
 * 
 * 扩充方法,这样的设计模式达到了代码的复用性和扩充性
 * 
 * 
 * @author WangCJ
 *
 */
public interface IUserService extends IBaseService<User, Integer> {
	//根据用户名称查询用户信息,用于Shiro框架对登录信息进行认证
	User findByName(String name)throws Exception   ;
	
	//根据用户名查询角色名称列表,这里将DAO的返回结果List<String>封装到Set<String>中返还给Shiro框架的Realm完成授权
	Set<String> findRoleNamesByName(String name)throws Exception ;
	
	//根据用户名查询权限名称列表,并将DAO的返回结果List<String>包装到Set<String>中回调给Shiro框架的Realm完成授权
	Set<String> findPermissionNamesByName(String name)throws Exception  ;
	
	
	
}
