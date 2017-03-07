package cn.wcj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.service.IBaseService;
import cn.wcj.util.DataUtil;
import static cn.wcj.util.DataUtil.* ;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 这里的设计模式是跟徐培成老师学的
 * @author WangCJ
 *
 * @param <T>   实体类型
 * @param <ID>  主键类型
 * 
 * 
 * @since 2017-03-07 09:27:54
 * 
 */
public abstract class BaseServiceImpl<T,ID> implements IBaseService<T, ID> {

	private IBaseDAO<T, ID>  dao  ;     //通用DAO 
	
	

	public IBaseDAO<T, ID> getDao() {
		return dao;
	}

	public void setDao(IBaseDAO<T, ID> dao) {
		this.dao = dao;
	}

	@Override
	public Integer doCreate(T entity) throws Exception {
		return this.dao.doCreate(entity)  ;
	}

	@Override
	public Integer doUpdate(T entity) throws Exception {
		return this.dao.doUpdate(entity)   ;
	}

	@Override
	public Integer doRemove(ID id) throws Exception {
		return this.dao.doRemove(id)   ;
	}

	@Override
	public Integer doBatchCreate(List<T> entities) throws Exception {
		return this.dao.doBatchCreate(entities)    ;
	}

	@Override
	public T findByID(ID id) throws Exception {
		return this.dao.findByID(id)   ;
	}

	@Override
	public Map<String,Object> findAll(Integer currentPage, Integer lineSize,
			                          String colName, String keyWord) throws Exception {
		Map<String,Object> result=new HashMap<>() ;   //保存结果集
		keyWord=genDBKeyWord(keyWord)  ;             //生成数据库查询时候的关键词
        PageHelper.startPage(currentPage, lineSize)   ;   //使用分页工具
        Map<String,Object> map=new HashMap<>()  ;  //保存分页必备信息:colNames、keyWord
        map.put("colName", colName)  ;
        map.put("keyWord", keyWord)  ;
        List<T> data=this.dao.findAll(map)  ;  //查询
        PageInfo<T> pageInfo=new PageInfo<>(data)   ;    //分页信息
        result.put("data", data)  ;  //放入数据
        result.put("pageInfo", pageInfo) ;   //放入分页信息 
		return result   ;
	}

}
