package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;

public interface UserService {
	List<User> findUserList() ;

	void updateState(String[] userIds, int state);

	void deleteUsers(String[] userIds);

	void saveUser(User user);

	void saveRoleUser(String userId, String[] roleIds);

	List<String> findUserRoleById(String userId);

	User findUserByU_P(String username, String password);

	User findUserById(String userId);

	User findUserByUsername(String username);
}
