package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserItems;

public class UserItemsDao {
	// 1人分のユーザーアイテムを取得
	public UserItems findByUserId(int userid) {
		Connection conn = null;
        UserItems item = null;

        try {
        	// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/E5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
            String sql = "SELECT * FROM useritems WHERE userid = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userid);
			
            
         // SQL文を実行し、結果を取得する
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                item = new UserItems(
                    rs.getInt("userid"),
                    rs.getInt("petitems1"),
                    rs.getInt("petitems2"),
                    rs.getInt("petitems3"),
                    rs.getInt("petitems4"),
                    rs.getInt("petitems5"),
                    rs.getInt("petitems6"),
                    rs.getInt("petitems7"),
                    rs.getInt("petitems8")
                );
            }

        } catch (SQLException e) {
	        e.printStackTrace(); // SQLエラー
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace(); // ドライバ未導入
	    } finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
        return item;
    }
	
	// 新規登録（ユーザー登録時に自動で作成）
	// ユーザーIDをもとに、useritemsテーブルに初期アイテムを挿入する
	public void insertInitialUserItems(int userid) {
		Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	    	System.out.println("insertInitialUserItems called for userid: " + userid);
	    	// JDBCドライバを読み込む
	    	Class.forName("com.mysql.cj.jdbc.Driver");

	    	// データベースに接続する
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/E5?"
	    						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	    						"root", "password");
	    	System.out.println("AutoCommit: " + conn.getAutoCommit());
	    	// SQL文を準備する
	        String sql = "INSERT INTO useritems "
	                   + "(userid, petitems1, petitems2, petitems3, petitems4, petitems5, petitems6, petitems7, petitems8) "
	                   + "VALUES (?, 0, 0, 0, 0, 0, 0, 0, 0)";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, userid);
	        pstmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace(); // SQLエラー
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace(); // ドライバ未導入
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	} 

    // 更新（ログイン＆チェックやログインボーナスで増やす、使うで減らす）
    // アイテム数を増やす処理
	public void incrementItem(int userId, int itemNumber) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	    	// JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/E5"
	                + "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	                "root", "password");
	        
	        // SQL文を準備する
	        String sql = "UPDATE useritems SET petitems" + itemNumber + " = petitems" + itemNumber + " + 1 WHERE userid = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, userId);
	        pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace(); // SQLエラー
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace(); // ドライバ未導入
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	// アイテム数を減らす処理
	public void useItem(int userId, int itemId) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/E5"
	                + "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	                "root", "password");

	        // itemId（1〜8）が妥当か確認
	        if (itemId < 1 || itemId > 8) {
	            throw new IllegalArgumentException("Invalid item ID: " + itemId);
	        }

	        // 対象カラム名（例: petitems3）を動的に組み立て
	        String columnName = "petitems" + itemId;

	        // 所持数が 0 より大きい場合にのみ 1 減らす
	        String sql = "UPDATE useritems SET " + columnName + " = CASE WHEN " + columnName + " > 0 THEN " + columnName + " - 1 ELSE 0 END WHERE userid = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, userId);
	        pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace(); // SQLエラー
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace(); // ドライバ未導入
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}