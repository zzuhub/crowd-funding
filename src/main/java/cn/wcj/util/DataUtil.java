package cn.wcj.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 
 * ���ݴ�������
 * 
 * @author WangCJ
 * 
 * @since 2017-03-07 10:32:45
 * 
 * ���з������þ�̬,��ʹ�����ע���ʶ,�������
 *
 *�������ܷ���
 *
 *
 *
 */
public class DataUtil {

	public static boolean isNull(String data)throws Exception{
		   return data==null || "".equals(data)   ;
	}
	
	public static String genDBKeyWord(String keyWord)throws Exception{
		   String result="%%"  ;   //Ĭ�Ϲؼ����ǲ�ѯȫ��
		   if(!isNull(keyWord))   //����ؼ��ʲ�Ϊ��
			      result="%"+keyWord+"%"  ;   //��֯�ؼ���
		   return result   ;   //�������ɵĽ��
	}
	
	/**
	 * ��ֵ���ܷ�ʽ
	 * @param algorithmName �����㷨����
	 * @param source ��Ҫ���ܵ��ִ�
	 * @param salt ��ֵ
	 * @param hashIterations  ���ܴ���
	 * @return
	 */
	public static  String encrypt(String algorithmName,String source ,String salt,Integer hashIterations){
		   String result=null ;
		   Object target = new SimpleHash(algorithmName, source, salt, hashIterations) ;  //��ֵ���ܺ󷵻ض���
		   result=target.toString()  ;   //�õ��ַ�����ʽ���ܺ������
		   return result  ;  //���ؼ��ܽ�� 
	}
	
	
	
}
