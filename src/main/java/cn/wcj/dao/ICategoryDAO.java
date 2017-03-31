package cn.wcj.dao;



import org.springframework.stereotype.Repository;

import cn.wcj.entity.Category;
/**
 * 
 * 类别DAO层 
 * 
 * @author WangCJ
 *
 *
 *
 */
@Repository("categoryDAO")
public interface ICategoryDAO extends IBaseDAO<Category, Integer> {

	 //根据名称查询种类,主要是交给AJAX做类别重名验证 
	 Category findByName(String name)throws Exception  ;
	

}
