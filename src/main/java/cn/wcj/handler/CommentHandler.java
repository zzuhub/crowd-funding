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

import cn.wcj.entity.Comment;
import cn.wcj.service.ICommentService;
import cn.wcj.util.Contants;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/Comment")
public class CommentHandler implements IBaseHandler<Comment>{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICommentService commentService   ;
	
	private static final String LIST_PAGE="comment/comment_list" ;
	
	@RequestMapping("/toListPage")
	public String toListPage()throws Exception{
		   return LIST_PAGE  ;
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/findAll",produces = "text/html;charset=UTF-8")
	public String findAll(@RequestParam("cp")Integer currentPage, 
			              @RequestParam("ls")Integer lineSize, 
			              @RequestParam("kw")String keyWord) throws Exception {
		  Map<String, Object> map = this.commentService.findAll(currentPage, lineSize, keyWord); 
		  PageInfo<Comment> pageInfo = (PageInfo<Comment>) map.get("pageInfo") ;  //分页信息
		  List<Comment> comments=(List<Comment>) map.get("data")  ;   //评论信息
		  JSONObject all=new JSONObject() ;   //保存最终的JSON字符串
		  all.put("allRecords", pageInfo.getTotal())   ;
	      all.put("kw", keyWord)   ;   //回传关键词
	      all.put("currentSize", pageInfo.getSize())  ;  //当前页数据量
	      //因为业务涉及到时间存储到JSON,必须手工配置每一个JSON,返回满足前台AJAX要求的数据
	      JSONArray array=new JSONArray()       ;
	      for(Comment comment : comments){
	    	  JSONObject obj=new JSONObject()   ;    //保存每一个JSON对象
	    	  obj.put("commentId", comment.getCommentId())  ;
	    	  obj.put("userName", comment.getUser().getName())   ;
	    	  obj.put("projectTitle", comment.getProject().getTitle())    ;
	    	  obj.put("content",comment.getContent())   ;
	    	  array.add(obj)  ;
	      }
	      all.put("allComments", array)   ;
		  return all.toString()   ;
	}
	
	
	private static final String DETAIL_PAGE="comment/comment_detail"  ;
	
	@RequestMapping("/toDetailPage/{commentId}")
	public ModelAndView toDetailPage(@PathVariable("commentId")Integer commentId)throws Exception{
		   return new ModelAndView(DETAIL_PAGE).addObject("comment", this.commentService.findByID(commentId))   ;
	}
	
	private static final String INSERT_PAGE="comment/comment_insert"  ;
	
	@RequestMapping("/toInsertPage")
	public String toInsertPage()throws Exception{
		   return INSERT_PAGE  ;
	}
	
	@ResponseBody
	@RequestMapping(value="/doCreate",produces = "text/html;charset=UTF-8")
	public String doCreate(Comment comment)throws Exception{
		   return this.commentService.doCreate(comment)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON  ;
	}
	
	private static final String EDIT_PAGE="comment/comment_edit"  ;   //修改页面 
	
	@RequestMapping("/toEditPage/{commentId}")
	public ModelAndView toEditPage(@PathVariable("commentId")Integer commentId)throws Exception{
		 ModelAndView editView=new ModelAndView(EDIT_PAGE);
		 editView.addObject("comment", this.commentService.findByID(commentId))  ;
		 return editView   ;
	}
	
	@ResponseBody
	@RequestMapping(value="/findById",produces = "text/html;charset=UTF-8")
	public String findById(@RequestParam("commentId")Integer commentId)throws Exception{
		   System.out.println(JSONObject.fromObject(this.commentService.findByID(commentId)).toString())  ;
		   return JSONObject.fromObject(this.commentService.findByID(commentId)).toString()   ;
	}
	
	@ResponseBody
    @RequestMapping(value="/doUpdate",produces = "text/html;charset=UTF-8")
    public String doUpdate(Comment comment)throws Exception {
    	  return this.commentService.doUpdate(comment)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON;
    }
    
	@ResponseBody
	@RequestMapping(value="/doRemove",produces = "text/html;charset=UTF-8")
	public String doRemove(@RequestParam("commentId")Integer commentId)throws Exception{
		return this.commentService.doRemove(commentId)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON;
	}
	
	
	
}
