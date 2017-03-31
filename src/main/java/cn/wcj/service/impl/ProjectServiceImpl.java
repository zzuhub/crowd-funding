package cn.wcj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.IProjectDAO;
import cn.wcj.entity.Project;
import cn.wcj.service.IProjectService;

@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project, Integer>
		                        implements IProjectService {

	@Autowired
	private IProjectDAO projectDAO   ;

	@Resource(name="projectDAO")
	@Override
	public void setDao(IBaseDAO<Project, Integer> dao) {
		   super.setDao(dao);
	}

	@Override
	public List<Project> findByUserID(Integer userId) throws Exception {
		return this.projectDAO.findByUserID(userId) ;
	}
	
	
	
	
	
	
}
