package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * �ڳ���Ŀ
 * 
 * 
 * �ڳ����
 * 
 * @author WangCJ
 *
 *
 *
 *
 */
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id   ;         //���ID
	private String name   ;       //������� 
	private List<Project> projects=new ArrayList<>() ;   //1������ж����Ŀ
	
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
