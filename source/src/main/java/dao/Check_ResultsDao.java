package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Check_Results;

public class Check_ResultsDao {
	public List<Check_Results> check_results(Check_Results card) {
		Connection conn = null;
		List<Check_Results> cardList = new ArrayList<Check_Results>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT check_results_id, stress_score, question1, question2, question3, question4, "
					+ "question5, question6, question7, question8, question9, question10, stress_factor "
					+ "FROM check_results "
					+ "WHERE userid = ? AND created_at BETWEEN ? AND ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, card.getUserid());
			pStmt.setDate(2,  java.sql.Date.valueOf(card.getCreated_at()));
			pStmt.setDate(3,  java.sql.Date.valueOf(card.getCreated_at().plusDays(1)));

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Check_Results bc = new Check_Results(
						rs.getInt("check_results_id"),
						rs.getInt("stress_score"), 
						rs.getInt("question1"),
						rs.getInt("question2"),
						rs.getInt("question3"),
						rs.getInt("question4"),
						rs.getInt("question5"),
						rs.getInt("question6"),
						rs.getInt("question7"),
						rs.getInt("question8"),
						rs.getInt("question9"),
						rs.getInt("question10"),
						rs.getString("stress_factor")
						);
				cardList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
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

		// 結果を返す
		return cardList;
	}
	
//	public List<Check_Results> check_comments(Check_Results card) {
//		Connection conn = null;
//		List<Check_Results> cardList = new ArrayList<Check_Results>();
//
//		try {
//			// JDBCドライバを読み込む
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			// データベースに接続する
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
//					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//					"root", "password");
//
//			// SQL文を準備する
//			String sql = "SELECT stress_score, question1, question2, question3, question4, "
//					+ "question5, question6, question7, question8, question9, question10, stress_factor "
//					+ "FROM check_results "
//					+ "WHERE userid LIKE ? AND created_at LIKE ? ";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			
//			pStmt.setString(1, "%" + card.getUserid() + "%");
//			pStmt.setString(2, "%" + card.getCreated_at() + "%");
//
//			// SELECT文を実行し、結果表を取得する
//			ResultSet rs = pStmt.executeQuery();
//
//			// 結果表をコレクションにコピーする
//			while (rs.next()) {
//				Check_Results bc = new Check_Results(
//						rs.getInt("check_results_id"),
//						rs.getInt("stress_score"), 
//						rs.getInt("question1"),
//						rs.getInt("question2"),
//						rs.getInt("question3"),
//						rs.getInt("question4"),
//						rs.getInt("question5"),
//						rs.getInt("question6"),
//						rs.getInt("question7"),
//						rs.getInt("question8"),
//						rs.getInt("question9"),
//						rs.getInt("question10"),
//						rs.getString("stress_factor")
//						);
//				cardList.add(bc);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			cardList = null;
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			cardList = null;
//		} finally {
//			// データベースを切断
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					cardList = null;
//				}
//			}
//		}
//
//		// 結果を返す
//		return cardList;
//	}
	
	
	
	public boolean check_insert(Check_Results card) {
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
			String sql = "INSERT INTO check_results (\r\n"
					+ "		    userid, stress_score,\r\n"
					+ "		    question1, question2, question3, question4, question5,\r\n"
					+ "		    question6, question7, question8, question9, question10,\r\n"
					+ "		    created_at, stress_factor\r\n"
					+ "		) VALUES (\r\n"
					+ "		    ?,               \r\n"
					+ "		    ?,				\r\n"
					+ "		    ?, ?, ?, ?, ?,  \r\n"
					+ "		    ?, ?, ?, ?, ?,  \r\n"
					+ "		    NOW(),          \r\n"
					+ "		    ?  \r\n"
					+ "		);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, card.getUserid());
	        pStmt.setInt(2, card.getStress_score());
	        pStmt.setInt(3, card.getQuestion1());
	        pStmt.setInt(4, card.getQuestion2());
	        pStmt.setInt(5, card.getQuestion3());
	        pStmt.setInt(6, card.getQuestion4());
	        pStmt.setInt(7, card.getQuestion5());
	        pStmt.setInt(8, card.getQuestion6());
	        pStmt.setInt(9, card.getQuestion7());
	        pStmt.setInt(10, card.getQuestion8());
	        pStmt.setInt(11, card.getQuestion9());
	        pStmt.setInt(12, card.getQuestion10());
	        pStmt.setString(13, card.getStress_factor());
			
//			// SELECT文を実行し、結果表を取得する
//			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
	        if (pStmt.executeUpdate() == 1) {
	            result = true;
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

		// 結果を返す
		return result;
	}
	
	public LocalDate getLastCheckDate(int userid, LocalDate today) {
		Connection conn = null;
		LocalDate lgday = null;
		java.sql.Date loginday;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT created_at FROM check_results WHERE userid = ? AND created_at BETWEEN ? AND ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userid);
			pStmt.setDate(2,  java.sql.Date.valueOf(today));
			pStmt.setDate(3,  java.sql.Date.valueOf(today.plusDays(1)));
			
//			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {  // 1行目があれば
				loginday = rs.getDate("created_at");
			    if (loginday != null) {
			        lgday = loginday.toLocalDate();
			    } else {
			        lgday = null;
			    }
			} else {
			    lgday = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

		// 結果を返す
		return lgday;
	}
	
