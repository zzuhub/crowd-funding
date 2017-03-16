package cn.wcj.dao;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.Permission;
/**
 * Permission-DAO��
 * @author WangCJ
 *
 *
 *1.�����������չDAO����
 *
 *
 *2.�̳�IBaseDAO,���뷺�Ϳ��Թ����Լ�ͨ�õ�CRUD����
 *
 *
 *3.����ӿ���Ҫ��MyBatis��Mapper�ļ���ƥ��
 *
 *
 *@since 2017-03-07 08:36:05
 */
@Repository("permissionDAO")
public interface IPermissionDAO extends IBaseDAO<Permission, Integer> {

	  /**
	   * ɾ��Ȩ��֮ǰ,Ҫ��ɾ��Ȩ��-��ɫ�м�������
	   * @param id Ȩ�ޱ��
	   * @return ����ɾ����������
	   * @throws Exception �쳣ֱ���׳������쳣����������
	   */
	  Integer doRemovePre(Integer id)throws Exception   ;
	
	  
}
