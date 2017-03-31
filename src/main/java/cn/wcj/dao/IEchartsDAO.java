package cn.wcj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.CategoryEchart;

/**
 * 
 *百度Echarts图表DAO层
 *
 * 这个DAO层跟别的DAO层不一样,不需要继承IBaseDAO
 * 
 * 主要完成MySQL数据统计
 * 
 * 返回给控制层
 * 
 * 方便JQuery-AJAX调用Controller层完成百度ECharts图表显示
 * 
 * @author ZZU·WangCJ
 *
 * @since 2017-03-31 10:15:06
 *
 */
@Repository("echartsDAO")
public interface IEchartsDAO {

	List<CategoryEchart> findCategoryEcharts()throws Exception  ;
	
}
