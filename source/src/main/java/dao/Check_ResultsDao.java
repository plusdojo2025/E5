package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Check_Results;

public class Check_ResultsDao {
	public List<Check_Results> check_results(Check_Results card) {
		Connection conn = null;
		List<Check_Results> cardList = new ArrayList<Check_Results>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT stress_score, question1, question2, question3, question4, "
					+ "question5, question6, question7, question8, question9, question10 "
					+ "FROM check_results "
					+ "WHERE userid LIKE ? AND created_at LIKE ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%" + card.getUserid() + "%");
			pStmt.setString(2, "%" + card.getCreated_at() + "%");

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Check_Results bc = new Check_Results(
						rs.getInt("check_results_id"),
						rs.getInt("stress_score"), 
						rs.getInt("question1"),
						rs.getInt("question2"),
						rs.getInt("question3"),
						rs.getInt("question4"),
						rs.getInt("question5"),
						rs.getInt("question6"),
						rs.getInt("question7"),
						rs.getInt("question8"),
						rs.getInt("question9"),
						rs.getInt("question10"),
						rs.getString("comments"),
						rs.getString("advice")
						);
				cardList.add(bc);
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
