package cn.wcj.util;

import java.io.Serializable;

/**
 * 
 * ����һ���ӿ�,ר�ű�����Ŀ���õĳ���,�����
 * 
 * @author WangCJ
 *
 *
 *@since 2017-03-10 22:47:42
 *
 *
 */
public interface Contants extends Serializable {

	 //�����㷨
	
	 public static final String ALGORITHM_MD5="md5"   ;   
	
	 public static final String ALGORITHM_SHA1="sha1"   ;  
	
	 public static final Integer COMMON_HASH_ITERATIONS=1024  ;   //ͨ�ü��ܴ���
	 
	 //���������ļ�������
	 public static final String FILTER_CHAIN_FILE="filterChain.properties"   ;
	 
	 //AJAX����SpringMVC�ش�JSON��ʽ��״̬��,��������״̬
	 public static final String SUCCESS_STATUS_JSON="{\"status\":\"1\"}" ;
	 public static final String FAILURE_STATUS_JSON="{\"status\":\"0\"}"  ;
	 
	 
}
