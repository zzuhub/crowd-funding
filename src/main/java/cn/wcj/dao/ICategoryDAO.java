package cn.wcj.dao;



import org.springframework.stereotype.Repository;

import cn.wcj.entity.Category;
/**
 * 
 * ���DAO�� 
 * 
 * @author WangCJ
 *
 *
 *
 */
@Repository("categoryDAO")
public interface ICategoryDAO extends IBaseDAO<Category, Integer> {

	 //�������Ʋ�ѯ����,��Ҫ�ǽ���AJAX�����������֤ 
	 Category findByName(String name)throws Exception  ;
	

}
