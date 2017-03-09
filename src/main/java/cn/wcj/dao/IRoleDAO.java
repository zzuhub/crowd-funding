package cn.wcj.dao;


import java.util.Map;

import org.springframework.stereotype.Service;

import cn.wcj.entity.Role;
/**
 * 
 * ��������Լ���DAO�����ⷽ��
 * 
 * @author WangCJ
 *
 * @since 2017-03-08 09:01:27
 *
 */
@Service("roleDAO")
public interface IRoleDAO extends IBaseDAO<Role, Integer> {
	 
	 //������һ������������ID
	 Integer findNextID()throws Exception   ;  
	
	 //����Roleһ������֮��,�������ֵ�t_role_permission��������
	 //����Ҫʹ������������MyBatis�����ļ�,������˵��һ����ս
	 //�����ս���ǲ���,��������ʵʵ����Map��
	 Integer doCreateAfter(Map<String,Object> map) throws Exception  ;
	 
	 //ɾ��t_role_permission��Ӧ������
	 Integer doRemovePre(Integer roleId)throws Exception   ;
	 
	 
	 
	 
	 
	 
	 
	 
}
