package cn.wcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wcj.dao.IEchartsDAO;
import cn.wcj.entity.CategoryEchart;
import cn.wcj.service.IEchartsService;

@Service("echartsService")
public class EchartsServiceImpl implements IEchartsService {

	@Autowired
	private IEchartsDAO echartsDAO   ;
	
	@Override
	public List<CategoryEchart> findCategoryEcharts() throws Exception {
		return this.echartsDAO.findCategoryEcharts()  ;
	}

}
