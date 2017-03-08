package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * ��ɫʵ��
 * 
 * 
 *@author WangCJ
 *
 *
 *@since 2017-03-08 08:32:37
 *
 *��������Ҫ���Role-Permission֮���Զ�Ĺ�ϵӳ��
 *
 *
 *�����toStrng()����û�κ�����,������Ԫ����ʱʹ��
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 7758258L;
	
	private Integer roleId    ;
	
	private String name   ;
	
	private String note   ;
	
	//���ϻ��ǳ�ʼ��һ�º�,��ΪҪ���ÿ�ָ��ķ��� 
	private List<Permission> permissions=new ArrayList<Permission>()  ;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", note=" + note
				+ ", permissions=" + permissions + "]";
	}
	
	
	
	
	
	
	

}
