package cn.wcj.entity;
/**
 * 
 * @author ZZU��WangCJ
 * 
 * ����:���ɱ�״ͼ��ÿһ������
 *
 *
 *
 */
public class CategoryEchart {

	private String categoryName  ;   //�������
	private Double income   ;   //����
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Double getIncome() {
		return income;
	}
	
	public void setIncome(Double income) {
		this.income = income;
	}

	@Override
	public String toString() {
		return "CategoryEchart [categoryName=" + categoryName + ", income="
				+ income + "]";
	}
	
}
