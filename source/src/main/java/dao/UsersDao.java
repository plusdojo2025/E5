package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UsernamePassword;

public class UsersDao {
	
	//ログイン確認
	// 引数で指定されたログイン成功ならtrueを返す
	public boolean isLoginOK(UsernamePassword usernamepassword) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "SELECT count(*) FROM users WHERE username=? AND password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, usernamepassword.getUsername());
			
			// パスワードをハッシュ化して比較
			String hashedPassword = hashSHA256(usernamepassword.getPassword());
			pStmt.setString(2, hashedPassword);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}
	
	//ユーザー登録
	public boolean insert(UsernamePassword users) {
	    Connection conn = null;
	    
	    try {
	    	// JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
	                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");

	        // ユーザーネームの重複チェック
	        String checkSql = "SELECT COUNT(*) FROM users WHERE username = ?";
	        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	        checkStmt.setString(1, users.getUsername());
	        ResultSet rs = checkStmt.executeQuery();
	        rs.next();
	        if (rs.getInt(1) > 0) {
	            return false; // すでにユーザーネームが存在
	        }

	        // // パスワードをハッシュ化して登録
	        String sql = "INSERT INTO users (username,password) VALUES (?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, users.getUsername());
	        
	        String hashedPassword = hashSHA256(users.getPassword());
			pStmt.setString(2, hashedPassword);
			
	        int result = pStmt.executeUpdate();
	        return result >0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } 
	    finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	// SHA-256ハッシュ化メソッド
		private String hashSHA256(String password) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hashBytes = md.digest(password.getBytes("UTF-8"));
				StringBuilder sb = new StringBuilder();
				for (byte b : hashBytes) {
					sb.append(String.format("%02x", b));
				}
				return sb.toString();
			} catch (Exception e) {
				throw new RuntimeException("パスワードのハッシュ化に失敗しました", e);
			}
		}
		//test
		public boolean isUsernameExists(String username) {
		    boolean exists = false;
		    Connection conn = null;

		    try {
		        // JDBCドライバを読み込む
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        // データベースに接続する
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
		                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
		                "root", "password");

		        // SQLを実行してユーザー名の存在確認
		        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
		        stmt.setString(1, username);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            exists = rs.getInt(1) > 0;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return exists;
		}

}
