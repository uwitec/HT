package cn.tarena.ht.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
@RequestMapping("/sysadmin/dept")
public class DeptController extends BaseController{//继承BaseController目的就是为了日期格式转化

	@Resource
	private DeptService deptService ;
	
	@RequestMapping("/list")
	public String toDeptList(Model model){
		List<Dept> deptList = deptService.findDeptList() ;
		model.addAttribute("deptList", deptList) ;
		return "/sysadmin/dept/jDeptList" ;
	}
	
	//状态启用
	@RequestMapping("/start")
	public String toStart(@RequestParam("deptId") String[] deptIds){
		deptService.updateState(deptIds,1) ;
		return "redirect:/sysadmin/dept/list" ;
	}
	
	//状态停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam("deptId") String[] deptIds){
		deptService.updateState(deptIds,0) ;
		return "redirect:/sysadmin/dept/list" ;
	}
	
	
	@RequestMapping("/delete")
	public String deletetDepts(@RequestParam("deptId") String[] deptIds){
		
		deptService.deleteDepts(deptIds) ;
		
		return "redirect:/sysadmin/dept/list" ;
	}
	
	
	//新增（创建页面）
	@RequestMapping("tocreate")
	public String toCreateDept(Model model){
		//准备页面数据	父级部门
		List<Dept> deptList = deptService.findDeptList() ;
		model.addAttribute("deptList", deptList) ;
		
		return "/sysadmin/dept/jDeptCreate" ;
	}
	
	
	//新增（保存）
	@RequestMapping("save")
	public String saveDept(Dept dept){
		
		deptService.saveDept(dept) ;
		
		return "redirect:/sysadmin/dept/list" ;
	}
	
	
	//查询部门(创建页面)
	@RequestMapping("toview")
	public String viewDept(Model model){
		List<Dept> deptList = deptService.findDeptList() ;
		model.addAttribute("deptList", deptList) ;
		return "/sysadmin/dept/jDeptView" ;
	}
	
	@RequestMapping("query")
	public String viewDeptQuery(Model model,String deptId){
		Dept dept = deptService.findOneDept(deptId) ;
		model.addAttribute("dept",dept) ;
		return "sysadmin/dept/jDeptViewShow" ;
	}
	
	
	
	
	
	
	
	
	
	
	//修改部门(创建页面)
	@RequestMapping("toupdate")
	public String toUpdate(Model model ,String deptId){
		Dept dept = deptService.findOneDept(deptId) ;
		model.addAttribute("dept", dept) ;
		return "sysadmin/dept/jDeptUpdate" ;
	}
	
	
	
	@RequestMapping("saveDeptUpdate")
	public String updateDept(Dept dept){
		
		System.out.println("11111111111111111111111111111111111111111111111111");
		System.out.println(dept);
		
		deptService.updateDept(dept) ;
		
		return "redirect:/sysadmin/dept/list" ;
	}
	
}
