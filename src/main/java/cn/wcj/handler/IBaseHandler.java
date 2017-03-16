package cn.wcj.handler;

import java.io.Serializable;


/**
 * 
 * ���Ʋ㷽������
 * 
 * @author WangCJ
 *
 *
 *��Ϊ���Ʋ�ÿ��CRUD��������ͨ��,���Բ��ٽ���ϸ���ȵĻ���
 *
 */
public interface IBaseHandler<T> extends Serializable {

	//���¶��򵽵�¼ҳ
	public static final String REDIRECT_LOGIN_PAGE="redirect:/login.jsp"  ;
    
	//��ҳ
	public static final String INDEX_PAGE="main/index"  ;
	
	/**
	 * 
	 * @param currentPage ��ǰҳ
	 * @param lineSize ÿҳ��ʾ������
	 * @param keyWord  �����ؼ���
	 * @return  JSON����
	 * @throws Exception  �쳣ֱ���׳� �����쳣AOPͳһ����
	 */
	String findAll(Integer currentPage,
                          Integer lineSize,
                          String keyWord)throws Exception ;
	
	
}
