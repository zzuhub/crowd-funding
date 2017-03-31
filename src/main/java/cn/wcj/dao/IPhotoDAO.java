package cn.wcj.dao;

import org.springframework.stereotype.Repository;

@Repository("photoDAO")
public interface IPhotoDAO extends  IBaseDAO<cn.wcj.entity.Photo,Integer> {}
