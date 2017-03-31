package cn.wcj.dao;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.Comment;

@Repository("commentDAO")
public interface ICommentDAO extends IBaseDAO<Comment, Integer> {}
