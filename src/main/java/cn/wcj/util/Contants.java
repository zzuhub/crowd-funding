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
	 
	 //过滤器链文件的名称
	 public static final String FILTER_CHAIN_FILE="filterChain.properties"   ;
	 
	 //AJAX调用SpringMVC回传JSON格式的状态码,反馈操作状态
	 public static final String SUCCESS_STATUS_JSON="{\"status\":\"1\"}" ;
	 public static final String FAILURE_STATUS_JSON="{\"status\":\"0\"}"  ;
	 
	 
}
