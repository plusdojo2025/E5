package model;

import java.io.Serializable;

public class UserItems implements Serializable {
	private int userid;    //ユーザーID
	private int petitems1; //ペットアイテム1
	private int petitems2; //ペットアイテム2
	private int petitems3; //ペットアイテム3
	private int petitems4; //ペットアイテム4
	private int petitems5; //ペットアイテム5
	private int petitems6; //ペットアイテム6
	private int petitems7; //ペットアイテム7
	private int petitems8; //ペットアイテム8
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getPetitems1() {
		return petitems1;
	}
	public void setPetitems1(int petitems1) {
		this.petitems1 = petitems1;
	}
	
	public int getPetitems2() {
		return petitems2;
	}
	public void setPetitems2(int petitems2) {
		this.petitems2 = petitems2;
	}
	
	public int getPetitems3() {
		return petitems3;
	}
	public void setPetitems3(int petitems3) {
		this.petitems3 = petitems3;
	}
	
	public int getPetitems4() {
		return petitems4;
	}
	public void setPetitems4(int petitems4) {
		this.petitems4 = petitems4;
	}
	
	public int getPetitems5() {
		return petitems5;
	}
	public void setPetitems5(int petitems5) {
		this.petitems5 = petitems5;
	}
	
	public int getPetitems6() {
		return petitems6;
	}
	public void setPetitems6(int petitems6) {
		this.petitems6 = petitems6;
	}
	
	public int getPetitems7() {
		return petitems7;
	}
	public void setPetitems7(int petitems7) {
		this.petitems7 = petitems7;
	}
	
	public int getPetitems8() {
		return petitems8;
	}
	public void setPetitems8(int petitems8) {
		this.petitems8 = petitems8;
	}
	
	public UserItems (int userid,int petitems1,int petitems2,int petitems3,int petitems4,int petitems5,int petitems6,int petitems7,int petitems8){
		this.userid = userid;
		this.petitems1 = petitems1;
		this.petitems2 = petitems2;
		this.petitems3 = petitems3;
		this.petitems4 = petitems4;
		this.petitems5 = petitems5;
		this.petitems6 = petitems6;
		this.petitems7 = petitems7;
		this.petitems8 = petitems8;
	}
	
	public UserItems (int userid){
		this.userid = userid;
	}
	
	public UserItems () {}
}
