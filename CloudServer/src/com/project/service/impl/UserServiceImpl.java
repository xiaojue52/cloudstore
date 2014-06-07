package com.project.service.impl;

import java.util.List;
import java.util.Map;

import com.project.dao.UserDAO;
import com.project.po.User;
import com.project.service.UserService;
/**
 * 数据库事物管理
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void saveUser(User arg0) {
		// TODO Auto-generated method stub
		userDAO.saveUser(arg0);
	}

	public void deleteUser(User arg0) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(arg0);
	}

	public void updateUser(User arg0) {
		// TODO Auto-generated method stub
		userDAO.updateUser(arg0);
	}

	public User getUserById(Integer arg0) {
		// TODO Auto-generated method stub
		return userDAO.getUserById(arg0);
	}

	public List<User> getUsersByHql(String hql) {
		// TODO Auto-generated method stub
		return userDAO.getUsersByHql(hql);
	}

	public List<User> getList(String hql,Map<String,Object> arg) {
		// TODO Auto-generated method stub
		return this.userDAO.getList(hql,arg);
	}

}
