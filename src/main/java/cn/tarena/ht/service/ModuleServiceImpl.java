package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.pojo.Module;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Resource
	private ModuleMapper moduleMapper ;
	
	public List<Module> findModuleList() {
		return moduleMapper.findModuleList();
	}

	@Override
	public void saveModule(Module module) {

		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		module.setState(1);//默认都是启用
		
		moduleMapper.saveModule(module) ;
	}

	@Override
	public Module findModuleById(String moduleId) {
		
		return moduleMapper.findModuleById(moduleId) ;
		
		
		
	}

	@Override
	public List<Module> findParentModuleList(String moduleId) {
		
		return moduleMapper.findParentModuleList(moduleId);
	}

	@Override
	public void updateModule(Module module) {
		moduleMapper.updateModule(module) ;
		
	}

	@Override
	public List<String> findRoleModuleByRoleId(String roleId) {
		
		return moduleMapper.findRoleModuleByRoleId(roleId);
	}

	
//	public void deleteModules()
}
