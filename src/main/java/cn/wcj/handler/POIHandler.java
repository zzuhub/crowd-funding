package cn.wcj.handler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.wcj.entity.Comment;
import cn.wcj.entity.User;
import cn.wcj.service.ICommentService;
import cn.wcj.service.IUserService;
import cn.wcj.util.Contants;
import cn.wcj.util.DataUtil;
import cn.wcj.util.POIUtils;
/**
 * 
 * @author ZZU·王成键
 * 
 * 专门使用POI完成Excel导入导出的控制层
 * 
 * 这是1个独立的控制层
 *
 */
@Controller
@RequestMapping("/POI")
public class POIHandler {

	//用户模板文件文件下载
	@RequestMapping("/User/download")
	public ResponseEntity<byte[]> downloadTemplateOfUser(HttpServletRequest HttpReq)throws Exception{
	  	    byte[] body=null;
	        String path=HttpReq.getServletContext().getRealPath("/download/userTemplate.xls")  ;   //取得XLS文件位置
      	    InputStream in=new FileInputStream(path) ;   //创建输入流
	        body=new byte[in.available()];               //保存文件
	        in.read(body);                               //读取文件
	        in.close();                     //读取结束就关闭,释放资源 
	        HttpHeaders headers=new HttpHeaders();
	       //响应头的名字和响应头的值
	       headers.add("Content-Disposition", "attachment;filename=userTemplate.xls");
	       HttpStatus statusCode=HttpStatus.OK;
	       ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	       return response; 
    }
	
	//评论模板文件文件下载
	@RequestMapping("/Comment/download")
	public ResponseEntity<byte[]> downloadTemplateOfComment(HttpServletRequest HttpReq)throws Exception{
		byte[] body=null;
		String path=HttpReq.getServletContext().getRealPath("/download/commentTemplate.xls")  ;   //取得XLS文件位置
		InputStream in=new FileInputStream(path) ;   //创建输入流
		body=new byte[in.available()];               //保存文件
		in.read(body);                               //读取文件
		in.close();                     //读取结束就关闭,释放资源 
		HttpHeaders headers=new HttpHeaders();
		//响应头的名字和响应头的值
		headers.add("Content-Disposition", "attachment;filename=commentTemplate.xls");
		HttpStatus statusCode=HttpStatus.OK;
		ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
		return response; 
	}
	
	
	//到达批量增加页面
	private static final String USER_BATCH_INSERT_PAGE="poi/user_batch_insert" ;
	
	@RequestMapping("/User/toBatchInsertPage")
	public String toBatchInsertPageOfUser()throws Exception{
		   return USER_BATCH_INSERT_PAGE   ;
	}
	
	private static final String USER_LIST_PAGE="redirect:/User/toListPage"  ;
	
	@Autowired
	private IUserService userService   ;
	
	@RequestMapping("/User/doBatchCreate")
	public String doBatchCreateOfUser(@RequestParam("dataFile") MultipartFile dataFile)throws Exception{
		   InputStream input = dataFile.getInputStream();  //得到输入流,接下来调用POI-Utils完成xls文件解析
		   List<User> users = POIUtils.parseAsUserList(input) ;   //解析流
		   //对每一个密码进行盐值加密
		   for(User user: users)
			   user.setPassword(DataUtil.encrypt(Contants.ALGORITHM_MD5, 
					             user.getPassword(),
					             user.getName(), 
					             Contants.COMMON_HASH_ITERATIONS))    ;
		   userService.doBatchCreate(users)  ;   //批量增加 
		   return USER_LIST_PAGE ;
	}
	
	private static final String COMMENT_BATCH_INSERT_PAGE="poi/comment_batch_insert"  ; 
	
	@RequestMapping("/Comment/toBatchInsertPage")
	public String toBatchInsertPageOfComment()throws Exception{
		   return COMMENT_BATCH_INSERT_PAGE  ;
	}
	
	private static final String COMMENT_LIST_PAGE="redirect:/Comment/toListPage"  ;   //评论显示页面
	
	@Autowired
	private ICommentService commentService    ;
	
	@RequestMapping("/Comment/doBatchCreate")
	public String doBatchCreateOfComment(@RequestParam("dataFile") MultipartFile dataFile)throws Exception{
		   InputStream input = dataFile.getInputStream();  //得到输入流,接下来调用POI-Utils完成xls文件解析
		   List<Comment> commens = POIUtils.parseAsCommentList(input);
		   commentService.doBatchCreate(commens)   ;   //批量增加 
		   return COMMENT_LIST_PAGE ;
	}
	
	
	
	
}
