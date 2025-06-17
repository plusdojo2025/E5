package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Check_Comments;

public class Check_CommentsDao {
	
	// 傾向（例：精神的ストレス）とスコア（例：67）からコメント1件取得(最大、最小、傾向で調べたレコードを返す）
	public Check_Comments selectByScoreAndTrend(int score, String trend) {
		Connection conn = null;
		Check_Comments result = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT comments, advice, pet_check_comments "
					+ "FROM check_comments "
					+ "WHERE min_score <= ? AND max_score >= ? AND trends = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, score);
			pStmt.setInt(2, score);
			pStmt.setString(3, trend);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				result = new Check_Comments(
							   rs.getString("comments"),
							   rs.getString("advice"),
							   rs.getString("pet_check_comments"));
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
		//return cardList;
		return result;
	}

}
