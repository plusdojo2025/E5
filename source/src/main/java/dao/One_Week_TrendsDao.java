package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.One_Week_Trends;

public class One_Week_TrendsDao {
	public List<One_Week_Trends> One_Week_Trends(One_Week_Trends weekTrends, String selectedDate) {
		Connection conn = null;
		List<One_Week_Trends> cardList = new ArrayList<One_Week_Trends>();
		
		try {
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//SQL文を準備する
			//選択された日付が含まれる月～日までのチェック結果スコアの検索
			String sql = "SELECT "
					+ "DATE_SUB(?, INTERVAL DAYOFWEEK(?) - 2 DAY) AS monday, "
					+ "DATE_ADD(DATE_SUB(?, INTERVAL DAYOFWEEK(?) - 2 DAY), INTERVAL 6 DAY) AS sunday "
					+ "FROM DUAL";
			// 接続する情報とSQLの情報をまとめてpStmtに入れている
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//選択された日付を?に入れてSQL文を完成させる まだselectedDateが無いためコメントアウト中
			pStmt.setString(1, selectedDate); // 選択された日付をプレースホルダーに設定
			pStmt.setString(2, selectedDate); // 月曜と日曜の算出に使用
			pStmt.setString(3, selectedDate); 
			pStmt.setString(4, selectedDate); 
			
			// mondayとsundayを格納する
			// SQL文を実行し、結果表を取得する
			// ResultSet型　なんでも受け付ける
			// rsにpStmtを表にしたものを入れる　つまりrsは表
			ResultSet rs = pStmt.executeQuery();
			String monday = null;
			String sunday = null;
			
			if (rs.next()) {
				monday = rs.getString("monday");
				sunday = rs.getString("sunday");
				// mondayとsundayを取得できているかの確認用
//				System.out.println(monday + sunday);
			}
			
			// 選択された日付が含まれる月～日までのチェック結果スコアを取得する
			String sql2 = "SELECT stress_score, stress_factor FROM check_results "
					+ "WHERE created_at BETWEEN ? AND ?";
			// 接続する情報とSQLの情報をまとめてpStmtに入れている
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			
			//選択された日付を?に入れてSQL文を完成させる
			pStmt2.setString(1, monday);
			pStmt2.setString(2, sunday);

			ResultSet rs2 = pStmt2.executeQuery();
			
			// 7日分のスコアを格納するリスト
			List<Integer> weekScore = new ArrayList<>();
			
			while (rs2.next()) {
				int score = rs2.getInt("stress_score"); // スコア取得
				weekScore.add(score);                   // リストに追加
			}
			
			public Map<String, Object> getWeekData(String selectedDate) {
				Map<String, Object> dataMap = new HashMap<>();
			    // 必要なデータを追加する
			    dataMap.put("weekScore", weekScore); // ローカルまたはフィールド変数を参照
			    dataMap.put("monday", monday);
			    dataMap.put("sunday", sunday);
			    return dataMap;
			}
			
			// 一番大きいストレス項目のカウント
			String sql3 = "SELECT stress_factor FROM check_results "
					+ "WHERE created_at BETWEEN ? AND ?";
			// 接続する情報とSQLの情報をまとめてpStmtに入れている
			PreparedStatement pStmt3 = conn.prepareStatement(sql3);
			
			//選択された日付を?に入れてSQL文を完成させる
			pStmt3.setString(1, monday);
			pStmt3.setString(2, sunday);

			ResultSet rs3 = pStmt3.executeQuery();
			
			// 7日分のスコアを格納するリスト
			Map<String, Integer> weekSF = new HashMap<>();
			weekSF.put("環境的ストレス", 0);
			weekSF.put("身体的ストレス", 0);
			weekSF.put("生活的ストレス", 0);
			
			// 検索結果からその日の一番大きいストレス項目を取得
			while (rs.next()) {
				String stressFactor = rs3.getString("stress_factor");
				
				// ストレス項目がweekSFのキーと同じならカウントを増やす
				if (weekSF.containsKey(stressFactor)) {
					weekSF.put(stressFactor, weekSF.get(stressFactor) + 1);
				}
			}
			
			// 最もカウント数の多いストレス項目を特定
			String mostFactor = null;
			int maxCount = 0;
			
			for (Map.Entry<String, Integer> entry : weekSF.entrySet()) {
				if (entry.getValue() > maxCount) {
					maxCount = entry.getValue();
					mostFactor = entry.getKey();
				}
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
					e.printStackTrace();
					cardList = null;
				}
			}
		}
		//結果を返す
		return cardList;
	}
	
	
}
