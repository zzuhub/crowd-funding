package cn.wcj.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import cn.wcj.entity.Comment;
import cn.wcj.entity.Project;
import cn.wcj.entity.Role;
import cn.wcj.entity.User;

/**
 * 
 * @author 软件学院·王成键
 *
 *POI工具类,主要完成Excle文件转换成List集合
 *
 */
@SuppressWarnings("resource")
public class POIUtils {

	public static List<User> parseAsUserList(InputStream input)throws Exception{
		   List<User> users=new ArrayList<>()   ;   //保存数据
		   HSSFWorkbook wb=new HSSFWorkbook(input)   ;      //创建工作簿
		   HSSFSheet sheet=wb.getSheetAt(0)  ;             //取得sheet页
		   for(int x=1;x<=sheet.getLastRowNum();x++){   //从第二行开始,第一行是标题
			   HSSFRow row=sheet.getRow(x)  ;   //取得每行
			   Cell nameCell = row.getCell(0)  ;   //用户名单元格
			   Cell passwordCell = row.getCell(1)  ;   //密码单元格
			   Cell roleCell = row.getCell(2)  ;   //角色单元格
			   String nameVal=getStringByCellType(nameCell) ;   //取得值
			   String passwordVal=getStringByCellType(passwordCell) ;  
			   String roleStr=getStringByCellType(roleCell)  ;
			   Integer[] roleIds=parseRoleStr(roleStr)  ;    //得到角色ID
			   User user=new User()  ;
			   user.setName(nameVal);
			   user.setPassword(passwordVal);
			   //设置角色
			   List<Role> roles=new ArrayList<>()  ;
			   for(Integer roleId : roleIds){
				   Role role=new Role() ;   //新建角色
				   role.setRoleId(roleId);  //设置角色ID
				   roles.add(role) ;
			   }
			   user.setRoles(roles);
			   users.add(user)   ;   //添加用户 
		   }
		   return users ;
	}
	
	public static String getStringByCellType(Cell cell){
	      String target=null ;   //默认字符串
	      if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType()){         //布尔型
	    	  target=String.valueOf(cell.getBooleanCellValue() )  ;
	      }else if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()){      
	    	   if(HSSFDateUtil.isCellDateFormatted(cell)){   //日期型
	    		   target=new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
	    	   }else{                                        //数值型
	    		   target=new DecimalFormat("##.##").format(cell.getNumericCellValue());
	    	   }
	      }else{                                                       //字符串型
	    	  target=cell.getStringCellValue() ;
	      }
	      return target   ;
    } 
	
	public static Integer[]  parseRoleStr(String roleStr)throws Exception{
		   String[] roleIdStrs = roleStr.split(",") ;
		   Integer[] roleIds=new Integer[roleIdStrs.length]  ;
		   for(int x=0;x<roleIdStrs.length;x++){
			   roleIds[x]=Integer.parseInt(roleIdStrs[x])  ;
		   }
		   return  roleIds ;
	}
	
	public static List<Comment>  parseAsCommentList(InputStream input)throws Exception{
		   List<Comment> comments=new ArrayList<>()  ;    //评论列表
		   HSSFWorkbook wb=new HSSFWorkbook(input)   ;      //创建工作簿
		   HSSFSheet sheet=wb.getSheetAt(0)  ;             //取得sheet页
		   for(int x=1;x<=sheet.getLastRowNum();x++){   //从第二行开始,第一行是标题
			   HSSFRow row=sheet.getRow(x)  ;   //取得每行
			   Cell userIdCell = row.getCell(0)  ;   //用户ID
			   Cell projectIdCell = row.getCell(1)  ;   //项目ID
			   Cell content = row.getCell(2)  ;   //内容
			   Integer userIdVal = Double.valueOf(userIdCell.getNumericCellValue()).intValue();
			   Integer projectIdVal = Double.valueOf(projectIdCell.getNumericCellValue()).intValue();
			   String contentVal = content.getStringCellValue();
			   Comment comment=new Comment() ;
			   //1.创建项目
			   Project project=new Project()  ;
			   project.setProjectId(projectIdVal);
			   //2.创建用户
			   User user=new User()  ;
			   user.setUserId(userIdVal);
			   //3.设置映射
			   comment.setUser(user);
			   comment.setProject(project);
			   comment.setContent(contentVal);   //设置评论内容 
			   //4.增加到列表
			   comments.add(comment)   ;
		   }
		   return comments   ;   //返回评论列表
	}
	
}
