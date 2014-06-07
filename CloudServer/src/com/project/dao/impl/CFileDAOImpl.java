package com.project.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.project.dao.CFileDAO;
import com.project.po.CFile;
/**
 * 对数据库接口得调用
 * @author Administrator
 *
 */
public class CFileDAOImpl extends HibernateDaoSupport implements CFileDAO {


	public void deleteCFile(CFile arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
	}

	public void updateCFile(CFile arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}

	public CFile getCFileById(Integer arg0) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CFile.class, arg0);
	}

	@SuppressWarnings("unchecked")
	public List<CFile> getCFilesByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(hql);
	}

	public void saveCFile(CFile arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	public List<CFile> getList(String hql) {
		// TODO Auto-generated method stub
		return null;
	}
}
