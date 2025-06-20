package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pet_Comments;

public class Pet_CommentsDao {
	
	public Pet_Comments selectComments(int petComNumber) {
		Connection conn = null;
	    Pet_Comments comment = null;
	    
	    try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT pet_comments_id, pet_comments "
					+ "FROM pet_comments WHERE pet_comments_id = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);

			// SQL文を完成させる
	        pstmt.setInt(1, petComNumber);
				
			// SQL文を実行し、結果表を取得する
	        ResultSet rs = pstmt.executeQuery();

			// 結果表をコレクションにコピーする
	        if (rs.next()) {
                int id = rs.getInt("pet_comments_id");
                String com = rs.getString("pet_comments");
                comment = new Pet_Comments(id, com);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
			//cardList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//cardList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					//cardList = null;
				}
			}
		}

		// 結果を返す
		return comment;
	}

}
