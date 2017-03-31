package cn.wcj.handler;

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

import cn.wcj.entity.Category;
import cn.wcj.service.ICategoryService;
import cn.wcj.util.Contants;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("unchecked")
@RequestMapping("/Category")
@Controller
public class CategoryHandler implements IBaseHandler<Category> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ICategoryService categoryService   ;

	@RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Override
	public String findAll(@RequestParam("cp")Integer currentPage, 
			              @RequestParam("ls")Integer lineSize, 
			              @RequestParam("kw")String keyWord)
			                                         throws Exception {
		Map<String, Object> map = this.categoryService.findAll(currentPage, lineSize, keyWord);  //��ҳ+ģ����ѯ
		PageInfo<Category> pageInfo=(PageInfo<Category>) map.get("pageInfo") ;  //��ҳ��Ϣ
		List<Category> categories=(List<Category>) map.get("data") ;   //����
		JSONObject jsonObj=new JSONObject()  ;  //��������JSON����
		jsonObj.put("allRecords", pageInfo.getTotal())   ;
		jsonObj.put("kw", keyWord)   ;   //�ش��ؼ���
		jsonObj.put("currentSize", pageInfo.getSize())  ;  //��ǰҳ������
		//��Ϊҵ���漰��ʱ��洢��JSON,�����ֹ�����ÿһ��JSON,��������ǰ̨AJAXҪ�������
		JSONArray array=new JSONArray()  ;
		for(Category category : categories){
			JSONObject obj=new JSONObject()   ;
			obj.put("id", category.getId())   ;
			obj.put("name", category.getName())  ;
			obj.put("projectSize", category.getProjects().size())   ;   //��Ŀ���� 
			array.add(obj)   ;  //��JSON���鱣��JSON���� 
		}
		jsonObj.put("allCategories", array)  ;
		return jsonObj.toString() ;
	}
	
	private static final String LIST_PAGE="category/category_list" ;   //�б�ҳ
	
	@RequestMapping("/toListPage")
	public String toListPage(){
		   return LIST_PAGE ;
	}
	
	private static final String DETAIL_PAGE="category/category_detail" ;  //ϸ��ҳ
	
	
	@RequestMapping("/toDetailPage/{id}")
	public ModelAndView toDetailPage(@PathVariable("id")Integer id)throws Exception{
		   ModelAndView detailView=new ModelAndView(DETAIL_PAGE) ;  
		   detailView.addObject("category", this.categoryService.findByID(id)) ;
		   return detailView   ;
	}
	
	private static final String INSERT_PAGE="category/category_insert"   ;
	
	@RequestMapping("/toInsertPage")
	public String toInsertPage()throws Exception{
		   return INSERT_PAGE   ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findByName",produces = "text/html;charset=UTF-8")
	public String findByName(@RequestParam("name")String name)throws Exception{
		  return this.categoryService.findByName(name)==null?Contants.FAILURE_STATUS_JSON:Contants.SUCCESS_STATUS_JSON   ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doCreate",produces = "text/html;charset=UTF-8")
	public String doCreate(@RequestParam("name")String name)throws Exception{
		   Category category=new Category()  ;   //��� 
		   category.setName(name)  ;
		   return this.categoryService.doCreate(category)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON   ;
	}
	
	private static final String EDIT_PAGE="category/category_edit" ;   //�༭ҳ�� 
	
	@RequestMapping("/toEditPage/{id}")
	public ModelAndView toEditPage(@PathVariable("id")Integer id)throws Exception{
		   ModelAndView editView=new ModelAndView(EDIT_PAGE) ;
		   editView.addObject("category", this.categoryService.findByID(id)) ;   //��Service���ѯ����
		   return  editView   ;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/findById",produces = "text/html;charset=UTF-8")
	public String findById(@RequestParam("id")Integer id)throws Exception{
		   return JSONObject.fromObject(this.categoryService.findByID(id)).toString()   ;  //����JSON���͵�����   
	}
	
	@ResponseBody
	@RequestMapping(value="/doUpdate",produces = "text/html;charset=UTF-8")
	public String doUpdate(@RequestParam("id")Integer id,
			               @RequestParam("name")String name)throws Exception{
           Category category=new Category()  ;
           category.setId(id);
           category.setName(name);
           return this.categoryService.doUpdate(category)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doRemove",produces = "text/html;charset=UTF-8")
	public String doRemove(@RequestParam("id")Integer id)throws Exception{
		   return this.categoryService.doRemove(id)>=0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON   ;
	}
	
	

}
