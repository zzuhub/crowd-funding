package cn.wcj.service.impl;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IBaseDAO;
import cn.wcj.dao.ICommentDAO;
import cn.wcj.entity.Comment;
import cn.wcj.service.ICommentService;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, java.lang.Integer> 
                                                            implements ICommentService {
   
	 @Autowired
	 private ICommentDAO commentDAO   ;

	//注入特定的DAO层方便调用 
	@Resource(name="commentDAO")
	@Override
	public void setDao(IBaseDAO<Comment, Integer> dao) {
		    super.setDao(dao);
	}
	
	
	 
	 
	 

}
