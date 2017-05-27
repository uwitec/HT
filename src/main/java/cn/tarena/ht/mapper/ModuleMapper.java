package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {
	
	//查询所有的模块信息
	public List<Module> findModuleList() ;

	public void saveModule(Module module);
	
	public Module findModuleById(String moduleId) ;

	public List<Module> findParentModuleList(String moduleId);
	
	public void updateModule(Module module) ;

	@Select("select module_id from role_module_p where role_id = #{roleId}")
	public List<String> findRoleModuleByRoleId(String roleId);
	
}
