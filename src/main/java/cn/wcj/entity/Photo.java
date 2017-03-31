package cn.wcj.entity;

import java.io.Serializable;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1L ;
	
	private Integer photoId   ;   //照片ID
	private String name   ;  
	private Project project  ;   //1张照片对应1个工程
	
	public Integer getPhotoId() {
		return photoId;
	}
	
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Photo [photoId=" + photoId + ", name=" + name + ", project="
				+ project + "]";
	}
	
	
	
	

}
