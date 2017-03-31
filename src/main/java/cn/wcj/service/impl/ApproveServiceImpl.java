package cn.wcj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IApproveDAO;
import cn.wcj.dao.IBaseDAO;
import cn.wcj.entity.Approve;
import cn.wcj.service.IApproveService;

@Service("approveService")
public class ApproveServiceImpl extends BaseServiceImpl<Approve, Integer>
		implements IApproveService {

	@Autowired
	private IApproveDAO approveDAO   ;

	//改写通用DAO
	@Resource(name="approveDAO")
	@Override
	public void setDao(IBaseDAO<Approve, Integer> dao) {
		 super.setDao(dao);
	}

	//向增加列表Approve增加数据步骤:
	//1.向t_approve表增加数据
	//2.更新t_project表的real_money字段
	@Override
	public Integer doCreate(Approve entity) throws Exception {
		Integer count=0  ;   //更新数据量 
		count+=this.approveDAO.doCreate(entity)  ;     //1.向t_approve表增加数据
		count+=this.approveDAO.doCreateAfter(entity)   ; //2.更新t_project表的real_money字段
		return count  ;
	}

	@Override
	public Integer doBatchCreate(List<Approve> entities) throws Exception {
		Integer count=0 ;   //更新数据量 
		//1.t_approve
		count+=this.approveDAO.doBatchCreate(entities)  ;
		//2.t_project
		count+=this.approveDAO.doBatchCreateAfter(entities)  ;
		return count ;
	}
	
	
	
	
	
}
