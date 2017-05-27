package cn.tarena.ht.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.mysql.jdbc.Security;
import com.sun.org.apache.xerces.internal.xs.StringList;

import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;


public class AuthRealm extends AuthorizingRealm{

	@Resource
	private UserService userService ;
	
	
	
	@Override//授权管理
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		/**
		 * session中存放着用户的信息，获取并添加权限
		 */
		
		Subject subject = SecurityUtils.getSubject() ;
		subject.getSession() ;
		
		
		List<String> roleList = new ArrayList<String>() ;
		roleList.add("货运管理") ;
		roleList.add("基础信息") ;
		roleList.add("系统管理") ;
		
		//权限管理器
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
		
		//向权限管理器添加权限
		info.addStringPermissions(roleList);
		
		
		
		return info;
	}

	@Override//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/**realm的作用 为shiro准备资料	真实用户信息
		 * 1、根据用户提供的username到数据库中查询用户信息
		 * 2、得到用户对象
		 * 3、把用户对象，用户的密码，当前的realm返回
		 * 4、shiro会自动的根据提供的资料与传入的信息作比较
		 */
		
		
		
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token ;
		
		//1、获取username	这是用户传入的username
		String username = loginToken.getUsername() ;
		
		//2、获取user对象
		User user = userService.findUserByUsername(username) ;
		
		//3、返回对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		
		
		
		return info;
	}

	
	
}
