package cn.wcj.util;

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
	
	
}
