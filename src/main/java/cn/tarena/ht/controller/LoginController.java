package cn.tarena.ht.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;

@Controller
public class LoginController extends BaseController {
	
	@Resource
	private UserService userService ;
	
	
	@RequestMapping("tologin")
	public String toLogin(){
		
		return "sysadmin/login/login" ;
	}
	
	
	@RequestMapping("login")
	public String login(String username,String password,Model model,HttpSession httpSession){
		//spring中提供的工具
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			
			model.addAttribute("errorInfo", "用户名密码不能为空！") ;
			
			//如果数据为空，跳转到登陆页面
			return "sysadmin/login/login" ;
		}
		
		
		//使用shiro进行用户校验
		
		//步骤一：创建subject
		Subject subject = SecurityUtils.getSubject() ;
		
		
		/**创建一个token  一个令牌
		 * shiro拿到用户自己输入的用户名和密码
		 * 通过realm为shiro准备真实的用户
		 * shiro自己把
		 * 
		 */
		//步骤二
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password) ;
		
		//步骤三,登陆失败抛出异常
		try {
			subject.login(token);
			
			//如果登陆成功subject中会保存当前用户信息
			User session_user = (User) subject.getPrincipal() ;
			httpSession.setAttribute("session_user", session_user);
			
			return "redirect:/home.action" ;
		} catch (AuthenticationException e) {
			//登陆失败跳转到登陆页面
			return "sysadmin/login/login";
		}

		
		
/*		System.out.println("-------------------------------------------------------");
		System.out.println(username);
		System.out.println(password);
		System.out.println("-------------------------------------------------------");
		System.out.println(Encrypt.getMd5(password, username));
		
		User user = userService.findUserByU_P(username, Encrypt.getMd5(password, username));
		if (user == null) {
			model.addAttribute("errorInfo", "用户名或密码错误！");

			return "sysadmin/login/login";
		}

		System.out.println("-------------------------------------------------------");
		System.out.println(user.getUserId());
		System.out.println("-------------------------------------------------------");
		
		User session_user = userService.findUserById(user.getUserId()) ;
		
		httpSession.setAttribute("session_user", session_user);
		
		return "redirect:/home.action" ;*/
	}
}
