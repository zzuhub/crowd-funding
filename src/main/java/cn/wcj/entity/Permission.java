package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Permission implements Serializable{

	private static final long serialVersionUID = 1L ;
	
	private Integer permissionId  ;   //权限编号
	
	private String name   ;  //权限英文名,方便页面控制
	
	private String note   ;   //权限中文名,方便理解
	

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", name=" + name
				+ ", note=" + note + "]";
	}


	
	
	
	

}
