package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Check_Comments;

public class Check_CommentsDao {
	
	// 引数card指定された項目で検索して、取得されたデータのリストを返す(最大、最小、傾向で調べたレコードを返す）
	public List<Check_Comments> select(Check_Comments card) {
		Connection conn = null;
		List<Check_Comments> cardList = new ArrayList<Check_Comments>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT comments, advice, pet_check_comments "
					+ "FROM Bc "
					+ "WHERE min_score <= ? AND max_score >= ? AND trends = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			// スコア（min/maxどちらもscoreとして使う）、傾向で絞り込み
			int score = card.getMin_score();  // min_scoreにスコア（例: 67）を格納していると仮定
			pStmt.setInt(1, score);
			pStmt.setInt(2, score);
			pStmt.setString(3, card.getTrends());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Check_Comments c = new Check_Comments(
							   rs.getString("comments"),
							   rs.getString("advice"),
							   rs.getString("pet_check_comments"));
				cardList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}

}
