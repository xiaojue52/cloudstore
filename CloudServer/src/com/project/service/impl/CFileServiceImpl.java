package com.project.service.impl;

import java.util.List;

import com.project.dao.CFileDAO;
import com.project.po.CFile;
import com.project.service.CFileService;
/**
 * 数据库事物管理
 * @author Administrator
 *
 */
public class CFileServiceImpl implements CFileService {

	private CFileDAO cFileDAO;
	
	public void setCFileDAO(CFileDAO cFileDAO) {
		this.cFileDAO = cFileDAO;
	}

	public void saveCFile(CFile arg0) {
		// TODO Auto-generated method stub
		cFileDAO.saveCFile(arg0);
	}

	public void deleteCFile(CFile arg0) {
		// TODO Auto-generated method stub
		cFileDAO.deleteCFile(arg0);
	}

	public void updateCFile(CFile arg0) {
		// TODO Auto-generated method stub
		cFileDAO.updateCFile(arg0);
	}

	public CFile getCFileById(Integer arg0) {
		// TODO Auto-generated method stub
		return cFileDAO.getCFileById(arg0);
	}

	public List<CFile> getCFilesByHql(String hql) {
		// TODO Auto-generated method stub
		return cFileDAO.getCFilesByHql(hql);
	}

	public List<CFile> getList(String hql) {
		// TODO Auto-generated method stub
		return this.cFileDAO.getList(hql);
	}

}
