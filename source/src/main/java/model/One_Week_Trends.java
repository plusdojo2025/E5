package model;

import java.io.Serializable;
import java.util.List;

public class One_Week_Trends implements Serializable {
	private int owt_id;                //週の傾向id
	private String owt;                //週の傾向
	private String owt_comments;       //週のコメント
	private String owt_stress_factor;  //一番高いストレスの傾向
	private List<Integer> weekScore;
	private String monday;
	private String sunday;
	
	public int getOwt_id() {
		return owt_id;
	}
	public void setOwt_id(int owt_id) {
		this.owt_id = owt_id;
	}
	
	public String getOwt() {
		return owt;
	}
	public void setOwt(String owt) {
		this.owt = owt;
	}
	
	public String getOwt_comments() {
		return owt_comments;
	}
	public void setOwt_comments(String owt_comments) {
		this.owt_comments = owt_comments;
	}
	
	public String getOwt_stress_factor() {
		return owt_stress_factor;
	}
	public void setOwt_stress_factor(String owt_stress_factor) {
		this.owt_stress_factor = owt_stress_factor;
	}
	
	public List<Integer> getWeekScore() {
		return weekScore;
	}
	public void setWeekScore(List<Integer> weekScore) {
		this.weekScore = weekScore;
	}
	
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	
	public One_Week_Trends(int owt_id, String owt, String owt_comments, String owt_stress_factor,
			List<Integer> weekScore, String monday, String sunday) {
		super();
		this.owt_id = owt_id;
		this.owt = owt;
		this.owt_comments = owt_comments;
		this.owt_stress_factor = owt_stress_factor;
		this.weekScore = weekScore;
		this.monday = monday;
		this.sunday = sunday;
	}
}
