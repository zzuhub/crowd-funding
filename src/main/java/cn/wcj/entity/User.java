package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * �û�ʵ��
 * 
 * ��������ĵ���,д��ʱ��Ҫע��
 * 
 * @author WangCJ
 * 
 * 
 * @since 2017-03-08 22:11:58 
 * 
 *
 */
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private Integer userId   ;    //�û����
	private String name ;        //����
	private String password ;    //����(Controller������ܺ�����뵽Service��)
	private Date lastLogin ;    //�ϴε�¼ʱ��
	private Integer status=1   ; //״̬:  1-->����  0--->����
	private Integer loginErr=0  ; //��¼�������,���������=4��ʱ�������
	private Date createTime=new Date()   ; //����ʱ��
	private String lastIP     ; //�ϴε�¼��IP
	private List<Role> roles=new ArrayList<>() ;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLoginErr() {
		return loginErr;
	}
	public void setLoginErr(Integer loginErr) {
		this.loginErr = loginErr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password="
				+ password + ", lastLogin=" + lastLogin + ", status=" + status
				+ ", loginErr=" + loginErr + ", createTime=" + createTime
				+ ", lastIP=" + lastIP + ", roles=" + roles + "]";
	}
	
	
	
	
	
	
	
}
