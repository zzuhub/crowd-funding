package cn.wcj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 用户实体
 * 
 * 这是最核心的类,写的时候要注意
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
	
	private Integer userId   ;    //用户编号
	private String name ;        //名称
	private String password ;    //密码(Controller传入加密后的密码到Service层)
	private Date lastLogin ;    //上次登录时间
	private Integer status=1   ; //状态:  1-->正常  0--->锁定
	private Integer loginErr=0  ; //登录密码错误,当错误次数=4的时候就锁定
	private Date createTime=new Date()   ; //创建时间
	private String lastIP     ; //上次登录的IP
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
