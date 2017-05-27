package cn.tarena.ht.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserService;

@Controller
@RequestMapping("/sysadmin/user/")
public class UserController extends BaseController {
	
	@Resource
	private UserService userService ;
	
	@Resource
	private DeptService deptService ;
	
	@Resource
	private RoleService roleService ;
	
	@RequestMapping("list")
	public String toUserList(Model model){
		
		List<User> userList = userService.findUserList() ;
		model.addAttribute("dataList", userList) ;
		
		return "sysadmin/user/jUserList" ;
	}
	
	
	
	@RequestMapping("start")
	public String toStart(@RequestParam("userId") String[] userIds){
		userService.updateState(userIds,1) ;
		
		return "redirect:/sysadmin/user/list" ;
	}
	
	
	@RequestMapping("delete")
	public String deleteUser(@RequestParam("userId") String[] userIds){
		
		userService.deleteUsers(userIds) ;
		
		return "redirect:/sysadmin/user/list" ;
	}
	
	
	@RequestMapping("tocreate")
	public String toCreate(Model model){
		//部门下拉列表
		List<Dept> deptList = deptService.findDeptList() ;
		
		//上级领导的下拉框
		List<User> userList = userService.findUserList() ;
		
		model.addAttribute("deptList", deptList) ;
		model.addAttribute("userList", userList) ;
		
		return "/sysadmin/user/jUserCreate" ;
	}
	
	
	@RequestMapping("save")
	public String saveUser(User user){
		
		userService.saveUser(user) ;
		return "redirect:/sysadmin/user/list" ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//为用户分配角色
	@RequestMapping("toRoleUser")
	public String toRoleUser(Model model,String userId) throws JsonProcessingException{
		
		//为页面准备数据，所有的角色信息
		List<Role> roleList = roleService.findRoleList() ;
		
		System.out.println(roleList);
		
		/**
		 * 数据回显
		 */
		//查询用户角色ID
		List<String> userRoleList = userService.findUserRoleById(userId) ;
		
		for (Role role : roleList) {
			if(userRoleList.contains(role.getRoleId())){
				role.setChecked("true");
			}
		}
	
		/*System.out.println(roleList);
		ObjectMapper mapper = new ObjectMapper() ;
		String json = mapper.writeValueAsString(roleList) ;
		System.out.println(json);*/
		
		ObjectMapper mapper = new ObjectMapper() ;
		String json = mapper.writeValueAsString(roleList) ;
		
		
		
		model.addAttribute("zTreeJson", json) ;
		
		model.addAttribute("userId", userId) ;
		
		//转向用户的角色分配页面(其中zTreeJson中存放的是json类型的数据)
		return "/sysadmin/user/jRoleUser" ;
	}
	
	
	
	
	
	
	
	@RequestMapping("saveUserRole")
	public String saveUserRole(String userId,String[] roleIds){
		
		System.out.println(userId);
		System.out.println(Arrays.toString(roleIds));
		
		//得到用户id
		userService.saveRoleUser(userId,roleIds) ;
		
		return "redirect:/sysadmin/user/list" ;
		
	}
	
	
}
