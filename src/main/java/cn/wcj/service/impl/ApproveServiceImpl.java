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

	//��дͨ��DAO
	@Resource(name="approveDAO")
	@Override
	public void setDao(IBaseDAO<Approve, Integer> dao) {
		 super.setDao(dao);
	}

	//�������б�Approve�������ݲ���:
	//1.��t_approve����������
	//2.����t_project���real_money�ֶ�
	@Override
	public Integer doCreate(Approve entity) throws Exception {
		Integer count=0  ;   //���������� 
		count+=this.approveDAO.doCreate(entity)  ;     //1.��t_approve����������
		count+=this.approveDAO.doCreateAfter(entity)   ; //2.����t_project���real_money�ֶ�
		return count  ;
	}

	@Override
	public Integer doBatchCreate(List<Approve> entities) throws Exception {
		Integer count=0 ;   //���������� 
		//1.t_approve
		count+=this.approveDAO.doBatchCreate(entities)  ;
		//2.t_project
		count+=this.approveDAO.doBatchCreateAfter(entities)  ;
		return count ;
	}
	
	
	
	
	
}
