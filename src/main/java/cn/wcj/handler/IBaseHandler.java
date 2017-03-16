package cn.wcj.handler;

import java.io.Serializable;


/**
 * 
 * 控制层方法抽象
 * 
 * @author WangCJ
 *
 *
 *因为控制层每个CRUD方法都不通用,所以不再进行细粒度的划分
 *
 */
public interface IBaseHandler<T> extends Serializable {

	//重新定向到登录页
	public static final String REDIRECT_LOGIN_PAGE="redirect:/login.jsp"  ;
    
	//首页
	public static final String INDEX_PAGE="main/index"  ;
	
	/**
	 * 
	 * @param currentPage 当前页
	 * @param lineSize 每页显示数据量
	 * @param keyWord  检索关键字
	 * @return  JSON数据
	 * @throws Exception  异常直接抛出 交给异常AOP统一处理
	 */
	String findAll(Integer currentPage,
                          Integer lineSize,
                          String keyWord)throws Exception ;
	
	
}
