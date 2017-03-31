package cn.wcj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.Project;

@Repository("projectDAO")
public interface IProjectDAO extends IBaseDAO<Project, Integer> {
	
	/**
	 * 
	 * @param userId  用户ID
	 * @return 返回项目列表
	 * @throws Exception  异常直接抛出,交给异常AOP处理 
	 */
	List<Project> findByUserID(Integer userId)throws Exception  ;
	
}
