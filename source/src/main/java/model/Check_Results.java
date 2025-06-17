package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Check_Results implements Serializable{
    private int userid;
    private int check_results_id;
    private int stress_score;
	private int question1;
    private int question2;
    private int question3;
    private int question4;
    private int question5;
    private int question6;
    private int question7;
    private int question8;
    private int question9;
    private int question10;
    private LocalDate created_at;
    private String stress_factor;
    private String comments;
    private String advice;
    
    public Check_Results(int check_results_id, int stress_score, int question1, int question2,
    		int question3, int question4, int question5, int question6, int question7, int question8,
    		int question9, int question10, String comments, String advice) {
    	this.check_results_id = check_results_id;
    	this.stress_score = stress_score;
    	this.question1 = question1;
    	this.question2 = question2;
    	this.question3 = question3;
    	this.question4 = question4;
    	this.question5 = question5;
    	this.question6 = question6;
    	this.question7 = question7;
    	this.question8 = question8;
    	this.question9 = question9;
    	this.question10 = question10;
    	this.comments = comments;
    	this.advice = advice;
    }
    
    public Check_Results(int check_results_id, int stress_score, int question1, int question2,
    		int question3, int question4, int question5, int question6, int question7, int question8,
    		int question9, int question10, String stress_factor) {
    	this.check_results_id = check_results_id;
    	this.stress_score = stress_score;
    	this.question1 = question1;
    	this.question2 = question2;
    	this.question3 = question3;
    	this.question4 = question4;
    	this.question5 = question5;
    	this.question6 = question6;
    	this.question7 = question7;
    	this.question8 = question8;
    	this.question9 = question9;
    	this.question10 = question10;
    	this.stress_factor = stress_factor;
    }


    public Check_Results(int userid, int check_results_id, int stress_score, int question1, int question2,
			int question3, int question4, int question5, int question6, int question7, int question8, int question9,
			int question10, String stress_factor) {
		this.userid = userid;
		this.check_results_id = check_results_id;
		this.stress_score = stress_score;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.question5 = question5;
		this.question6 = question6;
		this.question7 = question7;
		this.question8 = question8;
		this.question9 = question9;
		this.question10 = question10;
		this.stress_factor = stress_factor;
	}
    
    public Check_Results(int stress_score, String stress_factor) {
    	this.stress_score = stress_score;
    	this.stress_factor = stress_factor;
    }
    
    public Check_Results(String comments, String advice) {
    	this.comments = comments;
    	this.advice = advice;
    }

	public Check_Results(int stress_score, int question1) {
    	this.stress_score = stress_score;
    	this.question1 = question1;
    }
    
    public Check_Results(int userid, int stress_score, int question1) {
    	this.stress_score = stress_score;
    	this.question1 = question1;
    }
    
    public Check_Results(int userid, LocalDate created_at) {
    	this.userid = userid;
    	this.created_at = created_at;
    }
    
    public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCheck_results_id() {
		return check_results_id;
	}
	public void setCheck_results_id(int check_results_id) {
		this.check_results_id = check_results_id;
	}
	public int getStress_score() {
		return stress_score;
	}
	public void setStress_score(int stress_score) {
		this.stress_score = stress_score;
	}
	public int getQuestion1() {
		return question1;
	}
	public void setQuestion1(int question1) {
		this.question1 = question1;
	}
	public int getQuestion2() {
		return question2;
	}
	public void setQuestion2(int question2) {
		this.question2 = question2;
	}
	public int getQuestion3() {
		return question3;
	}
	public void setQuestion3(int question3) {
		this.question3 = question3;
	}
	public int getQuestion4() {
		return question4;
	}
	public void setQuestion4(int question4) {
		this.question4 = question4;
	}
	public int getQuestion5() {
		return question5;
	}
	public void setQuestion5(int question5) {
		this.question5 = question5;
	}
	public int getQuestion6() {
		return question6;
	}
	public void setQuestion6(int question6) {
		this.question6 = question6;
	}
	public int getQuestion7() {
		return question7;
	}
	public void setQuestion7(int question7) {
		this.question7 = question7;
	}
	public int getQuestion8() {
		return question8;
	}
	public void setQuestion8(int question8) {
		this.question8 = question8;
	}
	public int getQuestion9() {
		return question9;
	}
	public void setQuestion9(int question9) {
		this.question9 = question9;
	}
	public int getQuestion10() {
		return question10;
	}
	public void setQuestion10(int question10) {
		this.question10 = question10;
	}
	public LocalDate getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}
	public String getStress_factor() {
		return stress_factor;
	}
	public void setStress_factor(String stress_factor) {
		this.stress_factor = stress_factor;
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


}
