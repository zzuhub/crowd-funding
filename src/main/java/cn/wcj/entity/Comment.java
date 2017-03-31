package cn.wcj.entity;

import java.io.Serializable;

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer commentId ;  //评论ID
	private User user ;   //1条评论对应1个用户
	private Project project ;  //1条评论对应1个项目
	private String content ;   //评论内容
	
	public Integer getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", user=" + user
				+ ", project=" + project + ", content=" + content + "]";
	}
	
	
	
    
	
	
	
	

}
