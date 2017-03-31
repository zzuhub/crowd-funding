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
 * @author SoftwareCollege Of ZhengZhou University -ChengJian·Wang
 * @since 2017-03-28 09:37:55
 * 这个任务没有实现Job接口,耦合度很低,Spring整合Quartz时需要使用特殊配置
 *
 */
@Component
@SuppressWarnings("unchecked")
public class PhotoJob {

	@Autowired
	private IPhotoService photoService   ;

	/**
	 * 考虑到要去除重复,因为含有大量的nophoto.jpg
	 * @return 数据库中图片的名称 
	 * @throws Exception
	 */
	public Set<String> findAll()throws Exception{
		   List<Photo> photos=(List<Photo>) this.photoService.findAll(1, 0, "").get("data") ;  //查找到全部图片  
		   Set<String> photoNameSet=new HashSet<>()  ;  //存储图片名称
		   for(Photo photo : photos)
			   photoNameSet.add(photo.getName())   ;    //存入图片名称
		   return photoNameSet   ;   //返回图片名称列表 
	}
	
	
	public String findPath()throws Exception{
		  String path=null ;
		  String localPath = this.getClass().getResource("/").getPath();
		  String targetPath=localPath.substring(0, localPath.indexOf(Contants.PROJECT_NAME)+Contants.PROJECT_NAME.length()+1); 
		  path=targetPath+"upload"   ;
		  return path ;
	}
	
	public void clear()throws Exception {
		String path=this.findPath()  ;  //取得photo路径
		File file=new File(path)   ;      //文件夹
		String[] list = file.list() ;   //全部图片文件
		//这里需要格外注意我的封装方式,不然很容易爆java.lang.UnsupportedOperationException异常
		List<String> all=new ArrayList<>(Arrays.asList(list))  ;    //全部图片,包括已经失效的图片
		Set<String> set=this.findAll()  ;   //可以使用的图片
		List<String> useful=new ArrayList<>(set);
		boolean flag=all.removeAll(useful)   ;
		if(flag){  //移除全部中的没用的图片,如果移除成功就开始删除失联的图片
			for(String fileName : all){
				 File delFile=new File(path+File.separator+fileName)   ;  //删除文件
				 delFile.delete()   ;   //删除文件
			}
				
		}
	}
	
	
}
