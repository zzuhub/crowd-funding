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
	private IUserService userService   ;  //用户服务层 

	@RequestMapping("/doLogin")
	public String doLogin(@ModelAttribute User user,HttpServletRequest request)throws Exception{
		   String page=INDEX_PAGE   ;
		   String name = user.getName();  //用户名
		   String password = user.getPassword(); //密码
		   Subject subject = SecurityUtils.getSubject() ;
		   UsernamePasswordToken token = new UsernamePasswordToken(name, password); //令牌
		   HttpSession session = request.getSession();
		   try{  //出现异常,全部统一定向回登录页
			       subject.login(token);      //登录
			       String ip=request.getRemoteHost() ;   //获取IP
			       Date date=new Date();    //登录时间
			       User currentUser = this.userService.findByName(name) ;
			       currentUser.setLastIP(ip)  ;   //设置IP
			       currentUser.setLastLogin(date);
			       currentUser.setLoginErr(0) ;   //设置登录密码错误数归零
			       this.userService.doUpdate(currentUser) ;   //更新用户登录信息
			       session.setAttribute("user", currentUser);
		   }catch(UnknownAccountException e1){
			       session.setAttribute("errMsg", "用户名未知,请重新登陆!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(LockedAccountException e2){
			       session.setAttribute("errMsg", "对不起,该用户已锁定!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(IncorrectCredentialsException e3){
			       User currentUser = this.userService.findByName(name) ;
			       Integer errCount=currentUser.getLoginErr()  ; //获取密码错误数
			       if(errCount>3){   //密码错误>=4
			    	   currentUser.setStatus(0) ;   //锁定用户  1:可用  0:锁定
			    	   session.setAttribute("errMsg", "密码多次输错,用户已锁定") ;
			       }else{
			    	   currentUser.setLoginErr(++errCount) ;   //增加错误数
			    	   session.setAttribute("errMsg", "密码错误,请重新登陆!") ;
			       }
			       this.userService.doUpdate(currentUser)  ;    //更新用户出错和状态
			       page=REDIRECT_LOGIN_PAGE  ;
		   }catch(Exception e4){
			       session.setAttribute("errMsg", "系统出现异常,请重新登陆!") ;
			       page=REDIRECT_LOGIN_PAGE  ;
		   }
		   return page  ;
	}

	@Override
	public String findAll(Integer currentPage, Integer lineSize, String keyWord) throws Exception {
		return null;
	}

	
}
