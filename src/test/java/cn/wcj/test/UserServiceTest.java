package cn.wcj.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;

import cn.wcj.entity.Role;
import cn.wcj.entity.User;
import cn.wcj.service.IRoleService;
import cn.wcj.service.IUserService;
@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@Autowired
	private IUserService userService    ;
	
	@Autowired
	private IRoleService roleService   ;
	
	@Test
	public void testDoCreate() throws Exception {
		 Role role = roleService.findByID(3)  ;
		 User user=new User() ;
		 user.setName("阿键1234") ;
		 user.setPassword("123456") ;
		 user.setRoles(Arrays.asList(role)) ;
		 System.out.println(userService.doCreate(user))    ;
	}
	
	@Test
	public void testDoRemove()throws Exception {
		System.out.println(userService.doRemove(7))        ;
	}
	
	@Test
	public void testFindById()throws Exception{
		System.out.println(userService.findByID(1));
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		 User user = userService.findByID(1);
		 user.setLastLogin(new Date()) ;
		 user.setPassword("1234wcj")   ;
		 user.setName("wcj1234");
		 user.setRoles(Arrays.asList(roleService.findByID(1)));
		 Integer count = userService.doUpdate(user) ;
		 System.out.println("更新数据条数:"+count) ;
	}
	
	@Test
	public void testDoBatchCreate()throws Exception{
		  User user=new User()    ;
		  user.setName("atguigu") ;
		  user.setPassword("atguigu@java") ;
		  user.setRoles(Arrays.asList(roleService.findByID(1))) ;
		  Integer count = userService.doBatchCreate(Arrays.asList(user))  ;
		  System.out.println("增加数据量:"+count);
	}
	
	@Test
	public void testFindAll()throws Exception{
		 Map<String, Object> map = userService.findAll(1, 2,  "wcj1234")   ;
		 List<User> list=(List<User>) map.get("data") ;
		 for(User user : list)
			   System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+user);     ;
		 PageInfo<User> pageInfo=(PageInfo<User>) map.get("pageInfo");
		 System.out.println(pageInfo)   ;
	}
	
	@Test
	public void testFindByName()throws Exception{
		  System.out.println(">>>>>>>>>>>>"+userService.findByName("wcj1234111"));
	}
	
	@Test
	public void testFindRoleNamesByName()throws Exception{
		System.out.println(">>>>>>>>>>>>>>"+userService.findRoleNamesByName("wcj1234"));
	}
	
	
	@Test
	public void testFindPermissionNamesByName()throws Exception{
		System.out.println(">>>>>>>>>>"+userService.findPermissionNamesByName("wcj1234")) ;
	}
	
	
	
	
	

}
