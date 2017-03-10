package cn.wcj.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 
 * 数据处理工具类
 * 
 * @author WangCJ
 * 
 * @since 2017-03-07 10:32:45
 * 
 * 所有方法采用静态,不使用组件注解标识,方便调用
 *
 *新增加密方法
 *
 *
 *
 */
public class DataUtil {

	public static boolean isNull(String data)throws Exception{
		   return data==null || "".equals(data)   ;
	}
	
	public static String genDBKeyWord(String keyWord)throws Exception{
		   String result="%%"  ;   //默认关键词是查询全部
		   if(!isNull(keyWord))   //如果关键词不为空
			      result="%"+keyWord+"%"  ;   //组织关键词
		   return result   ;   //返回生成的结果
	}
	
	/**
	 * 盐值加密方式
	 * @param algorithmName 加密算法名称
	 * @param source 需要加密的字串
	 * @param salt 盐值
	 * @param hashIterations  加密次数
	 * @return
	 */
	public static  String encrypt(String algorithmName,String source ,String salt,Integer hashIterations){
		   String result=null ;
		   Object target = new SimpleHash(algorithmName, source, salt, hashIterations) ;  //盐值加密后返回对象
		   result=target.toString()  ;   //得到字符串形式加密后的密码
		   return result  ;  //返回加密结果 
	}
	
	
	
}
