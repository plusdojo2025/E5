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

	public String getPw() {
		return password;
	}

	public void setPw(String pw) {
		this.password = pw;
	}

	public  UsernamePassword(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public  UsernamePassword() {
		this.username = "";
		this.password = "";
	}
}
