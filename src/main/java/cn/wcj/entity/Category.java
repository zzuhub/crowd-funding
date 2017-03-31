package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 众筹项目
 * 
 * 
 * 众筹类别
 * 
 * @author WangCJ
 *
 *
 *
 *
 */
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id   ;         //类别ID
	private String name   ;       //类别名称 
	private List<Project> projects=new ArrayList<>() ;   //1个类别有多个项目
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", projects="
				+ projects + "]";
	}

	
	
	
  	
	
	
	
}
