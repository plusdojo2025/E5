package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login_Rewards;

public class Login_RewardsDao {

	// ユーザーのログイン継続情報を取得（ユーザーIDで検索）
	public Login_Rewards findByUserId(int userId) {
		Connection conn = null;
		Login_Rewards result = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/e5?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT login_date, last_login "
					+ "FROM login_rewards WHERE userid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, userId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			if (rs.next()) {
				int loginDate = rs.getInt("login_date");
				result = new Login_Rewards(userId, loginDate);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	// ログイン継続日数を更新（加算やリセット）（上書き）
	public boolean updateLoginStreak(int userId, int loginDate) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/e5?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "UPDATE login_rewards "
					+ "SET login_date = ? WHERE userid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, loginDate);
			pStmt.setInt(2, userId);

			// SQL文を実行し、結果表を取得する
			int rowsAffected = pStmt.executeUpdate();
			result = rowsAffected > 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	// ログイン継続日数をリセット（0にする）
	public boolean resetLoginStreak(int userId) {
		return updateLoginStreak(userId, 0);
	}
	
	public void insertLoginStreak(int userId, int streak) {
	    Connection conn = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root", "password");

	        String sql = "INSERT INTO login_rewards (userid, login_date) VALUES (?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setInt(1, userId);
	        pStmt.setInt(2, streak);

	        pStmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}
