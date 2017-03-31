package cn.wcj.test;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wcj.entity.Approve;
import cn.wcj.entity.Project;
import cn.wcj.service.IApproveService;

import com.github.pagehelper.PageInfo;
@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApproveServiceTest {

	@Autowired
	private IApproveService approveService  ;
	
	@Test
	public void test() {
		assertNotNull(approveService);
	}
	
	@Test
	public void testDoCreate()throws Exception{
		  Approve approve = new Approve() ;
		  Project project=new Project()   ;
		  project.setProjectId(1);
		  approve.setProject(project);
		  approve.setName("刘王八") ;
		  approve.setTel("15890037523");
		  approve.setMoney(6.66) ;
		  //支持时间使用默认的即可
		  System.out.println("更新数据量:"+this.approveService.doCreate(approve));
	}
	
	
	@Test
	public void testFindByID()throws Exception{
		System.out.println(this.approveService.findByID(1));
		System.out.println(this.approveService.findByID(2));
	}
	
	@Test
	public void testDoUpdate()throws Exception{
		  Approve approve = this.approveService.findByID(2) ;  //线查找
		  approve.setName("刘先生") ;
		  approve.setTel("999")  ;
		  System.out.println("更新数据量:"+this.approveService.doUpdate(approve));
	}
	
	@Test
	public void testDoRemove()throws Exception{
		System.out.println("删除数据量:"+this.approveService.doRemove(3));
	}
	
	@Test
	public void testFindAll()throws Exception{
		Map<String, Object> map = this.approveService.findAll(1, 10, "刘")  ;
		PageInfo<Approve> pageinfo=(PageInfo<Approve>) map.get("pageInfo") ;
		List<Approve> approves = (List<Approve>) map.get("data") ;
		for(Approve approve : approves )
			System.out.println(approve)  ;     ;
	    System.out.println(pageinfo)     ;
	    System.out.println("JSON:"+JSONArray.fromObject(approves));
	}
	
	@Test
	public void testDoBatchCreate()throws Exception{
		Project project=new Project()   ;
		project.setProjectId(1);
		
		Approve approve1 = new Approve();
        approve1.setMoney(7.77);
        approve1.setName("approve-1");
        approve1.setTel("110");
		approve1.setProject(project);  
		
		Approve approve2 = new Approve();
		approve2.setProject(project);
        approve2.setMoney(8.88);
        approve2.setName("approve-2");
        approve2.setTel("119");
        
        System.out.println("更新数据量:"+this.approveService.doBatchCreate(Arrays.asList(approve1,approve2)));
        
        
	}

}
