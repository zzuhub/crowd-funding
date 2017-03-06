package cn.wcj.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author WangCJ
 *定义DAO公共接口,方便扩展
 * @param <T> 实体类型
 * @param <ID> 主键
 * 
 * 1.这个基础DAO接口是给所以MyBatis的DAO接口继承
 * 
 * 2.定义了许多公共CRUD方法
 * 
 * 3.泛型技术增加方法的通用性
 * 
 * @since 2017-03-06 21:35:03
 * 
 * 
 */
public interface IBaseDAO<T,ID> extends Serializable{

	//=================================================do**表示写操作start===========================================
	
	Integer doCreate(T entity)throws Exception    ;  
	
	Integer doUpdate(T entity)throws Exception     ;  
	
	Integer doRemove(ID id)throws Exception    ;
	
	//批量增加,由于使用了MySQL要格外小心配置MyBatis动态生成SQL
	Integer doBatchCreate(List<T> entities)throws Exception   ;
	
	//===================================================do**表示写操作end===========================================
	
	
	//=================================================find**表示读操作end===========================================
	
	T findByID(ID id)throws Exception   ;

	//根据列名和关键词查找,当需要分页的时候,服务层调用MyBatisPageHelper即可完成分页显示
	List<T> findAll(List<String> colNames,String keyWord)throws Exception   ;
	
	//===================================================find**表示读操作end===========================================
	
	
	
	
	
	
	
}
