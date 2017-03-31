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

import com.github.pagehelper.PageInfo;

import cn.wcj.entity.Project;
import cn.wcj.service.ICategoryService;
import cn.wcj.service.IProjectService;
import cn.wcj.util.Contants;

@SuppressWarnings("unchecked")
@RequestMapping("/Project")
@Controller
public class ProjectHandler implements IBaseHandler<Project>{

	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private IProjectService projectService   ;
	
	@ResponseBody
	@RequestMapping(value="/findByUserId",produces = "text/html;charset=UTF-8")
	public String findByUserId(@RequestParam("userId")Integer userId)throws Exception{
		   return JSONArray.fromObject(projectService.findByUserID(userId)).toString()    ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findById",produces = "text/html;charset=UTF-8")
	public String findById(@RequestParam("projectId")Integer projectId)throws Exception{
		   return this.projectService.findByID(projectId)!=null?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON   ;
	}
	
	private static final String LIST_PAGE="project/project_list"   ;
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		   return LIST_PAGE   ;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	public String findAll(@RequestParam("cp")Integer currentPage, 
			              @RequestParam("ls")Integer lineSize, 
			              @RequestParam("kw")String keyWord)
			            		                       throws Exception{
		   JSONObject all=new JSONObject()   ;   //保存最终的JSON数据
		   Map<String, Object> map = this.projectService.findAll(currentPage, lineSize, keyWord);   //分页汇总
		   PageInfo<Project> pageInfo=(PageInfo<Project>) map.get("pageInfo") ;   //分页信息
		   List<Project> projects=(List<Project>) map.get("data")  ;    //项目列表
		   all.put("allRecords", pageInfo.getTotal())   ;
	       all.put("kw", keyWord)   ;   //回传关键词
	       all.put("currentSize", pageInfo.getSize())  ;  //当前页数据量
	       all.put("allProjects", projects)    ;   //存入项目列表 
		   return all.toString() ;
	}
	
	private static final String DETAIL_PAGE="project/project_detail" ;
	
	@RequestMapping("/toDetailPage/{projectId}")
	public ModelAndView toDetailPage(@PathVariable("projectId")Integer projectId)throws Exception{
		   return new ModelAndView(DETAIL_PAGE).addObject("project", this.projectService.findByID(projectId))  ;
	}
	
	private static final String INSERT_PAGE="project/project_insert" ;
	
	@Autowired
	private ICategoryService categoryService   ;
	
    @RequestMapping("/toInsertPage")
	public ModelAndView toInsertPage()throws Exception{
    	   return new ModelAndView(INSERT_PAGE)
    	                      .addObject("categories",this.categoryService.findAll(1, 0, "").get("data"))   ;
    }
    
    @ResponseBody
    @RequestMapping(value="/doCreate",produces = "text/html;charset=UTF-8")
    public String doCreate(Project project)throws Exception{
    	   return this.projectService.doCreate(project)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
    }
    
    private static final String EDIT_PAGE="project/project_edit"   ;    //编辑页面
    
    //到达编辑页面
    @ResponseBody
    @RequestMapping(value="/toEditPage/{projectId}",produces = "text/html;charset=UTF-8")
    public ModelAndView toEditPage(@PathVariable("projectId")Integer projectId)throws Exception{
    	   return new ModelAndView(EDIT_PAGE).addObject("project", this.projectService.findByID(projectId))   ;
    }
    
    @ResponseBody
    @RequestMapping(value="/findEntityById",produces = "text/html;charset=UTF-8")
    public String findEntityById(@RequestParam("projectId")Integer projectId)throws Exception{
           return JSONObject.fromObject(this.projectService.findByID(projectId)).toString()  ;
    }
    
    @ResponseBody
    @RequestMapping(value="/doUpdate",produces = "text/html;charset=UTF-8")
    public String doUpdate(Project project)throws Exception{
    	  return this.projectService.doUpdate(project)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
    }
    
    @ResponseBody
    @RequestMapping(value="/doRemove",produces = "text/html;charset=UTF-8")
    public String doRemove(@RequestParam("projectId")Integer projectId)throws Exception{
    	   //批量执行删除(因为关联太多),删除时结果>=0就表示删除成功,否则出错,AJAX有异常侦测机制
    	   return this.projectService.doRemove(projectId)>=0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
    }
    
    private static final String USER_PROJECT_LIST="user/project_list" ;    //用户自己的项目列表页 
    
    @RequestMapping("/toUserListPage/{userId}")
    public ModelAndView toUserListPage(@PathVariable("userId")Integer userId)throws Exception{
    	   return new ModelAndView(USER_PROJECT_LIST).addObject("projects", this.projectService.findByUserID(userId))  ;
    }
    
    private static final String USER_PROJECT_INSERT_PAGE="user/project_insert"   ;
    
    @RequestMapping("/toUserInsertPage/{userId}")
    public ModelAndView toUserInsertPage(@PathVariable("userId")Integer userId)throws Exception{
    	   return   new ModelAndView(USER_PROJECT_INSERT_PAGE).addObject("categories", this.categoryService.findAll(1, 0, "").get("data"));
    }
    
    private static final String USER_PROJECT_EDIT_PAGE="user/project_edit"  ;    //用户编辑项目页面
    
    @RequestMapping("/toUserEditPage/{projectId}")
	public ModelAndView toUserEditPage(@PathVariable("projectId")Integer projectId)throws Exception{
    	   return new ModelAndView(USER_PROJECT_EDIT_PAGE).addObject("project", this.projectService.findByID(projectId)) ;
    }
    
    private static final String GUEST_PROJECT_DETAIL_PAGE="guest/guest_project_detail";
    
    @RequestMapping("/toDetailPageByGuest/{projectId}")
    public ModelAndView toDetailPageByGuest(@PathVariable("projectId")Integer projectId)throws Exception{
    	  return new ModelAndView(GUEST_PROJECT_DETAIL_PAGE).addObject("project", this.projectService.findByID(projectId));
    }
    
    
}
