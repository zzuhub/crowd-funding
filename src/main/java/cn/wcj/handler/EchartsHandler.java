package cn.wcj.handler;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wcj.entity.CategoryEchart;
import cn.wcj.service.IEchartsService;

@RequestMapping("/Echarts")
@Controller
public class EchartsHandler {

	@Autowired
	private IEchartsService echartsService   ;    //百度ECharts服务层 
	
	private static final String CATEGORY_ECHARTS_PAGE="echarts/category_echarts"  ;
	
	@RequestMapping("/toCategoryEchartsPage")
	public String toCategoryEchartsPage()throws Exception{
		   return CATEGORY_ECHARTS_PAGE  ;   //页面只需编写好AJAX即可
	}
	
	@ResponseBody
	@RequestMapping(value="/findCategoryEcharts",produces = "text/html;charset=UTF-8")
	public String findCategoryEcharts()throws Exception{
		   JSONObject all=new JSONObject()  ;
		   JSONArray legend=new JSONArray()  ;   //name
		   JSONArray data=new JSONArray()   ;  //保存{name:"xx",value:""}
		   List<CategoryEchart> categoryEcharts = this.echartsService.findCategoryEcharts();
		   for(CategoryEchart categoryEchart:categoryEcharts){
			   legend.add(categoryEchart.getCategoryName())  ;   //保存name
			   //保存{name:"xx",value:""}
			   JSONObject nv=new JSONObject()  ; 
			   nv.put("name", categoryEchart.getCategoryName()) ;  //保存name
			   nv.put("value", categoryEchart.getIncome()) ; //保存value
			   data.add(nv)  ;
		   }
		   all.put("legend", legend)  ;
		   all.put("data", data)  ;
		   System.out.println("********************"+all.toString());
		   return all.toString() ;
	}
	
}
