package cn.wcj.test;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * ����MyBatis����Spring�����ļ�
 * @author WangCJ 
 *˼·:��̨����DAO��Service�㶼û������ȥ��ǰ̨
 */
@ContextConfiguration(locations={"/beans-spring.xml","/beans-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MyBatisTest {

	@Autowired
	private DataSource dataSource  ;   
	
	@Test
	public void testDataSource() {
		System.out.println(dataSource);
		//assertNotNull(dataSource);
	}
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean ;
	
	@Test
	public void testSqlSessionFactoryBean(){
		 System.out.println(sqlSessionFactoryBean);
	}
	
	

}
