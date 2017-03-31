package cn.wcj.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

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
import cn.wcj.entity.CategoryEchart;
import cn.wcj.entity.Photo;
import cn.wcj.entity.Project;
import cn.wcj.service.IEchartsService;
import cn.wcj.service.IPhotoService;

import com.github.pagehelper.PageInfo;
@SuppressWarnings("unchecked")
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EchartsServiceTest {

    @Autowired
    private IEchartsService echartsService  ;
	
	@Test
	public void test() {
		assertNotNull(echartsService);
	}
	
	@Test
	public void testFindCategoryEcharts()throws Exception{
		List<CategoryEchart> categoryEcharts = echartsService.findCategoryEcharts();
		for(CategoryEchart categoryEchart : categoryEcharts)
			                    System.out.println(categoryEchart)   ;
	}

}
