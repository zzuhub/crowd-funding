package cn.wcj.util;

import java.util.ArrayList;
import java.util.List;

import cn.wcj.entity.Permission;

/**
 * 封装通用方法
 * @author WangCJ
 *
 */
public class CommonUtil {
    /**
     * 
     * @param perms 权限ID
     * @return 权限对象集合
     * @throws Exception 异常直接抛出,统一由异常AOP处理
     */
	public static List<Permission> genPermissionList(Integer[] perms)throws Exception{
		   List<Permission> permissions=new ArrayList<>()   ;
		   for(Integer permissionId : perms){
			   Permission permission=new Permission()  ;  //权限对象 
			   permission.setPermissionId(permissionId);  //设置权限ID
			   permissions.add(permission)  ;  //向权限对象列表添加权限 
		   }
		   return permissions  ;
	}
	
}
