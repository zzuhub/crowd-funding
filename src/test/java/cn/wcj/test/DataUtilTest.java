package cn.wcj.test;


import org.junit.Test;

import cn.wcj.util.Contants;
import cn.wcj.util.DataUtil;
/**
 * 测试加密算法
 * 
 * 
 * @author WangCJ
 *
 *加密后的结果
 *(name=wcj1234,password=1234wcj) ===> ed6469759b82ee2598de0f8feb8d5e22
 *
 *(name=atguigu,password=atguigu@java) ===> d94a89bdf5b7109c1a1bc955e4146736 
 *
 *
 *UPDATE t_user SET password='ed6469759b82ee2598de0f8feb8d5e22' WHERE name='wcj1234';
 *UPDATE t_user SET password='d94a89bdf5b7109c1a1bc955e4146736' WHERE name='atguigu';
 *COMMIT ;
 *SELECT * FROM t_user ;
 *
 */
public class DataUtilTest {
    
	@Test
	public void testEncrypt() {
		String result = DataUtil.encrypt(Contants.ALGORITHM_MD5, 
				          "1234wcj",
				         "wcj1234",
				         Contants.COMMON_HASH_ITERATIONS)  ;
		System.out.println(result);
		System.out.println(result.length());
	}
	
	

}
