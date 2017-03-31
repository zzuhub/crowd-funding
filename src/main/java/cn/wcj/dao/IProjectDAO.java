package cn.wcj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.Project;

@Repository("projectDAO")
public interface IProjectDAO extends IBaseDAO<Project, Integer> {
	
	/**
	 * 
	 * @param userId  �û�ID
	 * @return ������Ŀ�б�
	 * @throws Exception  �쳣ֱ���׳�,�����쳣AOP���� 
	 */
	List<Project> findByUserID(Integer userId)throws Exception  ;
	
}
