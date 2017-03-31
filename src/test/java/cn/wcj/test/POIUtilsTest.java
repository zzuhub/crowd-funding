package cn.wcj.test;



import org.junit.Test;

import cn.wcj.util.POIUtils;

public class POIUtilsTest {


	@Test
	public void testParseRoleStr() throws Exception {
		Integer[] roleIds = POIUtils.parseRoleStr("1,2,3");
		for(Integer roleId : roleIds)
			System.out.println(roleId)  ;
	}
	
	

}
