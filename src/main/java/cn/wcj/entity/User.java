package cn.wcj.entity;

import java.io.Serializable;
import java.util.Date;
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
     
	private Integer userId   ;    //�û����
	private String name ;        //����
	private String password ;    //����(Controller������ܺ�����뵽Service��)
	private Date lastLogin ;    //�ϴε�¼ʱ��
	private Integer sttatus   ; //״̬:  1-->����  0--->����
	private Integer logingErr  ; //��¼�������,���������=4��ʱ�������
	private Date createTime=new Date()   ; //����ʱ��
	private String lastIP     ; //�ϴε�¼��IP
	
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
	
	public Integer getSttatus() {
		return sttatus;
	}
	
	public void setSttatus(Integer sttatus) {
		this.sttatus = sttatus;
	}
	
	public Integer getLogingErr() {
		return logingErr;
	}
	
	public void setLogingErr(Integer logingErr) {
		this.logingErr = logingErr;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password="
				+ password + ", lastLogin=" + lastLogin + ", sttatus="
				+ sttatus + ", logingErr=" + logingErr + ", createTime="
				+ createTime + ", lastIP=" + lastIP + "]";
	}
	
	
	
	
	
	
}
