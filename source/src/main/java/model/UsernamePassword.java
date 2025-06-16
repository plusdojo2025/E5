package model;

import java.io.Serializable;

public class UsernamePassword implements Serializable{
	private String username; // ID
	private String password; // パスワード

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

	public  UsernamePassword(String username) {
		this.username = username;
	}

	public  UsernamePassword() {
		this.username = "";
		this.password = "";
	}
}
