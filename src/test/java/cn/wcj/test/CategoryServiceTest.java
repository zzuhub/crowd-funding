package cn.wcj.test;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wcj.entity.Category;
import cn.wcj.service.ICategoryService;

import com.github.pagehelper.PageInfo;
@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest {

	@Autowired
	private ICategoryService categoryService   ;
	
	@Test
	public void test() {
		assertNotNull(categoryService);
	}
	
	@Test
	public void testDoCreate()throws Exception{
		Category category=new Category()  ;
		category.setName("其它") ;
		System.out.println("增加数据量:"+categoryService.doCreate(category));
	}
	
	@Test
	public void testFindById()throws Exception{
		System.out.println(categoryService.findByID(2));
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		Category category = categoryService.findByID(1);
		category.setName("测试修改类别") ;
		System.out.println("更新数据量 : "+categoryService.doUpdate(category));
	}
	
	@Test
	public void testDoRemove()throws Exception{
		System.out.println("删除数据量:"+categoryService.doRemove(1));
	}
	
	@Test
	public void testFindAll()throws Exception{
		Map<String, Object> map = categoryService.findAll(0, 10, "");
		List<Category> categories=(List<Category>) map.get("data");
		for(Category category : categories)
			 System.out.println(category)   ;
		PageInfo<Category> pageInfo=(PageInfo<Category>) map.get("pageInfo") ;
		System.out.println(pageInfo);
	}
	
	@Test
	public void testDoBatchCreate()throws Exception{
		Category c1=new Category() ;
		c1.setName("批量类别-1");
		Category c2=new Category() ;
		c2.setName("批量类别-2") ;
		System.out.println("增加数据量:"+categoryService.doBatchCreate(Arrays.asList(c1,c2)));
	}
	
	@Test
	public void testFindByName()throws Exception{
		   System.out.println(categoryService.findByName("测试类别"));
	}
	

	

}
