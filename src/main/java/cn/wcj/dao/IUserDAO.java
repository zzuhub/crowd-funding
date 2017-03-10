package cn.wcj.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.User;
/**
 * 
 * �����������������û��ķ���
 * 
 * 
 * @author WangCJ
 * 
 * 
 * 
 *
 */
@Repository("userDAO")
public interface IUserDAO extends IBaseDAO<User, Integer> {
	
	//list--->roleId    userId----->userId
	Integer doCreateAfter(Map<String,Object> map)throws Exception ;
	
	Integer findNextID()throws Exception   ;
	
	//��ɾ��t_user���Ӧ������֮ǰ,��ɾ�����������ݱ�(�ܹ�7�ű�,8��SQL)
	Integer doRemovePre(Integer userId) throws Exception ;
	
	//�ڸ���t_user��֮ǰ,�Ȱ�t_user_role���Ӧ������ɾ��
	Integer doUpdatePre(Integer userId)throws Exception   ;
	
	//�����û�����ѯ�û���Ϣ
	//�����û�����ѯ�û���Ϣ,����,ǰ̨ͨ��Ajax������û���Ψһ�Եı�֤
	User findByName(String name)throws Exception   ;

	//�����û����Ʋ�ѯ��ɫ�����б�
	List<String> findRoleNamesByName(String name)throws Exception  ;
	
	//�����û�����ѯȨ�������б�
	List<String> findPermissionNamesByName(String name)throws Exception   ;
	
	
	
	
	
}
