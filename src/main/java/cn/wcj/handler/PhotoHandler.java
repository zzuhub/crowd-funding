package cn.wcj.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.wcj.entity.Photo;
import cn.wcj.entity.Project;
import cn.wcj.service.IPhotoService;
import cn.wcj.service.IProjectService;
import cn.wcj.util.Contants;
import cn.wcj.util.IPTimeStamp;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("unchecked")
@RequestMapping("/Photo")
@Controller
public class PhotoHandler implements IBaseHandler<Photo>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IPhotoService photoService   ;
	
	private static final String INSERT_PAGE="photo/photo_insert"  ;
	
	 @RequestMapping("/toInsertPage")
	 public String toInsertPage()throws Exception{
		 return INSERT_PAGE  ;
	 }
	 
	 private static final String LIST_PAGE="photo/photo_list";
	 
	 @RequestMapping("/toListPage")
	 public String toListPage()throws Exception{
		 return LIST_PAGE  ;
	 }

	@ResponseBody
	@RequestMapping(value="findAll",produces = "text/html;charset=UTF-8")
	@Override
	public String findAll(@RequestParam("cp")Integer currentPage,
			              @RequestParam("ls")Integer lineSize, 
			              @RequestParam("kw")String keyWord)
	                                          throws Exception {
		Map<String, Object> map = this.photoService.findAll(currentPage, lineSize, keyWord);   //分页汇总
		PageInfo<Photo> pageInfo=(PageInfo<Photo>) map.get("pageInfo") ;  //分页信息
		List<Photo> photos=(List<Photo>) map.get("data") ;    //分页数据
		JSONObject jsonObj=new JSONObject()  ;
		jsonObj.put("allRecords", pageInfo.getTotal())   ;
		jsonObj.put("kw", keyWord)   ;   //回传关键词
		jsonObj.put("currentSize", pageInfo.getSize())  ;  //当前页数据量
		jsonObj.put("allPhotos", photos)  ;   //放入数据 
		return jsonObj.toString();
	}
	
	@Autowired
	private IPTimeStamp ipTimeStamp   ;
	
	@RequestMapping("doCreate") 
	public String doCreate(@RequestParam("photoFile") MultipartFile photoFile,
			               @RequestParam("projectId")Integer projectId,
			               HttpServletRequest req
			                                          )throws Exception{
		  String photo=null ;   
		  if(photoFile.getOriginalFilename()==null
			||"".equals(photoFile.getOriginalFilename()))
			                              photo="nophoto.jpg"   ;
		  String stuff=photoFile.getOriginalFilename().split("\\.")[1]  ;   //后缀
		  this.ipTimeStamp.setIp(req.getRemoteAddr());   //设置IP
		  String prefix=this.ipTimeStamp.getIPTimeStampRand() ;  //前缀
		  photo=prefix+"."+stuff ;   //照片名
		  InputStream input = photoFile.getInputStream();  //得到输入流
		  String path=req.getSession().getServletContext().getRealPath("upload");   //上传文件夹
		  String fileName=path+File.separator+photo  ;     //文件全名
		  File file=new File(fileName)  ;   //文件
		  OutputStream out=new FileOutputStream(file) ;   //文件输入流
		  byte[] buffer=new byte[1024]  ;  //缓冲区
		  int length= 0 ;
		  while((length=input.read(buffer))>0) //边读边写
			           out.write(buffer, 0, length) ;
		  //关闭流
		  out.close();  
		  input.close(); 
		  Photo entity=new Photo() ;
		  entity.setName(photo) ;
		  Project project=new Project() ;
		  project.setProjectId(projectId);
		  entity.setProject(project);
		  this.photoService.doCreate(entity)  ;  //增加图片
		  return LIST_PAGE  ;
	}
	
	@ResponseBody
	@RequestMapping(value="doRemove",produces = "text/html;charset=UTF-8")
	public String doRemove(@RequestParam("photoId")Integer photoId)throws Exception{
		   return this.photoService.doRemove(photoId)>0?Contants.SUCCESS_STATUS_JSON:Contants.FAILURE_STATUS_JSON   ;
	}
	
	private static final String DETAIL_PAGE="photo/photo_detail";
	
	@RequestMapping("/toDetailPage/{photoId}")
	public ModelAndView toDetailPage(@PathVariable("photoId")Integer photoId)throws Exception{
		   return new ModelAndView(DETAIL_PAGE).addObject("photo", this.photoService.findByID(photoId))  ;
	}
	 
	private static final String USER_PHOTO_INSERT_PAGE="/user/photo_insert"  ;   //用户增加图片页面
	
	@Autowired
	private IProjectService projectService   ;
	
	@RequestMapping("/toUserInsertPage/{userId}")
	public ModelAndView toUserInsertPage(@PathVariable("userId")Integer userId)throws Exception{
		   //根据用户ID查询用户所拥有的图片 
		   return new ModelAndView(USER_PHOTO_INSERT_PAGE).addObject("projects", this.projectService.findByUserID(userId)) ;
	}
	
	@RequestMapping("/doCreateByUser")
	public String doCreateByUser(@RequestParam("photoFile") MultipartFile photoFile,
			                     @RequestParam("projectId")Integer projectId,
			                     HttpServletRequest req)throws Exception{
		  String photo=null ;   
		  if(photoFile.getOriginalFilename()==null
			||"".equals(photoFile.getOriginalFilename()))
			                              photo="nophoto.jpg"   ;
		  String stuff=photoFile.getOriginalFilename().split("\\.")[1]  ;   //后缀
		  this.ipTimeStamp.setIp(req.getRemoteAddr());   //设置IP
		  String prefix=this.ipTimeStamp.getIPTimeStampRand() ;  //前缀
		  photo=prefix+"."+stuff ;   //照片名
		  InputStream input = photoFile.getInputStream();  //得到输入流
		  String path=req.getSession().getServletContext().getRealPath("upload");   //上传文件夹
		  String fileName=path+File.separator+photo  ;     //文件全名
		  File file=new File(fileName)  ;   //文件
		  OutputStream out=new FileOutputStream(file) ;   //文件输入流
		  byte[] buffer=new byte[1024]  ;  //缓冲区
		  int length= 0 ;
		  while((length=input.read(buffer))>0) //边读边写
			           out.write(buffer, 0, length) ;
		  //关闭流
		  out.close();  
		  input.close(); 
		  Photo entity=new Photo() ;   
		  entity.setName(photo) ;
		  Project project=new Project() ;
		  project.setProjectId(projectId);  //设置项目ID
		  entity.setProject(project);
		  this.photoService.doCreate(entity)  ;  //增加图片
		  return "redirect:/index.jsp" ;   //上传成功后跳转到首页
	}
	
	
	
}
