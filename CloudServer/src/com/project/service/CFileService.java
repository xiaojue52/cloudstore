package com.project.service;

import java.util.List;

import com.project.po.CFile;
/**
 * 相关接口函数
 * @author Administrator
 *
 */
public interface CFileService {
	public void saveCFile(CFile arg0);
	public void deleteCFile(CFile arg0);
	public void updateCFile(CFile arg0);
	public CFile getCFileById(Integer arg0);
	public List<CFile> getCFilesByHql(String hql);
	public List<CFile> getList(String hql);
}
