package cn.wcj.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author WangCJ
 *
 * @param <T> 实体类型
 * @param <ID> 主键类型
 * 
 * @since 2017-03-07 09:05:18
 * 
 * 1.这个接口抽象服务层全部通用操作
 * 
 * 2.利用Spring4.X提供的泛型依赖注入可以更漂亮的设计Service层,
 *   毕竟DAO层不用太多的封装,只需要我按成原子性的DAO操作就行了,对
 *   于业务的更细致的调用交给Service层
 * 
 * 
 * 
 * 
 */
public interface IBaseService<T,ID> {

	//=================================================do**表示写操作start===========================================
	
	Integer doCreate(T entity)throws Exception    ;  
	
	Integer doUpdate(T entity)throws Exception     ;  
	
	Integer doRemove(ID id)throws Exception    ;
	
	//批量增加,由于使用了MySQL要格外小心配置MyBatis动态生成SQL
	Integer doBatchCreate(List<T> entities)throws Exception   ;
	
	//===================================================do**表示写操作end===========================================
	
	
	//=================================================find**表示读操作end===========================================
	
	T findByID(ID id)throws Exception   ;

	/**
	 * 
	 * @param currentPage 当前页
	 * @param lineSize    每页显示数据量
	 * @param colNames    列名集合
	 * @param keyWord     搜索关键词
	 * @return            符合检索条件的实体集合和分页参数(KV键值对的形式)
	 * @throws Exception  异常直接抛出,我们会开发AOP形式的异常捕获方式
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize ,String colName,String keyWord)throws Exception   ;
	
	//===================================================find**表示读操作end===========================================
	
	
}
