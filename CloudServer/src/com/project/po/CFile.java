package com.project.po;
/**
 * �����Ӧ���ݿ��ֶ�
 * @author Administrator
 *
 */
public class CFile {
	private Integer id;
	private String name;
	private User user;
	private String createtime;
    private String data;//������ݣ��������ݱ�
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
