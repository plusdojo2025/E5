package model;

import java.io.Serializable;

public class Users implements Serializable{
	private String username; // ログイン時のユーザーネーム

	public String getUsername() {
		return username;
	}

	public void setUserUsername(String username) {
		this.username = username;
	}

	public Users() {
		this(null);
	}

	public Users(String username) {
		this.username = username;
	}
}
