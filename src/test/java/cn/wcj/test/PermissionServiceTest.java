package cn.wcj.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;

import cn.wcj.entity.Permission;
import cn.wcj.service.IPermissionService;

@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PermissionServiceTest {

	@Autowired
	private IPermissionService permissionService   ;
	
	
	@Test
	public void testDoCreate() throws Exception {
		Permission permission=new Permission()   ;
		permission.setName("test-permission") ;
		permission.setNote("测试权限")    ;
		System.out.println(permissionService.doCreate(permission));
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		Permission permission = this.permissionService.findByID(6);
		permission.setName("test-update-permission") ;
		permission.setNote("测试修改权限")  ;
		System.out.println(permissionService.doUpdate(permission));
	}
	
	@Test
	public void testFindByID()throws Exception{
		   Permission permission = this.permissionService.findByID(6);
		   System.out.println(permission);
	}
	
	@Test
	public void testDoRemove()throws Exception{
		  System.out.println(permissionService.doRemove(2));
	}
	
	@Test
	public void testDoBatchCreate()throws Exception{
		 List<Permission> permissions=new ArrayList<>()  ;
		 for(int x=0;x<5;x++){
			 Permission permission=new Permission()  ;
			 permission.setName("permission-"+x);
			 permission.setNote("权限-"+x);
			 permissions.add(permission)   ;
		 }
		 System.out.println(permissionService.doBatchCreate(permissions));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAll()throws Exception{
		 Map<String, Object> map = permissionService.findAll(0, 2, "permission_id", "1")  ;
		List<Permission> permissions=(List<Permission>)map.get("data");
		 for(Permission permission : permissions)
			  System.out.println(permission)   ;
         PageInfo<Permission> pageInfo=(PageInfo<Permission>) map.get("pageInfo")   ;
         System.out.println(pageInfo);
	}
	
	
	
	
	
	

}
