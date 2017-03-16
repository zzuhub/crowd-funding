package cn.wcj.util;

import java.util.Collections;  
import java.util.Enumeration;  
import java.util.LinkedHashSet;  
import java.util.Properties;  
import java.util.Set;  
/** 
 * 顺序读取Properties文件类
 * 
 *  
 * @author WangCJ 
 * 
 * 
 * Shiro框架完成认证/授权时必须采用的一种机制
 * 
 */  
public class OrderedProperties extends Properties {  
  
	private static final long serialVersionUID = 1L;
	
	
	private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();  
  
    @Override  
    public Set<String> stringPropertyNames() {  
        Set<String> set = new LinkedHashSet<String>();  
        for (Object key : keys) {  
            set.add((String) key);  
        }  
        return set;  
    }  
  
    @Override  
    public Set<Object> keySet() {  
        return keys;  
    }  
  
    @Override  
    public synchronized Enumeration<Object> keys() {  
        return Collections.enumeration(keys);  
    }  
  
    @Override  
    public synchronized Object put(Object key, Object value) {  
        keys.add(key);  
        return super.put(key, value);  
    }  
  
}  