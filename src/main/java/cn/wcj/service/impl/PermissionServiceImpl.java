package cn.wcj.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.IPermissionDAO;
import cn.wcj.entity.Permission;
import cn.wcj.service.IPermissionService;
/**
 * �����������Service��,Controller���þ͵�������༴��,ǧ������̬,
 * SB��ֻ̬��ǿ������ת�Ͳ����õ����������ⷽ��
 * @author WangCJ
 *
 *@since 2017-03-07 10:51:16
 *
 */
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer>
		                           implements IPermissionService                {

	@Autowired
	private IPermissionDAO permissionDAO  ;
	
	//ֻ��������ʽ���ܽ�DAO����BaseServiceImpl��ȥ
	@Resource(name="permissionDAO")
	@Override
	public void setDao(IBaseDAO<Permission, Integer> dao) {
		super.setDao(dao);
	}

	@Override
	public Integer doRemove(Integer id) throws Exception {
		Integer count= 0 ;
		count+=this.permissionDAO.doRemovePre(id) ;   //ɾ�������ֵ�
		count+=super.getDao().doRemove(id)  ;        //ɾ��Ȩ�ޱ��Ӧ������
		return count;
	}
	
	

     	 
	 
	 
	 
	
	
	
	

	  
	
	 
	
	
}
