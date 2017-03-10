package cn.wcj.service;

import java.util.Set;

import cn.wcj.entity.User;
/**
 * 
 * 
 * ���䷽��,���������ģʽ�ﵽ�˴���ĸ����Ժ�������
 * 
 * 
 * @author WangCJ
 *
 */
public interface IUserService extends IBaseService<User, Integer> {
	//�����û����Ʋ�ѯ�û���Ϣ,����Shiro��ܶԵ�¼��Ϣ������֤
	User findByName(String name)throws Exception   ;
	
	//�����û�����ѯ��ɫ�����б�,���ｫDAO�ķ��ؽ��List<String>��װ��Set<String>�з�����Shiro��ܵ�Realm�����Ȩ
	Set<String> findRoleNamesByName(String name)throws Exception ;
	
	//�����û�����ѯȨ�������б�,����DAO�ķ��ؽ��List<String>��װ��Set<String>�лص���Shiro��ܵ�Realm�����Ȩ
	Set<String> findPermissionNamesByName(String name)throws Exception  ;
	
	
	
}
