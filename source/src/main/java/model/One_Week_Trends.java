package model;

import java.io.Serializable;

public class One_Week_Trends implements Serializable {
	private int owt_id;                //週の傾向id
	private String owt;                //週の傾向
	private String owt_comments;       //週のコメント
	private String owt_stress_factor;  //一番高いストレスの傾向
	
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
	
	public One_Week_Trends(int owt_id, String owt, String owt_comments, String owt_stress_factor) {
		super();
		this.owt_id = owt_id;
		this.owt = owt;
		this.owt_comments = owt_comments;
		this.owt_stress_factor = owt_stress_factor;
	}
}
