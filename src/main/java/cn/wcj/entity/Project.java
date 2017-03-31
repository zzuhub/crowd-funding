package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer projectId   ;
	private Category category   ;    //1个项目对应1个类别
	private User user ;              //1个项目对应1个用户
	private Double expectMoney =0.0 ;   //希望资金
	private Double realMoney=0.0    ;  //真实资金
	private String note         ;  //说明
	private String tel          ; //联系方式
	private Integer status=1      ;  //状态,0完成 1未完成
	private String title   ;       //标题
	private List<Photo> photos=new ArrayList<>() ;   //1个项目有多张图片
	private List<Comment> comments=new ArrayList<>() ; //1个项目有多条评论
	
	public Integer getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Double getExpectMoney() {
		return expectMoney;
	}
	
	public void setExpectMoney(Double expectMoney) {
		this.expectMoney = expectMoney;
	}
	
	public Double getRealMoney() {
		return realMoney;
	}
	
	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Photo> getPhotos() {
		return photos;
	}
	
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", category=" + category
				+ ", user=" + user + ", expectMoney=" + expectMoney
				+ ", realMoney=" + realMoney + ", note=" + note + ", tel="
				+ tel + ", status=" + status + ", title=" + title + ", photos="
				+ photos + ", comments=" + comments + "]";
	}
	
	
	
	
	
	
	
	
	

}
