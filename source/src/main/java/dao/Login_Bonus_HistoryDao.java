package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Login_Bonus_History;

public class Login_Bonus_HistoryDao {
	
	// その日ログインボーナスをもらったか確認
	public boolean hasReceivedBonusToday(int userId, LocalDate date) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT * FROM login_bonus_history "
					+ "WHERE user_id = ? AND bonus_date = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, userId);
			pStmt.setDate(2, Date.valueOf(date));
				
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			result = rs.next(); // 該当データがあれば true
			
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
	
	// ボーナス取得履歴を登録
	public boolean insertBonusRecord(Login_Bonus_History record) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO login_bonus_history "
					+ "(user_id, bonus_date) VALUES (?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, record.getUserId());
			pStmt.setDate(2, Date.valueOf(record.getBonusDate()));
					
			// SQL文を実行し、結果表を取得する
			int rowsAffected = pStmt.executeUpdate();
            result = rowsAffected > 0;
            
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
