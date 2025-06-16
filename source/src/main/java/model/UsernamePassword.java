package model;

import java.io.Serializable;

public class UsernamePassword implements Serializable{
	private String username; // ID
	private String pw; // パスワード

	public String getId() {
		return username;
	}

	public void setId(String username) {
		this.username = username;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public  UsernamePassword(String username, String pw) {
		this.username = username;
		this.pw = pw;
	}

	public  UsernamePassword() {
		this.username = "";
		this.pw = "";
	}
}
