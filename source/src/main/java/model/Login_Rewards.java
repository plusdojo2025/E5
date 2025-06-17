package model;

public class Login_Rewards {
	private int userid;//ユーザーid
	private int login_date;//ログイン継続日数	
	
	public Login_Rewards(int userid, int login_date) {
		this.userid = userid;
		this.login_date = login_date;
	}
	
	public Login_Rewards() {
		this.userid = 0;
		this.login_date = 0;
	}

	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getLogin_date() {
		return login_date;
	}
	
	public void setLogin_date(int login_date) {
		this.login_date = login_date;
	}

}
