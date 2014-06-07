package com.project.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import com.project.base.Constant;
/**
 * 上传数据文件
 * @author Administrator
 *
 */
public class SocketClient {
	private Socket so = null;
	private Integer sotimeout=1*1*10;//
	private String name;
	private String path;
	public SocketClient(String name,String path){
		this.name = name;
		this.path = path;
		try {
			InetAddress address = InetAddress.getByName(Constant.host);
			so = new Socket(address,8000);
			so.setKeepAlive(true);
			so.setSoTimeout(sotimeout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int send(){

		try {
			DataInputStream reader = new DataInputStream(new FileInputStream(path));
			PrintWriter out = new PrintWriter(so.getOutputStream(), true);
			out.println(name);
			out.flush();
			DataOutputStream sendout = new DataOutputStream(so.getOutputStream()); 
			byte[] sendbyte = new byte[1024];
			int length = 0;
			while((length=reader.read(sendbyte,0,sendbyte.length))>0){
				if(!so.isConnected()){
					reader.close();
					return -1;
				}
				sendout.write(sendbyte,0,length);
				sendout.flush();
			}
			so.close();
			reader.close();
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}

}
