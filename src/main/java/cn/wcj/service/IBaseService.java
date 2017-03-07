package cn.wcj.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author WangCJ
 *
 * @param <T> ʵ������
 * @param <ID> ��������
 * 
 * @since 2017-03-07 09:05:18
 * 
 * 1.����ӿڳ�������ȫ��ͨ�ò���
 * 
 * 2.����Spring4.X�ṩ�ķ�������ע����Ը�Ư�������Service��,
 *   �Ͼ�DAO�㲻��̫��ķ�װ,ֻ��Ҫ�Ұ���ԭ���Ե�DAO����������,��
 *   ��ҵ��ĸ�ϸ�µĵ��ý���Service��
 * 
 * 
 * 
 * 
 */
public interface IBaseService<T,ID> {

	//=================================================do**��ʾд����start===========================================
	
	Integer doCreate(T entity)throws Exception    ;  
	
	Integer doUpdate(T entity)throws Exception     ;  
	
	Integer doRemove(ID id)throws Exception    ;
	
	//��������,����ʹ����MySQLҪ����С������MyBatis��̬����SQL
	Integer doBatchCreate(List<T> entities)throws Exception   ;
	
	//===================================================do**��ʾд����end===========================================
	
	
	//=================================================find**��ʾ������end===========================================
	
	T findByID(ID id)throws Exception   ;

	/**
	 * 
	 * @param currentPage ��ǰҳ
	 * @param lineSize    ÿҳ��ʾ������
	 * @param colNames    ��������
	 * @param keyWord     �����ؼ���
	 * @return            ���ϼ���������ʵ�弯�Ϻͷ�ҳ����(KV��ֵ�Ե���ʽ)
	 * @throws Exception  �쳣ֱ���׳�,���ǻῪ��AOP��ʽ���쳣����ʽ
	 */
	Map<String,Object> findAll(Integer currentPage,Integer lineSize ,String colName,String keyWord)throws Exception   ;
	
	//===================================================find**��ʾ������end===========================================
	
	
}
