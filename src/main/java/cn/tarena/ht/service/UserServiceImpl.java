package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.utils.Encrypt;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper ;
	
	@Resource
	private UserInfoMapper userInfoMapper ;
	
	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		return userMapper.findUserList();
	}

	@Override
	public void updateState(String[] userIds, int state) {
		userMapper.updateState(userIds, state) ;
		
	}

	@Override
	public void deleteUsers(String[] userIds) {
		userMapper.deleteUsers(userIds) ;
		userInfoMapper.deleteUserInfos(userIds);
	}

	@Override
	public void saveUser(User user) {
		String uuid = UUID.randomUUID().toString() ;
		//保存时需要保存两张表的数据
		UserInfo userInfo = user.getUserInfo() ;
		userInfo.setUserInfoId(uuid);
		userInfo.setCreateTime(new Date());//为UserInfo赋值
		
		user.setUserId(uuid);
		user.setCreateTime(new Date());
		user.setState(1);
		
		//两张表分别保存
		userMapper.saveUser(user) ;
		userInfoMapper.saveUserInfo(userInfo) ;
		
	}

	
	//因为中间表没有实际意义，所以调用userMapper进行处理
	@Override
	public void saveRoleUser(String userId, String[] roleIds) {
		
		//多对多先删除后插入
		userMapper.deleteRoleUserByUserId(userId) ;
		
		
		for (String roleId : roleIds) {
			userMapper.saveRoleUser(userId,roleId) ;
		}
		
	}

	
	
	@Override
	public List<String> findUserRoleById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserRoleById(userId);
	}

	@Override
	public User findUserByU_P(String username, String password) {
		// TODO Auto-generated method stub
		return userMapper.findUserByU_P(username,password);
	}

	@Override
	public User findUserById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserById(userId);
	}

	//shiro中根据用户名查询用户信息
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUsername(username);
	}



}
