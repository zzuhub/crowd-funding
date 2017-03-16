package cn.wcj.handler;

import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wcj.entity.Permission;
import cn.wcj.entity.User;
import cn.wcj.service.IUserService;

@RequestMapping("/User")
@Controller
public class UserHandler implements IBaseHandler<Permission> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUserService userService   ;  //�û������ 

	@RequestMapping("/doLogin")
	public String doLogin(@ModelAttribute User user,HttpServletRequest request)throws Exception{
		   String page=INDEX_PAGE   ;
		   String name = user.getName();  //�û���
		   String password = user.getPassword(); //����
		   Subject subject = SecurityUtils.getSubject() ;
		   UsernamePasswordToken token = new UsernamePasswordToken(name, password); //����
		   HttpSession session = request.getSession();
		   try{  //�����쳣,ȫ��ͳһ����ص�¼ҳ
			       subject.login(token);      //��¼
			       String ip=request.getRemoteHost() ;   //��ȡIP
			       Date date=new Date();    //��¼ʱ��
			       User currentUser = this.userService.findByName(name) ;
			       currentUser.setLastIP(ip)  ;   //����IP
			       currentUser.setLastLogin(date);
			       currentUser.setLoginErr(0) ;   //���õ�¼�������������
			       this.userService.doUpdate(currentUser) ;   //�����û���¼��Ϣ
			       session.setAttribute("user", currentUser);
		   }catch(UnknownAccountException e1){
			       session.setAttribute("errMsg", "�û���δ֪,�����µ�½!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(LockedAccountException e2){
			       session.setAttribute("errMsg", "�Բ���,���û�������!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(IncorrectCredentialsException e3){
			       User currentUser = this.userService.findByName(name) ;
			       Integer errCount=currentUser.getLoginErr()  ; //��ȡ���������
			       if(errCount>3){   //�������>=4
			    	   currentUser.setStatus(0) ;   //�����û�  1:����  0:����
			    	   session.setAttribute("errMsg", "���������,�û�������") ;
			       }else{
			    	   currentUser.setLoginErr(++errCount) ;   //���Ӵ�����
			    	   session.setAttribute("errMsg", "�������,�����µ�½!") ;
			       }
			       this.userService.doUpdate(currentUser)  ;    //�����û������״̬
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(Exception e4){
			       session.setAttribute("errMsg", "ϵͳ�����쳣,�����µ�½!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }
		   return page  ;
	}

	@Override
	public String findAll(Integer currentPage, Integer lineSize, String keyWord) throws Exception {
		return null;
	}

	
}
