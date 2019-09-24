package org.skysigh.lulu.admin.po;

import java.io.Serializable;

public class User implements Serializable , Cloneable{

	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	private String password;
	public User() {
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public User clone() {
		User user = null;
		try {
			user = (User) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return user;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + username + ", password=" + password + "]";
	}

}
