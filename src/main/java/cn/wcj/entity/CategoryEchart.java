package cn.wcj.entity;
/**
 * 
 * @author ZZU·WangCJ
 * 
 * 意义:生成饼状图的每一个饼块
 *
 *
 *
 */
public class CategoryEchart {

	private String categoryName  ;   //类别名称
	private Double income   ;   //收入
	
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
