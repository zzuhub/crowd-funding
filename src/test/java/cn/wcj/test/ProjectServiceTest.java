package cn.wcj.test;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wcj.entity.Category;
import cn.wcj.entity.Project;
import cn.wcj.entity.User;
import cn.wcj.service.IProjectService;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceTest {

	@Autowired
	private IProjectService projectService  ;
	
	@Test
	public void test() {
		 assertNotNull(projectService)  ;
	}
	
	@Test
	public void testDoCreate()throws Exception{
		Project project=new Project() ;
		project.setExpectMoney(5000.0) ;
		project.setTel("15890037523") ;
		project.setTitle("郑州核电站") ;
		project.setNote("郑州人民欢迎核武器入住") ;

		Category category=new Category();
		category.setId(1) ;
		project.setCategory(category) ;
		
		User user=new User()  ;
		user.setUserId(1)     ;
		project.setUser(user) ;
		
		System.out.println("增加数据量:"+projectService.doCreate(project));
		
	}
	
	
	@Test
	public void testFindByID()throws Exception{
		System.out.println(projectService.findByID(1));
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		Project project = projectService.findByID(2) ;
		project.setTel("666")  ;
		project.setTitle("修改标题") ;
		project.setNote("修改内容")  ;
		project.setStatus(0);
		System.out.println("更新数据量:"+projectService.doUpdate(project));
	}
	
	@Test
	public void testFindAll()throws Exception{
		 Map<String, Object> map = projectService.findAll(1, 10, "1");
		 List<Project> projects=(List<Project>) map.get("data") ;
		 PageInfo<Project> pageInfo=(PageInfo<Project>) map.get("pageInfo") ;
		 System.out.println(pageInfo) ;
		 for(Project project : projects) 
			 System.out.println(project) ;          ;
	}
	
	@Test
	public void testDoBatchCreate()throws Exception{
		 Project project=new Project() ;
		 project.setTitle("批量标题")  ;
		 project.setNote("批量内容")  ;
		 project.setExpectMoney(10000.0) ;
		 project.setTel("110") ;
		 
		Category category=new Category();
		category.setId(1) ;
		project.setCategory(category) ;
		
		User user=new User()  ;
		user.setUserId(1)     ;
		project.setUser(user) ;
		
		System.out.println("批量增加数据量:"+projectService.doBatchCreate(Arrays.asList(project)));
		
	}
	
	@Test
	public void testDoRemove()throws Exception{
		System.out.println(projectService.doRemove(2));
	}
	
	
	@Test
	public void testFindByUserId()throws Exception{
		System.out.println(projectService.findByUserID(1));
	}
	

}
