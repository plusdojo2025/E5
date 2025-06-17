package model;

import java.io.Serializable;

public class UsernamePassword implements Serializable{
	private int id; //ID
	private String username; // ユーザーネーム
	private String password; // パスワード
	
	public  UsernamePassword(int id,String username ,String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public  UsernamePassword() {
		this.id = 0;
		this.username = "";
		this.password = "";
	}
	
	public int getId() {
		return id;
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


	public  UsernamePassword(String username,String password) {
		this.username = username;
		this.password = password;
	
	}
}