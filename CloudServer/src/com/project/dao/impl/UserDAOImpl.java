package com.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.constant.Constant;
import com.project.dao.UserDAO;
import com.project.po.User;
/**
 * 对数据库接口得调用
 * @author Administrator
 *
 */
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	public void saveUser(User arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	public void deleteUser(User arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
	}

	public void updateUser(User arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}

	public User getUserById(Integer arg0) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(User.class, arg0);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(hql);
	}

	public List<User> getList(String hql,Map<String,Object> arg) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(hql);
		query = Constant.setParameters(query, arg);
		return query.list();
	}

}
