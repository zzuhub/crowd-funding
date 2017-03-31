package cn.wcj.service;

import java.util.List;

import cn.wcj.entity.Project;

public interface IProjectService extends IBaseService<Project, Integer> {
	
	/**
	 * 
	 * @param userId  用户ID
	 * @return 返回项目列表
	 * @throws Exception  异常直接抛出,交给异常AOP处理 
	 */
	List<Project> findByUserID(Integer userId)throws Exception  ;
	
}
