package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Login_Bonus_History implements Serializable{
	 private int userId;			// ユーザーID
	 private LocalDate bonusDate;	// ログインボーナス付与履歴
	
	 // 引数全部のコンストラクタ
	 public Login_Bonus_History(int userId, LocalDate bonusDate) {
		this.userId = userId;
		this.bonusDate = bonusDate;
	}
	 
	// 引数なしのコンストラクタ
	public Login_Bonus_History() {
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public LocalDate getBonusDate() {
		return bonusDate;
	}
	
	public void setBonusDate(LocalDate bonusDate) {
		this.bonusDate = bonusDate;
	}

}
