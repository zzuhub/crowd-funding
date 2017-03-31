package cn.wcj.test;



import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wcj.entity.Permission;
import cn.wcj.entity.Role;
import cn.wcj.service.IPermissionService;
import cn.wcj.service.IRoleService;
@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceTest {

	@Autowired
	private IRoleService roleService    ;
	
	@Test
	public void testDoCreate() throws Exception {
		Role role=new Role()  ;
		role.setName("itcast") ;
		role.setNote("传智播客") ;
		Permission permission=new Permission()  ;
		permission.setPermissionId(6) ;
		role.setPermissions(Arrays.asList(permission));  //1个角色有多种权限
		roleService.doCreate(role)  ;
	}
	
	@Test
	public void testFindByID()throws Exception{
		 System.out.println(roleService.findByID(3));
	}
	
	@Test
	public void testDoRemove()throws Exception{
		 System.out.println("删除数据量:"+roleService.doRemove(1)) ;
	}
	
	@Autowired
	private IPermissionService permissionService  ;
	
	@Test
	public void testDoUpdate()throws Exception{
		 Role role = this.roleService.findByID(6);
		 role.setName("Update-Name")   ;
		 role.setNote("测试修改角色名字")  ;
		 Permission permission=permissionService.findByID(5)  ;
		 role.setPermissions(Arrays.asList(permission));
		 System.out.println(this.roleService.doUpdate(role));   ;
	}
	
	
	@Test
	public void testDoBatchCreate()throws Exception{
		   Role role=new Role() ;
		   role.setName("batch-role1111")  ;
		   role.setNote("批量角色11111")  ;
		   Integer count = roleService.doBatchCreate(Arrays.asList(role))   ;
		   System.out.println("count="+count);
	}
	
	
	@Test
	public void testFindAll()throws Exception{
		  Map<String, Object> map = this.roleService.findAll(1, 2, "经理")   ;
		  List<Role> roles=(List<Role>) map.get("data");
		  for(Role role : roles){
			  System.out.println(role);
		  }
		  System.out.println(map.get("pageInfo"));
	}
	
	@Test
	public void testFindByName()throws Exception{
		 System.out.println(this.roleService.findByName("admin1111"));
	}
	

	

}
