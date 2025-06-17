package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UsernamePassword;

public class UsersDao {
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
			String sql = "SELECT count(*) FROM UsernamePassword WHERE username=? AND password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, usernamepassword.getUsername());
			pStmt.setString(2, usernamepassword.getPassword());

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
	
	public boolean insert(UsernamePassword userspassword) {
	    Connection conn = null;
	    
	    try {
	    	// JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?"
	                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");

	        // ユーザーネームの重複チェック
	        String checkSql = "SELECT COUNT(*) FROM users WHERE id = ?";
	        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	        checkStmt.setString(1, userspassword.getUsername());
	        ResultSet rs = checkStmt.executeQuery();
	        rs.next();
	        if (rs.getInt(1) > 0) {
	            return false; // すでにユーザーネームが存在
	        }

	        // 登録処理
	        String sql = "INSERT INTO users (username,password) VALUES (?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, userspassword.getUsername());
	        pStmt.setString(2, userspassword.getPassword());//ハッシュ化する
	        int result = pStmt.executeUpdate();
	        return result >0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } 
	    /*finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            /*e.printStackTrace();
	        }*/
	    }
	
}
