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
 * Shiro��ȫ��������֤����Ȩ����
 * 
 * 
 * 
 * 
 * @author WangCJ
 *
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService   ;   //�û������ע��
	
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//����Ȩ�������б�ͽ�ɫ�����б�
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo() ;
		String userName=(String) principals.getPrimaryPrincipal()  ;  //�û���
		try {
			Set<String> roleNameSet = this.userService.findRoleNamesByName(userName) ;   //�����û�����ѯȫ����ɫ�����б�
			info.setRoles(roleNameSet);   //��ӽ�ɫ�����б�
			Set<String> permissionNameSet = this.userService.findPermissionNamesByName(userName);//�����û�����ѯȫ��Ȩ�������б�
			info.setStringPermissions(permissionNameSet) ;   //���Ȩ�������б�  
		} catch (Exception e) {}
		return info  ;
	}
    
	/*
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 * 
	 * ��Ҫ����û���¼ʱ��֤��Ϣ�ĵ�ȡ
	 * 
	 * 
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo authenticationInfo=null ;
		//1.��AuthenticationTokenǿתΪUsernamePasswordToken
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		//2.�����û�������UserService���ѯ�û���Ϣ
		String name=upToken.getUsername() ;
		User user=null ;
		try {
			user=this.userService.findByName(name) ;
		} catch (Exception e) {}
		if(user==null)
			throw new UnknownAccountException("�û���������")  ;   //�׳��û��������쳣
		else if(user.getStatus().equals(0))
			throw new LockedAccountException("�û�������")   ;    //�û������� 
		//�����ݿ��в�ѯ�����û�����������õ�SimpleAuthenticationInfo��
		//1)Object principal �û���֤��Ϣ,���û���, 
		//2)Object credentials �û�����, 
		//3)String realmName Real����,ͨ��getNameȡ��
		Object principal =user.getName()   ;
		Object credentials=user.getPassword()   ;
		String realmName=super.getName() ; //���ø����ȡRealm���Ƶķ���
		ByteSource credentialsSalt=ByteSource.Util.bytes(name);  //��ֵ
		authenticationInfo=new SimpleAuthenticationInfo(principal, credentials,
				                                         credentialsSalt, realmName) ;
		return authenticationInfo ;
	}

}
