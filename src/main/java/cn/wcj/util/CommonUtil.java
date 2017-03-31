package cn.wcj.util;

import java.util.ArrayList;
import java.util.List;

import cn.wcj.entity.Permission;
import cn.wcj.entity.Role;

/**
 * ��װͨ�÷���
 * @author WangCJ
 *
 */
public class CommonUtil {
    /**
     * 
     * @param perms Ȩ��ID
     * @return Ȩ�޶��󼯺�
     * @throws Exception �쳣ֱ���׳�,ͳһ���쳣AOP����
     */
	public static List<Permission> genPermissionList(Integer[] perms)throws Exception{
		   List<Permission> permissions=new ArrayList<>()   ;
		   for(Integer permissionId : perms){
			   Permission permission=new Permission()  ;  //Ȩ�޶��� 
			   permission.setPermissionId(permissionId);  //����Ȩ��ID
			   permissions.add(permission)  ;  //��Ȩ�޶����б����Ȩ�� 
		   }
		   return permissions  ;
	}
	
	/**
	 * 
	 * @param roles ��ɫID����
	 * @return ��װ��ɫID�Ľ�ɫ�б�,ר�����DAO�����������õ�
	 * @throws Exception �쳣ֱ���׳�,�������Ʋ㴦�� 
	 */
	public static List<Role> genRoleList(Integer[] roles)throws Exception{
		   List<Role> roleList=new ArrayList<>()  ;
		   for(Integer roleId : roles){
			   Role role=new Role()   ;
			   role.setRoleId(roleId) ;   //���ý�ɫID
			   roleList.add(role)   ;   //���ӽ�ɫ
		   }
		   return roleList  ;
	}
	
	
}
