package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.One_Week_Trends;

public class One_Week_TrendsDao {
	public List<One_Week_Trends> One_Week_Trends(One_Week_Trends card) {
		Connection conn = null;
		List<One_Week_Trends> cardList = new ArrayList<One_Week_Trends>();
		
		try {
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//SQL文を準備する
			//選択された日付が含まれる月～日までのチェック結果スコアの検索
			String sql = "SELECT stress_score FROM check_results "
					+ "WHERE check_date BETWEEN DATE_SUB('?', INTERVAL DAYOFWEEK('?') - 2 DAY)"
					+ "AND DATE_ADD(DATE_SUB('?', INTERVAL DAYOFWEEK('?') - 2 DAY), INTERVAL 6 DAY)";
			// 接続する情報とSQLの情報をまとめてpStmtに入れている
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//選択された日付を?に入れてSQL文を完成させる まだselectedDateが無いためコメントアウト中
//			pStmt.setString(1, selectedDate);
//			pStmt.setString(2, selectedDate);
//			pStmt.setString(3, selectedDate);
//			pStmt.setString(4, selectedDate);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			cardList = null;
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
		//結果を返す
		return cardList;
	}
}
