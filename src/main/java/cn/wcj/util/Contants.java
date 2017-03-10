package cn.wcj.util;

import java.io.Serializable;

/**
 * 
 * 定义一个接口,专门保存项目常用的常量,解耦合
 * 
 * @author WangCJ
 *
 *
 *@since 2017-03-10 22:47:42
 *
 *
 */
public interface Contants extends Serializable {

	 //加密算法
	
	 public static final String ALGORITHM_MD5="md5"   ;   
	
	 public static final String ALGORITHM_SHA1="sha1"   ;  
	
	 public static final Integer COMMON_HASH_ITERATIONS=1024  ;   //通用加密次数
	 
	 
	 
}
