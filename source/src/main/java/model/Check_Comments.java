package model;

public class Check_Comments {
	private int comments_advice_id;		//コメント・アドバイスid
	private String comments;			//コメント
	private String advice;				//アドバイス
	private String pet_check_comments;	//キャラクター用コメント
	private String trends;				//傾向
	private int min_score;				//最小
	private int max_score;				//最大
	
	public Check_Comments(int comments_advice_id, String comments, String advice, String pet_check_comments,
			String trends, int min_score, int max_score) {
		this.comments_advice_id = comments_advice_id;
		this.comments = comments;
		this.advice = advice;
		this.pet_check_comments = pet_check_comments;
		this.trends = trends;
		this.min_score = min_score;
		this.max_score = max_score;
	}
	
	public Check_Comments() {
		this.comments_advice_id = 0;
		this.comments = "";
		this.advice = "";
		this.pet_check_comments = "";
		this.trends = "";
		this.min_score = 0;
		this.max_score = 0;
	}

	public int getComments_advice_id() {
		return comments_advice_id;
	}
	
	public void setComments_advice_id(int comments_advice_id) {
		this.comments_advice_id = comments_advice_id;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getAdvice() {
		return advice;
	}
	
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	public String getPet_check_comments() {
		return pet_check_comments;
	}
	
	public void setPet_check_comments(String pet_check_comments) {
		this.pet_check_comments = pet_check_comments;
	}
	
	public String getTrends() {
		return trends;
	}
	
	public void setTrends(String trends) {
		this.trends = trends;
	}
	
	public int getMin_score() {
		return min_score;
	}
	
	public void setMin_score(int min_score) {
		this.min_score = min_score;
	}
	
	public int getMax_score() {
		return max_score;
	}
	
	public void setMax_score(int max_score) {
		this.max_score = max_score;
	}
	

}
