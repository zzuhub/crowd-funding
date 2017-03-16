package cn.wcj.dao;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.Permission;
/**
 * Permission-DAO层
 * @author WangCJ
 *
 *
 *1.在这里可以扩展DAO方法
 *
 *
 *2.继承IBaseDAO,传入泛型可以构造自己通用的CRUD方法
 *
 *
 *3.这个接口需要和MyBatis的Mapper文件相匹配
 *
 *
 *@since 2017-03-07 08:36:05
 */
@Repository("permissionDAO")
public interface IPermissionDAO extends IBaseDAO<Permission, Integer> {

	  /**
	   * 删除权限之前,要先删除权限-角色中间表的数据
	   * @param id 权限编号
	   * @return 返回删除数据条数
	   * @throws Exception 异常直接抛出交给异常处理器处理
	   */
	  Integer doRemovePre(Integer id)throws Exception   ;
	
	  
}
