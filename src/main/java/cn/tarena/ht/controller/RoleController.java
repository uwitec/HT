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

import cn.tarena.ht.pojo.BaseEntity;
import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;


@Controller
@RequestMapping("/sysadmin/role/")
public class RoleController extends BaseEntity {

	
	@Resource
	private RoleService roleService ;
	
	@Resource
	private ModuleService moduleService ;
	
	@RequestMapping("list")
	public String toRoleList(Model model){
		List<Role> dataList = roleService.findRoleList() ;
		model.addAttribute("dataList", dataList) ;
		return "/sysadmin/role/jRoleList" ;
	}
	
	
	
	
	
	@RequestMapping("roleAction_delete")
	public String toRoleDelete(@RequestParam("roleId") String[] roleIds){
		
		
		roleService.deleteRoleById(roleIds) ;
		return "redirect:/sysadmin/role/list" ;
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("create")
	public String toSaveRole(){
		
		return "/sysadmin/role/jRoleCreate" ;
	}
	
	
	@RequestMapping("save")
	public String saveRole(Role role){
		
		roleService.saveRole(role) ;
		
		return "redirect:/sysadmin/role/list" ;
	}
	
	
	
	@RequestMapping("update")
	public String toUpdate(String roleId,Model model){
		
		//查询当前要修改的角色
		Role role = roleService.findRoleById(roleId) ;
		
		model.addAttribute("role", role) ;
		
		return "/sysadmin/role/jRoleUpdate" ;
	}
	
	
	
	
	
	@RequestMapping("saveUpdate")
	public String saveUpdate(Role role){		
		roleService.updateRole(role) ;
		
		return "redirect:/sysadmin/role/list" ;
	}
	
	
	@RequestMapping("view")
	public String toView(Model model, String roleId){
		Role roleInfo = roleService.findRoleInfosById(roleId) ;
		model.addAttribute("role", roleInfo) ;
		return "/sysadmin/role/jRoleInfos" ;
	}
	
	
	//转向模块选择页面
	@RequestMapping("toRoleModule")
	public String toRoleUser(String roleId,Model model) throws JsonProcessingException{
		//查询数据
		List<Module> moduleList = moduleService.findModuleList() ;
		
		
		//数据回显
		List<String> roleModuleList = moduleService.findRoleModuleByRoleId(roleId) ;
		
		for (Module module : moduleList) {
			if(roleModuleList.contains(module.getId())){
				module.setChecked("true");
			}
		}
		
		
		
		
		
		
		
		//将数据转化为json
		ObjectMapper objectMapper = new ObjectMapper() ;
		String jsonZtree = objectMapper.writeValueAsString(moduleList) ;
		
		System.out.println(jsonZtree);
		
		//为页面准备数据
		model.addAttribute("jsonZtree", jsonZtree) ;
		model.addAttribute("roleId", roleId) ;//将roleId带入到页面中
		return "/sysadmin/role/jRoleModule" ;
	}
	
	
	@RequestMapping("saveRoleModule")
	public String saveRoleModule(String roleId,String[] moduleIds){
		System.out.println(roleId);
		System.out.println("------------------------");
		System.out.println(Arrays.toString(moduleIds));
		
		roleService.saveRoleModule(roleId,moduleIds) ;
		
		
		return "redirect:/sysadmin/role/list" ;
	}
}
