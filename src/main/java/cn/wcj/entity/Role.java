package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 角色实体
 * 
 * 
 *@author WangCJ
 *
 *
 *@since 2017-03-08 08:32:37
 *
 *在这里需要完成Role-Permission之间多对多的关系映射
 *
 *
 *这里的toStrng()方法没任何意义,仅仅单元测试时使用
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 7758258L;
	
	private Integer roleId    ;
	
	private String name   ;
	
	private String note   ;
	
	//集合还是初始化一下好,因为要放置空指针的发生 
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
