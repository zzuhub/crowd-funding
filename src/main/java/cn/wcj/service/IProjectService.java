package cn.wcj.service;

import java.util.List;

import cn.wcj.entity.Project;

public interface IProjectService extends IBaseService<Project, Integer> {
	
	/**
	 * 
	 * @param userId  �û�ID
	 * @return ������Ŀ�б�
	 * @throws Exception  �쳣ֱ���׳�,�����쳣AOP���� 
	 */
	List<Project> findByUserID(Integer userId)throws Exception  ;
	
}
