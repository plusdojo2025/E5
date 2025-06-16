package model;

public class One_Month_Trends {
	private int omt_id;//月の傾向id
	private String omt;//月の傾向
	
	public One_Month_Trends(int omt_id, String omt) {
		this.omt_id = omt_id;
		this.omt = omt;
	}
	
	public One_Month_Trends() {
		this.omt_id = 0;
		this.omt = "";
	}

	public int getOmt_id() {
		return omt_id;
	}
	
	public void setOmt_id(int omt_id) {
		this.omt_id = omt_id;
	}
	
	public String getOmt() {
		return omt;
	}
	
	public void setOmt(String omt) {
		this.omt = omt;
	}

}
