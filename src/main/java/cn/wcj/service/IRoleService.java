package cn.wcj.service;

import cn.wcj.entity.Role;
/**
 * 
 * Roleʵ��ķ����ӿ�
 * 
 * @author WangCJ
 * 
 * @since 2017-03-08 08:38:28
 * 
 * ��Ҫ�����ﶨ������ķ���
 *
 */
public interface IRoleService extends IBaseService<Role, Integer> {
	 //���ݽ�ɫ���Ʋ�ѯ��ɫ,��Ҫ�Ǹ�AJAX��CRUDʱ��֤�û�����Ψһ��
	 Role findByName(String name)throws Exception    ;
}
