package cn.wcj.dao;


import java.util.Map;


import org.springframework.stereotype.Repository;

import cn.wcj.entity.Role;
/**
 * 
 * 在这里可以加上DAO的特殊方法
 * 
 * @author WangCJ
 *
 * @since 2017-03-08 09:01:27
 *
 */
@Repository("roleDAO")
public interface IRoleDAO extends IBaseDAO<Role, Integer> {
	 
	 //查找下一次主键增长的ID
	 Integer findNextID()throws Exception   ;  
	
	 //增加Role一条数据之后,向数据字典t_role_permission增加数据
	 //这里要使用两个参数的MyBatis配置文件,对我来说是一次挑战
	 //这次挑战还是不行,还是老老实实传入Map吧
	 Integer doCreateAfter(Map<String,Object> map) throws Exception  ;
	 
	 //删除t_role_permission,t_user_role对应的数据
	 Integer doRemovePre(Integer roleId)throws Exception   ;
	 
	 //更新前,删除t_role_permission表对应的数据
	 Integer doUpdatePre(Integer roleId)throws Exception   ;
	 
	 //根据角色名称查询角色,主要是给AJAX做CRUD时验证用户名的唯一性
	 Role findByName(String name)throws Exception    ;
	 
	 
	 
}
