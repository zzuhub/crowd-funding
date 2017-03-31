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
 * @author ZZU�����ɼ�
 * 
 * ר��ʹ��POI���Excel���뵼���Ŀ��Ʋ�
 * 
 * ����1�������Ŀ��Ʋ�
 *
 */
@Controller
@RequestMapping("/POI")
public class POIHandler {

	//�û�ģ���ļ��ļ�����
	@RequestMapping("/User/download")
	public ResponseEntity<byte[]> downloadTemplateOfUser(HttpServletRequest HttpReq)throws Exception{
	  	    byte[] body=null;
	        String path=HttpReq.getServletContext().getRealPath("/download/userTemplate.xls")  ;   //ȡ��XLS�ļ�λ��
      	    InputStream in=new FileInputStream(path) ;   //����������
	        body=new byte[in.available()];               //�����ļ�
	        in.read(body);                               //��ȡ�ļ�
	        in.close();                     //��ȡ�����͹ر�,�ͷ���Դ 
	        HttpHeaders headers=new HttpHeaders();
	       //��Ӧͷ�����ֺ���Ӧͷ��ֵ
	       headers.add("Content-Disposition", "attachment;filename=userTemplate.xls");
	       HttpStatus statusCode=HttpStatus.OK;
	       ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	       return response; 
    }
	
	//����ģ���ļ��ļ�����
	@RequestMapping("/Comment/download")
	public ResponseEntity<byte[]> downloadTemplateOfComment(HttpServletRequest HttpReq)throws Exception{
		byte[] body=null;
		String path=HttpReq.getServletContext().getRealPath("/download/commentTemplate.xls")  ;   //ȡ��XLS�ļ�λ��
		InputStream in=new FileInputStream(path) ;   //����������
		body=new byte[in.available()];               //�����ļ�
		in.read(body);                               //��ȡ�ļ�
		in.close();                     //��ȡ�����͹ر�,�ͷ���Դ 
		HttpHeaders headers=new HttpHeaders();
		//��Ӧͷ�����ֺ���Ӧͷ��ֵ
		headers.add("Content-Disposition", "attachment;filename=commentTemplate.xls");
		HttpStatus statusCode=HttpStatus.OK;
		ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
		return response; 
	}
	
	
	//������������ҳ��
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
		   InputStream input = dataFile.getInputStream();  //�õ�������,����������POI-Utils���xls�ļ�����
		   List<User> users = POIUtils.parseAsUserList(input) ;   //������
		   //��ÿһ�����������ֵ����
		   for(User user: users)
			   user.setPassword(DataUtil.encrypt(Contants.ALGORITHM_MD5, 
					             user.getPassword(),
					             user.getName(), 
					             Contants.COMMON_HASH_ITERATIONS))    ;
		   userService.doBatchCreate(users)  ;   //�������� 
		   return USER_LIST_PAGE ;
	}
	
	private static final String COMMENT_BATCH_INSERT_PAGE="poi/comment_batch_insert"  ; 
	
	@RequestMapping("/Comment/toBatchInsertPage")
	public String toBatchInsertPageOfComment()throws Exception{
		   return COMMENT_BATCH_INSERT_PAGE  ;
	}
	
	private static final String COMMENT_LIST_PAGE="redirect:/Comment/toListPage"  ;   //������ʾҳ��
	
	@Autowired
	private ICommentService commentService    ;
	
	@RequestMapping("/Comment/doBatchCreate")
	public String doBatchCreateOfComment(@RequestParam("dataFile") MultipartFile dataFile)throws Exception{
		   InputStream input = dataFile.getInputStream();  //�õ�������,����������POI-Utils���xls�ļ�����
		   List<Comment> commens = POIUtils.parseAsCommentList(input);
		   commentService.doBatchCreate(commens)   ;   //�������� 
		   return COMMENT_LIST_PAGE ;
	}
	
	
	
	
}
