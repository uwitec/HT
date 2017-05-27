package cn.tarena.ht.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import cn.tarena.ht.utils.Encrypt;

public class AuthCredential extends  SimpleCredentialsMatcher{
	
	//shiro没有指定必须要作加密，如果用户想要做加密算法必须重写doCredentialsMatch方法
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		/**
		 * 将用户传入的密码进行加密，
		 * 加密算法是自定义的
		 * 需要将加密后的password放入token中，之后shiro才能做校验
		 */
		
		UsernamePasswordToken  loginToken = (UsernamePasswordToken) token ;
		
		//获取用户传入的密码
		String password = String.valueOf(loginToken.getPassword()) ;
		String username = loginToken.getUsername() ;
		
		//密码加密
		password = Encrypt.getMd5(password, username) ;
		
		//将加密后的password放入token
		loginToken.setPassword(password.toCharArray());
		
		
		
		return super.doCredentialsMatch(loginToken, info);
	}
	
	
	
	
	
	
	
	
	/*@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//通过token 获取用户名和密码
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String username = userToken.getUsername();
		String password = String.valueOf(userToken.getPassword());
		
		String encryptPassword = Encrypt.getMd5(password, username);
		userToken.setPassword(encryptPassword.toCharArray());
		
		return super.doCredentialsMatch(userToken, info);
	}*/
}