	public List<Check_Results> week_check_results(Check_Results card) {
		Connection conn = null;
		List<Check_Results> cardList = new ArrayList<Check_Results>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT stress_score,stress_factor FROM check_results WHERE userid = ? AND created_at BETWEEN ? AND ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			LocalDate startdate = card.getStartday();  // LocalDate型で日付があると仮定
			java.sql.Date sqlDate = java.sql.Date.valueOf(startdate);
			LocalDate enddate = card.getEndday();  // LocalDate型で日付があると仮定
			java.sql.Date sqlDate2 = java.sql.Date.valueOf(enddate);
			
			pStmt.setInt(1, card.getUserid());
			pStmt.setDate(2, sqlDate);
			pStmt.setDate(3, sqlDate2);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Check_Results bc = new Check_Results(
						rs.getInt("stress_score"),
						rs.getString("stress_factor")
						);
				cardList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
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

		// 結果を返す
		return cardList;
	}
	
	public List<Check_Results> month_check_results(Check_Results card) {
		Connection conn = null;
		List<Check_Results> cardList = new ArrayList<Check_Results>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT stress_score,stress_factor FROM check_results WHERE userid = ? AND created_at BETWEEN ? AND ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			LocalDate startdate = card.getStartday();  // LocalDate型で日付があると仮定
			java.sql.Date sqlDate = java.sql.Date.valueOf(startdate);
			LocalDate enddate = card.getEndday();  // LocalDate型で日付があると仮定
			java.sql.Date sqlDate2 = java.sql.Date.valueOf(enddate);
			
			pStmt.setInt(1, card.getUserid());
			pStmt.setDate(2, sqlDate);
			pStmt.setDate(3, sqlDate2);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Check_Results bc = new Check_Results(
						rs.getInt("stress_score"),
						rs.getString("stress_factor")
						);
				cardList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
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

		// 結果を返す
		return cardList;
	}
	
	public List<Check_Results> findByUserIdAndDate(int userid, LocalDate today) {
		Connection conn = null;
		List<Check_Results> cardList = new ArrayList<Check_Results>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT stress_score,stress_factor \"\r\n"
					+ "					 \"FROM check_results \"\r\n"
					+ "					 \"WHERE userid = ? AND created_at BETWEEN ? AND ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, userid);
			pStmt.setDate(2,  java.sql.Date.valueOf(today));
			pStmt.setDate(3,  java.sql.Date.valueOf(today.plusDays(1)));


			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Check_Results bc = new Check_Results(
						rs.getInt("stress_score"),
						rs.getString("stress_factor")
						);
				cardList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
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

		// 結果を返す
		return cardList;
	}
	
	public boolean hasCheckResultToday(int userid, LocalDate today) {
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
			String sql = "SELECT stress_score,created_at FROM check_results WHERE userid = ? AND created_at BETWEEN ? AND ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, userid);
			pStmt.setDate(2,  java.sql.Date.valueOf(today));
			pStmt.setDate(3,  java.sql.Date.valueOf(today.plusDays(1)));
			
//			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
	        if (rs.next()) {
	            result = true;
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

		// 結果を返す
		return result;
	}
}
