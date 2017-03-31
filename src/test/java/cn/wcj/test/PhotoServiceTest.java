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
import cn.wcj.entity.Photo;
import cn.wcj.entity.Project;
import cn.wcj.service.IPhotoService;

import com.github.pagehelper.PageInfo;
@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PhotoServiceTest {

	@Autowired
	private IPhotoService photoService  ;
	
	@Test
	public void test() {
		assertNotNull(photoService);
	}
	
	@Test
	public void testDoCreate()throws Exception{
		Photo photo=new Photo()   ;
		//----------------项目--------------------------
		Project project=new Project()  ;
		project.setProjectId(1) ; 
		//----------------项目--------------------------
		photo.setProject(project) ;
		photo.setName("yello.jpg")  ;   //簧片
		System.out.println("增加数据量:"+this.photoService.doCreate(photo) ); ;
	}
	
	@Test
	public void testFindByID()throws Exception{
		   System.out.println(this.photoService.findByID(3))  ;
	}
	
	
	@Test
	public void testDoUpdate()throws Exception{
		  Photo photo = this.photoService.findByID(2) ;
		  photo.setName("photo.jpg") ;
		  System.out.println("更新数据量:"+photoService.doUpdate(photo));
	}
	
	@Test
	public void testDoRemove()throws Exception{
		   System.out.println("删除数据量:"+photoService.doRemove(2));
	}
	
	
	@Test
	public void testFindAll()throws Exception{
		 Map<String, Object> map = this.photoService.findAll(1, 10, "");
		 PageInfo<Category> pageInfo=(PageInfo<Category>) map.get("pageInfo") ;
		 List<Photo> photos=(List<Photo>) map.get("data")  ;
         System.out.println(pageInfo)   ;
         for(Photo photo : photos)
        	    System.out.println(photo)       ;
	}
	
	@Test
	public void testDoBatchCreate()throws Exception{
		 Photo photo=new Photo()   ;
		 photo.setName("test.png") ;
		 Project project=new Project()   ;
		 project.setProjectId(1) ;
		 photo.setProject(project);
		 System.out.println("增加数据量:"+this.photoService.doBatchCreate(Arrays.asList(photo))); ;
	}
	
	
	
	

}
