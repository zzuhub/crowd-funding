package cn.wcj.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;

import cn.wcj.entity.Comment;
import cn.wcj.entity.Project;
import cn.wcj.entity.User;
import cn.wcj.service.ICommentService;

@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentServiceTest {

	@Autowired
	private ICommentService commentService   ;
	
	@Test
	public void test()throws Exception{
		System.out.println(commentService);
	}
	
	@Test
	public void testDoCreate()throws Exception{
		 Comment comment=new Comment() ;    
		 comment.setContent("测试删除评论") ;
		 
		 Project project=new Project();
		 project.setProjectId(2);
		 comment.setProject(project);
		 
		 User user=new User()  ;
		 user.setUserId(1)     ;
		 comment.setUser(user);
		 
		 this.commentService.doCreate(comment)  ;
		 
	}
	
	@Test
	public void testFindById()throws Exception{
		 System.out.println(this.commentService.findByID(6));
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		Comment comment = this.commentService.findByID(1);
		comment.setContent("刘王八也会捐钱啊,哈哈") ;
		System.out.println("更新数据量:"+this.commentService.doUpdate(comment))  ;
	}
	
	@Test
	public void testDoRemove()throws Exception{
		  System.out.println("删除数据量:"+this.commentService.doRemove(4));
	}
	
	@Test
	public void testFindAll()throws Exception{
		 Map<String, Object> map = this.commentService.findAll(1, 10, "");
		 PageInfo<Comment> pageInfo=(PageInfo<Comment>) map.get("pageInfo") ;
		 List<Comment> comments=(List<Comment>) map.get("data")  ;
		 for(Comment comment : comments)
			      System.out.println(comment) ;        ;
         System.out.println(pageInfo)  ;			      
	}
	
	@Test
	public void testDoCreateBatch()throws Exception{
		 Comment comment=new Comment() ;    
		 comment.setContent("评论测试") ;
		 
		 Project project=new Project();
		 project.setProjectId(1);
		 comment.setProject(project);
		 
		 User user=new User()  ;
		 user.setUserId(1)     ;
		 comment.setUser(user);
		 
		 System.out.println("批量增加数据量:"+this.commentService.doBatchCreate(Arrays.asList(comment)));
		 
	}
	
	
	
}
