package com.project;

import com.project.structure.User;

import android.app.Application;
/**
 * ��������ȫ�ֱ���
 * @author Administrator
 *
 */
public class MainApplication extends Application{
      
    private User user;
    public boolean isNew = true;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
      
}