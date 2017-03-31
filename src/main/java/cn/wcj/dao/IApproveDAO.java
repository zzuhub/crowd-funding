package cn.wcj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.Approve;

@Repository("approveDAO")
public interface IApproveDAO extends IBaseDAO<Approve, Integer> {
	
	/**
	 * 
	 * @param approve ֧�ֶ���
	 * @return ����t_project���real_money�ֶγɹ�������
	 * @throws Exception �쳣ֱ���׳�,�����쳣AOP���� 
	 */
	Integer doCreateAfter(Approve approve)throws Exception ;
	
	Integer doBatchCreateAfter(List<Approve> approves)throws Exception   ;
	
	
	
}
