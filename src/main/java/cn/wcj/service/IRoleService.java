package cn.wcj.service;

import cn.wcj.entity.Role;
/**
 * 
 * Role实体的服务层接口
 * 
 * @author WangCJ
 * 
 * @since 2017-03-08 08:38:28
 * 
 * 主要在这里定义特殊的方法
 *
 */
public interface IRoleService extends IBaseService<Role, Integer> {
	 //根据角色名称查询角色,主要是给AJAX做CRUD时验证用户名的唯一性
	 Role findByName(String name)throws Exception    ;
}
