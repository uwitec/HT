package cn.tarena.ht.mapper;

import cn.tarena.ht.pojo.UserInfo;

public interface UserInfoMapper {
	public void deleteUserInfos(String[] userInfoIds) ;

	public void saveUserInfo(UserInfo userInfo);
}
