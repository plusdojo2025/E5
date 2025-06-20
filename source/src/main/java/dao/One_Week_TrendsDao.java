package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.One_Week_Trends;

public class One_Week_TrendsDao {
	public List<One_Week_Trends> selectByScoreAndTrend(int stressScore, String weekmaxtrend) {
		Connection conn = null;
		List<One_Week_Trends> cardList = new ArrayList<One_Week_Trends>();
		
		try {
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			// 一番大きいストレス項目の週の傾向と週のコメントを取得する
			String sql2 = "SELECT owt, owt_comments FROM check_results "
					+ "WHERE owt_stress_factor = ?";
			// 接続する情報とSQLの情報をまとめてpStmtに入れている
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			
			//選択された日付を?に入れてSQL文を完成させる
			pStmt2.setString(1, weekmaxtrend);

			ResultSet rs2 = pStmt2.executeQuery();
			
			while (rs2.next()) {
				One_Week_Trends owt = new One_Week_Trends(rs2.getOwt(''))
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(); // // デバッグ情報としてコンソールに詳細を出力
					System.out.println("エラーが起きました！！"); // ユーザー向けのメッセージ
					cardList = null;
				}
			}
		}
		//結果を返す
		return cardList;
	}
}