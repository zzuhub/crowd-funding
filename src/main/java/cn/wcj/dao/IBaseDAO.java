package cn.wcj.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author WangCJ
 *����DAO�����ӿ�,������չ
 * @param <T> ʵ������
 * @param <ID> ����
 * 
 * 1.�������DAO�ӿ��Ǹ�����MyBatis��DAO�ӿڼ̳�
 * 
 * 2.��������๫��CRUD����
 * 
 * 3.���ͼ������ӷ�����ͨ����
 * 
 * @since 2017-03-06 21:35:03
 * 
 * 
 */
public interface IBaseDAO<T,ID> extends Serializable{

	//=================================================do**��ʾд����start===========================================
	
	Integer doCreate(T entity)throws Exception    ;  
	
	Integer doUpdate(T entity)throws Exception     ;  
	
	Integer doRemove(ID id)throws Exception    ;
	
	//��������,����ʹ����MySQLҪ����С������MyBatis��̬����SQL
	Integer doBatchCreate(List<T> entities)throws Exception   ;
	
	//===================================================do**��ʾд����end===========================================
	
	
	//=================================================find**��ʾ������end===========================================
	
	T findByID(ID id)throws Exception   ;

	//���������͹ؼ��ʲ���,����Ҫ��ҳ��ʱ��,��������MyBatisPageHelper������ɷ�ҳ��ʾ
	//String colName,String keyWord
	List<T> findAll(Map<String,Object> map)throws Exception   ;
	
	//===================================================find**��ʾ������end===========================================
	
	
	
	
	
	
	
}
