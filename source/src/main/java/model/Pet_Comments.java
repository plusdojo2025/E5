package model;

import java.io.Serializable;

public class Pet_Comments implements Serializable {
	private int pet_comment_id;		//ペットコメントid
	private String pet_comments;	//ペットコメント
	
	public Pet_Comments(int pet_comment_id, String pet_comments) {
		this.pet_comment_id = pet_comment_id;
		this.pet_comments = pet_comments;
	}
	
	public Pet_Comments() {
		this.pet_comment_id = 0;
		this.pet_comments = "";
	}
	
	public int getPet_comment_id() {
		return pet_comment_id;
	}
	
	public void setPet_comment_id(int pet_comment_id) {
		this.pet_comment_id = pet_comment_id;
	}
	
	public String getPet_comments() {
		return pet_comments;
	}
	
	public void setPet_comments(String pet_comments) {
		this.pet_comments = pet_comments;
	}

}
