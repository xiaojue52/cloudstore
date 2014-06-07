package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.po.User;
/**
 * 相关接口函数
 * @author Administrator
 *
 */
public interface UserService {
	public void saveUser(User arg0);
	public void deleteUser(User arg0);
	public void updateUser(User arg0);
	public User getUserById(Integer arg0);
	public List<User> getUsersByHql(String hql);
	public List<User> getList(String hql,Map<String,Object> arg);
}
