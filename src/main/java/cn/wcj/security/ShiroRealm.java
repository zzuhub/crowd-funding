package cn.wcj.security;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wcj.entity.User;
import cn.wcj.service.IUserService;
/**
 * 
 * Shiro安全框架完成认证和授权的类
 * 
 * 
 * 
 * 
 * @author WangCJ
 *
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService   ;   //用户服务层注入
	
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//保存权限名称列表和角色名称列表
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo() ;
		String userName=(String) principals.getPrimaryPrincipal()  ;  //用户名
		try {
			Set<String> roleNameSet = this.userService.findRoleNamesByName(userName) ;   //根据用户名查询全部角色名称列表
			info.setRoles(roleNameSet);   //添加角色名称列表
			Set<String> permissionNameSet = this.userService.findPermissionNamesByName(userName);//根据用户名查询全部权限名称列表
			info.setStringPermissions(permissionNameSet) ;   //添加权限名称列表  
		} catch (Exception e) {}
		return info  ;
	}
    
	/*
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 * 
	 * 主要完成用户登录时认证信息的调取
	 * 
	 * 
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo authenticationInfo=null ;
		//1.将AuthenticationToken强转为UsernamePasswordToken
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		//2.根据用户名调用UserService层查询用户信息
		String name=upToken.getUsername() ;
		User user=null ;
		try {
			user=this.userService.findByName(name) ;
		} catch (Exception e) {}
		if(user==null)
			throw new UnknownAccountException("用户名不存在")  ;   //抛出用户名错误异常
		else if(user.getStatus().equals(0))
			throw new LockedAccountException("用户被锁定")   ;    //用户被锁定 
		//将数据库中查询到的用户名和密码放置到SimpleAuthenticationInfo中
		//1)Object principal 用户认证信息,即用户名, 
		//2)Object credentials 用户密码, 
		//3)String realmName Real名称,通过getName取得
		Object principal =user.getName()   ;
		Object credentials=user.getPassword()   ;
		String realmName=super.getName() ; //调用父类获取Realm名称的方法
		ByteSource credentialsSalt=ByteSource.Util.bytes(name);  //盐值
		authenticationInfo=new SimpleAuthenticationInfo(principal, credentials,
				                                         credentialsSalt, realmName) ;
		return authenticationInfo ;
	}

}
