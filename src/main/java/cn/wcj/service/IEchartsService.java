package cn.wcj.service;

import java.util.List;

import cn.wcj.entity.CategoryEchart;

public interface IEchartsService {

	List<CategoryEchart> findCategoryEcharts()throws Exception  ;
	
}
