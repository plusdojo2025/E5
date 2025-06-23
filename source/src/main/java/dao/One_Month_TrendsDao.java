package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.One_Month_Trends;

public class One_Month_TrendsDao {
	public One_Month_Trends selectByScoreAndTrend(int stressScore, String monthmaxtrend) {
		Connection conn = null;
		One_Month_Trends trendComment = null;
		
		try {
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			
			// 一番大きいストレス項目の週の傾向と週のコメントを取得する
			String sql = "SELECT omt FROM one_month_trends "
					+ "WHERE omt_stress_factor = ? ORDER BY RAND() LIMIT 1";
			// 接続する情報とSQLの情報をまとめてpStmtに入れている
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//選択された日付を?に入れてSQL文を完成させる
			pStmt.setString(1, monthmaxtrend);

			ResultSet rs = pStmt.executeQuery();
			

			if (rs.next()) {
				trendComment = new One_Month_Trends();
				trendComment.setOmt(rs.getString("omt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			trendComment = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(); // // デバッグ情報としてコンソールに詳細を出力
					System.out.println("エラーが起きました！！"); // ユーザー向けのメッセージ
					trendComment = null;
				}
			}
		}
		//結果を返す
		return trendComment;
	}
}
