package cn.wcj.entity;

import java.io.Serializable;
import java.util.Date;

public class Approve implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer approveId    ;     //支持编号
	
	private String name ;              //支持者名称
	
	private String tel  ;              //支持者电话
	
	private Double money=0d ;            //支持资金
	
	private Date approveTime=new Date() ;       //支持时间
	
	private Project project ;         //1个支持对应1个项目

	public Integer getApproveId() {
		return approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Approve [approveId=" + approveId + ", name=" + name + ", tel="
				+ tel + ", money=" + money + ", approveTime=" + approveTime
				+ ", project=" + project + "]";
	}
	
	
	
	

}
