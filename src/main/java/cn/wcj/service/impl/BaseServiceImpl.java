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
 * ��������ģʽ�Ǹ��������ʦѧ��
 * @author WangCJ
 *
 * @param <T>   ʵ������
 * @param <ID>  ��������
 * 
 * 
 * @since 2017-03-07 09:27:54
 * 
 */
public abstract class BaseServiceImpl<T,ID> implements IBaseService<T, ID> {

	private IBaseDAO<T, ID>  dao  ;     //ͨ��DAO 
	
	

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
		Map<String,Object> result=new HashMap<>() ;   //��������
		keyWord=genDBKeyWord(keyWord)  ;             //�������ݿ��ѯʱ��Ĺؼ���
        PageHelper.startPage(currentPage, lineSize)   ;   //ʹ�÷�ҳ����
        Map<String,Object> map=new HashMap<>()  ;  //�����ҳ�ر���Ϣ:colNames��keyWord
        map.put("colName", colName)  ;
        map.put("keyWord", keyWord)  ;
        List<T> data=this.dao.findAll(map)  ;  //��ѯ
        PageInfo<T> pageInfo=new PageInfo<>(data)   ;    //��ҳ��Ϣ
        result.put("data", data)  ;  //��������
        result.put("pageInfo", pageInfo) ;   //�����ҳ��Ϣ 
		return result   ;
	}

}
