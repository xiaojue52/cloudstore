package com.project.action;


import java.io.File;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import com.constant.Constant;
import com.opensymphony.xwork2.ActionSupport;
import com.project.po.CFile;
import com.project.service.CFileService;
/**
 * 上传文件相关
 **/
public class CFileAction extends ActionSupport{

	private static final long serialVersionUID = 7482287939244643007L;
	
	private CFileService cFileService;
	private List<CFile> list;
	private CFile doc;
	private String path;
	private int tag = 0;
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public CFile getDoc() {
		return doc;
	}
	public void setDoc(CFile doc) {
		this.doc = doc;
	}
	public CFileService getCFileService() {
		return cFileService;
	}
	public void setCFileService(CFileService cFileService) {
		this.cFileService = cFileService;
	}
	public List<CFile> getList() {
		return list;
	}
	public void setList(List<CFile> list) {
		this.list = list;
	}
    public String listCFilesAction(){
    	path = ServletActionContext.getServletContext().getRealPath("/")+"file/";
    	String hql = "from CFile cFile";
    	this.list = this.cFileService.getCFilesByHql(hql);
    	return SUCCESS;
    }
    public String deleteCFileAction(){
    	try{
    		String path = ServletActionContext.getServletContext().getRealPath("/")+"file/";
    		System.out.println(path);
    		File file = new File(path+doc.getName());
    		if(!file.delete()){
    			return ERROR;
    		}
    		this.cFileService.deleteCFile(doc);
    		return SUCCESS;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return ERROR;
    }
	public void addCFileAction(){
		//System.out.println(doc.getName());
		String hql = "from CFile cFile where cFile.name = '"+doc.getName()+"'";
		if(this.cFileService.getCFilesByHql(hql).size()>0){
			Constant.flush(-1);
		}else{
			this.doc.setCreatetime(Constant.getCurrentDataString("yyyy-MM-dd HH:mm:ss"));
			this.cFileService.saveCFile(doc);
			Constant.flush(1);
		}
	}
}
