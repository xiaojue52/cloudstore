package com.init.listener;

import javax.servlet.ServletContextEvent;
//import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.WebApplicationContextUtils;
import com.project.socket.SocketService;

/**
 * ≥Ã–Ú≥ı ºªØ
 * @author Administrator
 *
 */
public class InitProjectListener extends ContextLoaderListener{
	private SocketService socketService = null;
	public void contextInitialized(ServletContextEvent event) {  
	    super.contextInitialized(event);  
	    //ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
	    //ClassRoomService initData = (ClassRoomService) applicationContext.getBean("ClassRoomService");
	    socketService = new SocketService(event);
	    socketService.startService();
	}
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		// TODO Auto-generated method stub
		socketService.stopService();
	}
}
