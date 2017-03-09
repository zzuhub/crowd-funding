package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Permission implements Serializable{

	private static final long serialVersionUID = 1L ;
	
	private Integer permissionId  ;   //Ȩ�ޱ��
	
	private String name   ;  //Ȩ��Ӣ����,����ҳ�����
	
	private String note   ;   //Ȩ��������,�������
	

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
