package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper ;
	
	@Override
	public List<Role> findRoleList() {
		// TODO Auto-generated method stub
		return roleMapper.findRoleList();
	}

	@Override
	public void saveRole(Role role) {
		
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateTime(new Date());
		roleMapper.saveRole(role) ;
		
	}

	
	public Role findRoleById(String roleId) {
		
		return roleMapper.findRoleById(roleId);
	}

	@Override
	public void updateRole(Role role) {
		role.setUpdateTime(new Date());
		
		roleMapper.updateRole(role);
		
	}

	@Override
	public Role findRoleInfosById(String roleId) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleInfosById(roleId);
	}

	
	
	
	public void saveRoleModule(String roleId, String[] moduleIds) {
		//在保存信息前先删除(因为这是一张关联表)
		roleMapper.deleteRoleModule(roleId) ;
		
		//插入操作
		for (String moduleId : moduleIds) {
			roleMapper.saveModule(roleId,moduleId) ;
		}
		
		
	}

	@Override
	public void deleteRoleById(String[] roleIds) {
		
		for (String roleId : roleIds) {
			roleMapper.deleteRoleModule(roleId);
		}
		roleMapper.deleteRoleById(roleIds) ;
	}

}
