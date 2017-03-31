package cn.wcj.entity;

import java.io.Serializable;
import java.util.Date;

public class Approve implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer approveId    ;     //֧�ֱ��
	
	private String name ;              //֧��������
	
	private String tel  ;              //֧���ߵ绰
	
	private Double money=0d ;            //֧���ʽ�
	
	private Date approveTime=new Date() ;       //֧��ʱ��
	
	private Project project ;         //1��֧�ֶ�Ӧ1����Ŀ

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
