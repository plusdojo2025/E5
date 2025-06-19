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
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
	                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	                "root", "password");

	        // ユーザー名でパスワードのハッシュ値を取得
	        String sql = "SELECT password FROM users WHERE username = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, usernamepassword.getUsername());
	        ResultSet rs = pStmt.executeQuery();

	        // 結果が存在する場合は、パスワードを照合
	        if (rs.next()) {
	            String storedHashedPassword = rs.getString("password");

	            // 入力されたパスワードをSHA-256でハッシュ化
	            String inputHashedPassword = hashSHA256(usernamepassword.getPassword());

	            // ハッシュ値を比較
	            if (storedHashedPassword.equals(inputHashedPassword)) {
	                loginResult = true;
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        // データベースを切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return loginResult;
	}
	
	//　ログインユーザーのIDを取得
	public int findUserIdByUsername(String username) {
	    Connection conn = null;
	    int userId = -1;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/e5?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	            "root", "password");

	        String sql = "SELECT id FROM users WHERE username = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, username);
	        ResultSet rs = pStmt.executeQuery();

	        if (rs.next()) {
	            userId = rs.getInt("id");
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

	    return userId;
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

	        // パスワードをハッシュ化して登録
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
