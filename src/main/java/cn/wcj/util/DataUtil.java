package cn.wcj.util;

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
	
	
}
