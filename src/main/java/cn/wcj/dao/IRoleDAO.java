package cn.wcj.dao;


import java.util.Map;


import org.springframework.stereotype.Repository;

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
@Repository("roleDAO")
public interface IRoleDAO extends IBaseDAO<Role, Integer> {
	 
	 //������һ������������ID
	 Integer findNextID()throws Exception   ;  
	
	 //����Roleһ������֮��,�������ֵ�t_role_permission��������
	 //����Ҫʹ������������MyBatis�����ļ�,������˵��һ����ս
	 //�����ս���ǲ���,��������ʵʵ����Map��
	 Integer doCreateAfter(Map<String,Object> map) throws Exception  ;
	 
	 //ɾ��t_role_permission,t_user_role��Ӧ������
	 Integer doRemovePre(Integer roleId)throws Exception   ;
	 
	 //����ǰ,ɾ��t_role_permission���Ӧ������
	 Integer doUpdatePre(Integer roleId)throws Exception   ;
	 
	 //���ݽ�ɫ���Ʋ�ѯ��ɫ,��Ҫ�Ǹ�AJAX��CRUDʱ��֤�û�����Ψһ��
	 Role findByName(String name)throws Exception    ;
	 
	 
	 
}
