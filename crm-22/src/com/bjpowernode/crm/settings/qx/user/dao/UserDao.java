package com.bjpowernode.crm.settings.qx.user.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.qx.user.domain.User;

/**
 * 用户持久化处理接口
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 根据账号和密码查询用户
	 * @param map
	 * @return
	 */
	public User queryUserByLoginActPwd(Map<String,Object> map);
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryAllUsers();
}
