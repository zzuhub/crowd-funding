package cn.wcj.service.impl;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.IPhotoDAO;
import cn.wcj.entity.Photo;
import cn.wcj.service.IPhotoService;

@Service("photoService")
public class PhotoServiceImpl extends BaseServiceImpl<Photo, Integer> implements
		IPhotoService {
 
	@Autowired
	private IPhotoDAO photoDAO   ;

	//�����DAOע���ض���DAO����,��Ҫ��Ϊ�˷����װͨ�õ�CRUD����
	@Resource(name="photoDAO")
	@Override
	public void setDao(IBaseDAO<Photo, Integer> dao) {
		  super.setDao(dao);
	}
	
	
	
	
	
	

}
