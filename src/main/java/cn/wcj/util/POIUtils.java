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
 * @author ���ѧԺ�����ɼ�
 *
 *POI������,��Ҫ���Excle�ļ�ת����List����
 *
 */
@SuppressWarnings("resource")
public class POIUtils {

	public static List<User> parseAsUserList(InputStream input)throws Exception{
		   List<User> users=new ArrayList<>()   ;   //��������
		   HSSFWorkbook wb=new HSSFWorkbook(input)   ;      //����������
		   HSSFSheet sheet=wb.getSheetAt(0)  ;             //ȡ��sheetҳ
		   for(int x=1;x<=sheet.getLastRowNum();x++){   //�ӵڶ��п�ʼ,��һ���Ǳ���
			   HSSFRow row=sheet.getRow(x)  ;   //ȡ��ÿ��
			   Cell nameCell = row.getCell(0)  ;   //�û�����Ԫ��
			   Cell passwordCell = row.getCell(1)  ;   //���뵥Ԫ��
			   Cell roleCell = row.getCell(2)  ;   //��ɫ��Ԫ��
			   String nameVal=getStringByCellType(nameCell) ;   //ȡ��ֵ
			   String passwordVal=getStringByCellType(passwordCell) ;  
			   String roleStr=getStringByCellType(roleCell)  ;
			   Integer[] roleIds=parseRoleStr(roleStr)  ;    //�õ���ɫID
			   User user=new User()  ;
			   user.setName(nameVal);
			   user.setPassword(passwordVal);
			   //���ý�ɫ
			   List<Role> roles=new ArrayList<>()  ;
			   for(Integer roleId : roleIds){
				   Role role=new Role() ;   //�½���ɫ
				   role.setRoleId(roleId);  //���ý�ɫID
				   roles.add(role) ;
			   }
			   user.setRoles(roles);
			   users.add(user)   ;   //����û� 
		   }
		   return users ;
	}
	
	public static String getStringByCellType(Cell cell){
	      String target=null ;   //Ĭ���ַ���
	      if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType()){         //������
	    	  target=String.valueOf(cell.getBooleanCellValue() )  ;
	      }else if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()){      
	    	   if(HSSFDateUtil.isCellDateFormatted(cell)){   //������
	    		   target=new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
	    	   }else{                                        //��ֵ��
	    		   target=new DecimalFormat("##.##").format(cell.getNumericCellValue());
	    	   }
	      }else{                                                       //�ַ�����
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
		   List<Comment> comments=new ArrayList<>()  ;    //�����б�
		   HSSFWorkbook wb=new HSSFWorkbook(input)   ;      //����������
		   HSSFSheet sheet=wb.getSheetAt(0)  ;             //ȡ��sheetҳ
		   for(int x=1;x<=sheet.getLastRowNum();x++){   //�ӵڶ��п�ʼ,��һ���Ǳ���
			   HSSFRow row=sheet.getRow(x)  ;   //ȡ��ÿ��
			   Cell userIdCell = row.getCell(0)  ;   //�û�ID
			   Cell projectIdCell = row.getCell(1)  ;   //��ĿID
			   Cell content = row.getCell(2)  ;   //����
			   Integer userIdVal = Double.valueOf(userIdCell.getNumericCellValue()).intValue();
			   Integer projectIdVal = Double.valueOf(projectIdCell.getNumericCellValue()).intValue();
			   String contentVal = content.getStringCellValue();
			   Comment comment=new Comment() ;
			   //1.������Ŀ
			   Project project=new Project()  ;
			   project.setProjectId(projectIdVal);
			   //2.�����û�
			   User user=new User()  ;
			   user.setUserId(userIdVal);
			   //3.����ӳ��
			   comment.setUser(user);
			   comment.setProject(project);
			   comment.setContent(contentVal);   //������������ 
			   //4.���ӵ��б�
			   comments.add(comment)   ;
		   }
		   return comments   ;   //���������б�
	}
	
}
