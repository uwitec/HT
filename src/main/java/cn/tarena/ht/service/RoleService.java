package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Role;

public interface RoleService {
	public List<Role> findRoleList() ;

	public void saveRole(Role role);

	public Role findRoleById(String roleId);

	public void updateRole(Role role);

	public Role findRoleInfosById(String roleId);

	public void saveRoleModule(String roleId, String[] moduleIds);

	public void deleteRoleById(String[] roleIds);
}
