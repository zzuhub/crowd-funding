package cn.wcj.handler;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.wcj.entity.Approve;
import cn.wcj.service.IApproveService;
import cn.wcj.util.Contants;

import com.github.pagehelper.PageInfo;

@RequestMapping("/Approve")
@Controller
@SuppressWarnings("unchecked")
public class ApproveHandler implements IBaseHandler<Approve> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IApproveService approveService   ;

	private static final String LIST_PAGE="approve/approve_list"  ;   //列表页 
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		  return LIST_PAGE  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	@Override
	public String findAll(@RequestParam("cp")Integer currentPage, 
			              @RequestParam("ls")Integer lineSize, 
			              @RequestParam("kw")String keyWord)
			                                               throws Exception {
		Map<String, Object> map = this.approveService.findAll(currentPage, lineSize, keyWord);  
		PageInfo<Approve> pageInfo=(PageInfo<Approve>) map.get("pageInfo") ;
		List<Approve> approves=(List<Approve>) map.get("data")  ;
		JSONObject jsonObj=new JSONObject()  ;
		jsonObj.put("allRecords", pageInfo.getTotal())   ;
		jsonObj.put("kw", keyWord)   ;   //回传关键词
		jsonObj.put("currentSize", pageInfo.getSize())  ;  //当前页数据量
		//因为业务涉及到时间存储到JSON,必须手工配置每一个JSON,返回满足前台AJAX要求的数据
		JSONArray array=new JSONArray()     ;
		for(Approve approve : approves){
			JSONObject obj=new JSONObject()               ;
			obj.put("approveId", approve.getApproveId())  ;
			obj.put("projectName", approve.getProject().getTitle())   ;
			obj.put("tel", approve.getTel())    ;
			obj.put("name", approve.getName())  ;
			obj.put("money", approve.getMoney())  ;
			obj.put("approveTime", new SimpleDateFormat("yyyy-MM-dd").format(approve.getApproveTime()))  ;
			array.add(obj)   ;  //放入数组
		}
		jsonObj.put("allApproves", array)  ;   //放入数据 
		return jsonObj.toString() ;
	}
	
	private static final String INSERT_PAGE="approve/approve_insert"   ;   //增加页面
	
	@RequestMapping("/toInsertPage")
	public String toInsertPage()throws Exception{
		   return INSERT_PAGE  ;
	}
	
	private static final String DETAIL_PAGE="approve/approve_detail"   ;   //详情页面
	
	
	@RequestMapping("/toDetailPage/{approveId}")
	public ModelAndView toDetailPage(@PathVariable("approveId")Integer approveId)throws Exception{
		   return new ModelAndView(DETAIL_PAGE).addObject("approve", this.approveService.findByID(approveId))  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doCreate",produces = "text/html;charset=UTF-8")
	public String doCreate(Approve approve)throws Exception{
		   return this.approveService.doCreate(approve)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
	}
	
	private static final String EDIT_PAGE="approve/approve_edit"   ;
	
	@RequestMapping("/toEditPage/{approveId}")
	public ModelAndView toEditPage(@PathVariable("approveId")Integer approveId)throws Exception{
		   return new ModelAndView(EDIT_PAGE).addObject("approve",this.approveService.findByID(approveId))   ;
	}
	
	@ResponseBody
	@RequestMapping(value="findById",produces = "text/html;charset=UTF-8")
	public String findById(@RequestParam("approveId")Integer approveId)throws Exception{
		   return JSONObject.fromObject(this.approveService.findByID(approveId)).toString()  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doUpdate",produces = "text/html;charset=UTF-8")
	public String doUpdate(Approve approve)throws Exception{
		   return this.approveService.doUpdate(approve)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON   ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doRemove",produces = "text/html;charset=UTF-8")
	public String doRemove(@RequestParam("approveId")Integer approveId)throws Exception{
		   return this.approveService.doRemove(approveId)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
	}
	
	//游客筹资页面
	private static final String GUSET_APPROVE_INSERT_PAGE="guest/guest_approve_insert" ;   //游客增加页面,带参
	
	@RequestMapping("/toGuestInsertPage/{projectId}")
	public ModelAndView toGuestInsertPage(@PathVariable("projectId")Integer projectId)throws Exception{
		   return new ModelAndView(GUSET_APPROVE_INSERT_PAGE).addObject("projectId", projectId)   ;
	}
	
	
	

}
