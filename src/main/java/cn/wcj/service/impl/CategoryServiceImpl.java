package cn.wcj.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.ICategoryDAO;
import cn.wcj.entity.Category;
import cn.wcj.service.ICategoryService;

@Service("categoryService")
public class CategoryServiceImpl 
                            extends BaseServiceImpl<Category, Integer>
		                    implements ICategoryService {
    @Autowired   
	private ICategoryDAO categoryDAO  ;

    //¸ÄÐ´DAO×é¼þ
    @Resource(name="categoryDAO")
	@Override
	public void setDao(IBaseDAO<Category, Integer> dao) {
		 super.setDao(dao);
	}

	@Override
	public Category findByName(String name) throws Exception {
		return categoryDAO.findByName(name) ;
	}
    
    
    
}
