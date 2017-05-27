package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;

public interface UserMapper {
	List<User> findUserList() ;

	void updateState(@Param("userIds") String[] userIds, @Param("state") int state);

	void deleteUsers(String[] userIds);

	void saveUser(User user);

	void saveRoleUser(@Param("userId") String userId, @Param("roleId") String roleId);

	@Select("select role_id from role_user_p where user_id=#{userId}")
	List<String> findUserRoleById(String userId);

	
	
	@Delete("delete from role_user_p where user_id=#{userId}")
	void deleteRoleUserByUserId(String userId);

	
	User findUserByU_P(@Param("username") String username, @Param("password") String password);
	
	@Select("select * from user_p where user_id = #{userId}")
	User findUserById(String userId) ;

	User findUserByUsername(String username);
}
