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
 * ���ڹ�������,ȫ�����������ļ���
 * 
 * 
 * Ȼ��ͨ����������(K,V)��ֵ��
 * 
 * ע��ʹ��LinkedHashMap�����ֵ��
 * 
 * ��Ϊ����Map����ʱ�����
 * 
 * 
 * @author WangCJ
 *
 */
public class FilterChainDefinitionMapBuilder {
	
	public LinkedHashMap<String, String> buildfilterChainDefinitionMap()throws Exception{
			//1.׼��˳�򼯺�
		    LinkedHashMap<String, String> map=new LinkedHashMap<String,String>()  ;
			//2.��ȡ�����ļ�
		    ClassLoader loader = this.getClass().getClassLoader();
			InputStream in = loader.getResourceAsStream(Contants.FILTER_CHAIN_FILE)  ;
			InputStream inputStream = new BufferedInputStream(in)  ;
			Properties properties = new OrderedProperties();
			properties.load(inputStream);
			Iterator<String> iterator = properties.stringPropertyNames().iterator()  ;
		    for(;iterator.hasNext();){
			   String key = iterator.next()   ;
			   String value=properties.getProperty(key)  ;
			   map.put(key, value)  ;   //�򼯺��з���K/V��ֵ��
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
