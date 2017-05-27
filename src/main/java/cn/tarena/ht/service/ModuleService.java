package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Module;

public interface ModuleService {
	public List<Module> findModuleList() ;

	public void saveModule(Module module);

	public Module findModuleById(String moduleId);

	public List<Module> findParentModuleList(String moduleId);
	
	public void updateModule(Module module) ;

	public List<String> findRoleModuleByRoleId(String roleId);
}
