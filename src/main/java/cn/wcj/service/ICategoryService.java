package cn.wcj.service;

import cn.wcj.entity.Category;

public interface ICategoryService extends IBaseService<Category, Integer> {

	Category findByName(String name)throws Exception   ;
	
	
}
