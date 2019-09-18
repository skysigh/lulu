package org.skysigh.lulu.admin.po;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String account;
	private String password;
	public User() {
	}
	public User(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + "]";
	}

}
