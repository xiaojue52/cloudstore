package com.project.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
/**
 * 启动socket服务来接受文件
 * @author Administrator
 *
 */
public class SocketService extends Thread{
	private int port = 8000;
	private ServerSocket server = null;
	private ServletContextEvent sce = null;
	private boolean stop = false; 
	private List<Socket> list = new ArrayList<Socket>();
	public void startService(){
		this.start();
	}
	public void stopService(){
		stop = true;
		try {
			server.close();
			for(int i=0;i<list.size();i++){
				list.get(i).close();
			}
			list.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SocketService(ServletContextEvent sce){
		this.sce = sce;
		try{
			server = new ServerSocket(port);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){
		while(!stop){
			Socket client = null;
			try{
				client = server.accept();
				invoke(client);
			}catch(Exception e){
				break;
			}
		}
	}
	private void invoke(final Socket so){
		list.add(so);
		
		new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub		
				try{
					BufferedReader in = new BufferedReader(new InputStreamReader(	
							so.getInputStream(),"utf-8"));
					String name = null;			
					if((name=in.readLine())==null){	
						so.close();
						in.close();
						return;
					}
					//in.close();
					DataInputStream dataIn = new DataInputStream(	
							so.getInputStream());
					System.out.println(name);
					FileManager fm = new FileManager(sce,name);
					byte[] inputByte = new byte[1024];
					int length = 0;
					while((length = dataIn.read(inputByte,0,inputByte.length))>0){
						fm.uploadFile(inputByte,length,name);
					}
					fm.close();
					dataIn.close();
					so.close();
					list.remove(so);
				}catch(Exception e){
					e.printStackTrace();
					try {
						so.close();
						list.remove(so);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		}).start();
	}
}
