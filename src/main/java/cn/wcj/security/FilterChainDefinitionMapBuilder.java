package cn.wcj.security;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;

import cn.wcj.util.Contants;
import cn.wcj.util.OrderedProperties;

/**
 * 
 * 对于过滤器链,全部放置属性文件里
 * 
 * 
 * 然后通过这个类读到(K,V)键值对
 * 
 * 注意使用LinkedHashMap保存键值对
 * 
 * 因为上述Map保存时有序的
 * 
 * 
 * @author WangCJ
 *
 */
public class FilterChainDefinitionMapBuilder {
	
	public LinkedHashMap<String, String> buildfilterChainDefinitionMap()throws Exception{
			//1.准备顺序集合
		    LinkedHashMap<String, String> map=new LinkedHashMap<String,String>()  ;
			//2.读取数据文件
		    ClassLoader loader = this.getClass().getClassLoader();
			InputStream in = loader.getResourceAsStream(Contants.FILTER_CHAIN_FILE)  ;
			InputStream inputStream = new BufferedInputStream(in)  ;
			Properties properties = new OrderedProperties();
			properties.load(inputStream);
			Iterator<String> iterator = properties.stringPropertyNames().iterator()  ;
		    for(;iterator.hasNext();){
			   String key = iterator.next()   ;
			   String value=properties.getProperty(key)  ;
			   map.put(key, value)  ;   //向集合中放入K/V键值对
		    }
			inputStream.close()  ; 
			return map  ;
	}
	
/*	
   public static void main(String[] args) throws Exception{
		Map<String,String> map = new FilterChainDefinitionMapBuilder().buildfilterChainDefinitionMap();
		Iterator<String> keys = map.keySet().iterator();
		while(keys.hasNext()){
			 String key=keys.next();
			 String val=map.get(key) ;
			 System.out.println("key : "+key+" , val : "+val) ;
		}
	}

*/
	
	
	
	
}
