package cn.wcj.quartz.job;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import cn.wcj.entity.Photo;
import cn.wcj.service.IPhotoService;
import cn.wcj.util.Contants;

/**
 * 
 * @author SoftwareCollege Of ZhengZhou University -ChengJian��Wang
 * @since 2017-03-28 09:37:55
 * �������û��ʵ��Job�ӿ�,��϶Ⱥܵ�,Spring����Quartzʱ��Ҫʹ����������
 *
 */
@Component
@SuppressWarnings("unchecked")
public class PhotoJob {

	@Autowired
	private IPhotoService photoService   ;

	/**
	 * ���ǵ�Ҫȥ���ظ�,��Ϊ���д�����nophoto.jpg
	 * @return ���ݿ���ͼƬ������ 
	 * @throws Exception
	 */
	public Set<String> findAll()throws Exception{
		   List<Photo> photos=(List<Photo>) this.photoService.findAll(1, 0, "").get("data") ;  //���ҵ�ȫ��ͼƬ  
		   Set<String> photoNameSet=new HashSet<>()  ;  //�洢ͼƬ����
		   for(Photo photo : photos)
			   photoNameSet.add(photo.getName())   ;    //����ͼƬ����
		   return photoNameSet   ;   //����ͼƬ�����б� 
	}
	
	
	public String findPath()throws Exception{
		  String path=null ;
		  String localPath = this.getClass().getResource("/").getPath();
		  String targetPath=localPath.substring(0, localPath.indexOf(Contants.PROJECT_NAME)+Contants.PROJECT_NAME.length()+1); 
		  path=targetPath+"upload"   ;
		  return path ;
	}
	
	public void clear()throws Exception {
		String path=this.findPath()  ;  //ȡ��photo·��
		File file=new File(path)   ;      //�ļ���
		String[] list = file.list() ;   //ȫ��ͼƬ�ļ�
		//������Ҫ����ע���ҵķ�װ��ʽ,��Ȼ�����ױ�java.lang.UnsupportedOperationException�쳣
		List<String> all=new ArrayList<>(Arrays.asList(list))  ;    //ȫ��ͼƬ,�����Ѿ�ʧЧ��ͼƬ
		Set<String> set=this.findAll()  ;   //����ʹ�õ�ͼƬ
		List<String> useful=new ArrayList<>(set);
		boolean flag=all.removeAll(useful)   ;
		if(flag){  //�Ƴ�ȫ���е�û�õ�ͼƬ,����Ƴ��ɹ��Ϳ�ʼɾ��ʧ����ͼƬ
			for(String fileName : all){
				 File delFile=new File(path+File.separator+fileName)   ;  //ɾ���ļ�
				 delFile.delete()   ;   //ɾ���ļ�
			}
				
		}
	}
	
	
}
