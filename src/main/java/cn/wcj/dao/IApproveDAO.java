package cn.wcj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.Approve;

@Repository("approveDAO")
public interface IApproveDAO extends IBaseDAO<Approve, Integer> {
	
	/**
	 * 
	 * @param approve 支持对象
	 * @return 更新t_project表的real_money字段成功的字数
	 * @throws Exception 异常直接抛出,交给异常AOP处理 
	 */
	Integer doCreateAfter(Approve approve)throws Exception ;
	
	Integer doBatchCreateAfter(List<Approve> approves)throws Exception   ;
	
	
	
}
