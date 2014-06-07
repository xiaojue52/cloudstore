package com.project.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContextEvent;

/**
 * 用来读写文件得类
 * @author Administrator
 *
 */
public class FileManager {
	private FileOutputStream fw = null; 
	public void uploadFile(byte[] c,int length,String name){
		try {
			fw.write(c,0,length);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public FileManager(ServletContextEvent sce,String name){
		String path = sce.getServletContext().getRealPath("/")+"file/"+name;
		File file = new File(path);
		try {
			file.createNewFile();
			fw = new FileOutputStream(file); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void close(){
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
