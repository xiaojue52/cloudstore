package com.project.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.constant.Constant;
import com.opensymphony.xwork2.ActionSupport;
import com.project.po.User;
import com.project.service.UserService;
/**
 * 用户登陆相关
 * @author Administrator
 *
 */
public class UserAction extends ActionSupport{

	private static final long serialVersionUID = 7482287939244643007L;
	
	private UserService userService;
	private List<User> list;
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}

	public void addUserAction(){
		String hql = "from User user where user.name = '"+user.getName()+"'";
		if(this.userService.getUsersByHql(hql).size()>0){
			Constant.flush(-1);
		}else{
			this.userService.saveUser(user);
			Constant.flush(1);
		}
	}
	public void getVipAction(){
		User user = this.userService.getUserById(this.user.getId());
		user.setVip("1");
		this.userService.updateUser(user);
		Constant.flush(1);
	}
	public void loginAction(){
		String hql = "from User user where user.name = :name and user.password=:password";
		Map<String,Object> arg = new HashMap<String,Object>();
		arg.put("name", user.getName());
		arg.put("password", user.getPassword());
		List<User> list = this.userService.getList(hql,arg);
		if(list!=null&&list.size()>0){
			Constant.flush(list.get(0));
		}else
			Constant.flush(-1);
	}
}
