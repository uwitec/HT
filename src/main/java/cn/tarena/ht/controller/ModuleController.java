package cn.tarena.ht.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;


@Controller
@RequestMapping("/sysadmin/module/")
public class ModuleController extends BaseController{

	@Resource
	private ModuleService moduleService ;
	
	@RequestMapping("list")
	public String toModuleList(Model model){
		
		//准备页面数据
		model.addAttribute("dataList", moduleService.findModuleList()) ;
		return "sysadmin/module/jModuleList" ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("tocreate")
	public String toCreate(Model model){
		
		List<Module> moduleList = moduleService.findModuleList() ;
		
		model.addAttribute("moduleList", moduleList) ;
		return "sysadmin/module/jModuleCreate" ;
	}
	
	
	
	
	
	@RequestMapping("save")
	public String saveModule(Module module){
		
		moduleService.saveModule(module) ;
		
		return "redirect:/sysadmin/module/list" ;
	}
	

	
	
	
	
	@RequestMapping("toupdate")
	public String toUpdate(String moduleId,Model model){
		//准备要修改的数据
		Module module = moduleService.findModuleById(moduleId) ;
		
		//修改模块管理的代码部分：在修改代码的页面中数据需要回显但是本身不可能是本身的上级，这时候有两种解法办法
			//1、修改显示页面部分的jsp代码                          2、新增数据库中的查询办法
		List<Module> moduleList = moduleService.findParentModuleList(moduleId) ;
		
		model.addAttribute("module", module) ;
		model.addAttribute("moduleList", moduleList) ;		
		
		return "sysadmin/module/jModuleUpdate" ;
	}
	
	
	
	
	
		
	
	@RequestMapping("update")
	public String updateModule(Module module){
	
		moduleService.updateModule(module) ;//该行代码接受jsp页面的数据，本身的自关联信息也要在传值的jsp页面中显现
		
		
		//从定向到用户列表中
		return "redirect:/sysadmin/module/list" ;
		
	}
	
	
	
	@RequestMapping("toview")
	public String toView(Model model ,String moduleId){
		model.addAttribute("module", moduleService.findModuleById(moduleId)) ;
		
		return "sysadmin/module/jModuleView" ;
		
	}
	
	
	//删除
	/*@RequestMapping("delete")
	public String toDelete(@RequestParam(),){
		
	}*/
	
}
