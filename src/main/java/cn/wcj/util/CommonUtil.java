package cn.wcj.util;

import java.util.ArrayList;
import java.util.List;

import cn.wcj.entity.Permission;

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
	
}
