package servlet;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Check_CommentsDao;
import dao.Check_ResultsDao;
import dao.One_Month_TrendsDao;
import dao.One_Week_TrendsDao;
import model.Check_Results;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public static List<Check_Results> calcPoints(List<Integer> scores) {
        double centerX = 100.0;
        double centerY = 100.0;
        double radius = 100.0;
        int pointCount = scores.size();
        double angleStep = 2 * Math.PI / pointCount;
        
        

        List<Check_Results> check_results = new ArrayList<>();
        for (int i = 0; i < pointCount; i++) {
        	
            Integer currentScore = scores.get(i); // Integer型で取得

            int score;
            if (currentScore == null) {
                score = 0; // nullであれば0に変換する
                // または、その点の描画をスキップするなど、要件に応じた処理
                // 例: continue; // この点計算をスキップする場合
            } else {
                score = currentScore;
            }

            double ratio = (score-3.0) / (15-3);  // scoreが3〜15の範囲を想定
            double angle = angleStep * i - Math.PI / 2;
            double x = centerX + radius * ratio * Math.cos(angle);
            double y = centerY + radius * ratio * Math.sin(angle);
            System.out.printf("score=%d → ratio=%.2f → x=%.1f, y=%.1f%n", score, ratio, x, y);
            check_results.add(new Check_Results(x, y));
        }
        return check_results;
    }
    
 // ポリゴンのd属性用文字列を作る
    public static String makePolygonD(List<Check_Results> check_results) {
        if (check_results.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        Check_Results first = check_results.get(0);
        sb.append("M ").append(first.getX()).append(" ").append(first.getY());
        for (int i = 1; i < check_results.size(); i++) {
            Check_Results p = check_results.get(i);
            sb.append(" L ").append(p.getX()).append(" ").append(p.getY());
        }
        sb.append(" L ").append(first.getX()).append(" ").append(first.getY()); // 閉じる
        return sb.toString();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		LocalDate day;

		// ログインしていない時。
//		if (session.getAttribute("id") == null) {
//			
//			response.sendRedirect(request.getContextPath() +"/LoginServlet");
//			return;
//		}
		
		// ログインしていた時の処理
		request.setCharacterEncoding("UTF-8");
//		int id = (Integer) session.getAttribute("id");
		int id = 1;
		// パラメータから日付取得（例：2025-06-17）
		if (request.getParameter("day") == null || request.getParameter("day").isEmpty()) {
			day = LocalDate.now(); 
		} else {
			try {
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        day = LocalDate.parse(request.getParameter("day"), formatter);
		    } catch (DateTimeParseException e) {
		        // 日付の形式がおかしいときも、今の日付にする or エラー返す
		        day = LocalDate.now();
		        e.printStackTrace(); // ログにも出す
		    }
		}
		
        // 指定日の週の月曜日（週の開始）と日曜日（週の終了）を求める
        LocalDate startofweek = day.with(DayOfWeek.MONDAY);
        LocalDate endofweek = day.with(DayOfWeek.SUNDAY);
		// 日付を受け取り、その日付の月の開始日、終了日を代入する。
        LocalDate startofmonth = day.withDayOfMonth(1);
        LocalDate endofmonth = day.withDayOfMonth(day.lengthOfMonth());
        
		// チェック結果を格納する

		Check_ResultsDao crdao = new Check_ResultsDao();
		List<Check_Results> onedayresult = crdao.check_results(new Check_Results(id,day));
		List<Check_Results> oneweekresult = crdao.week_check_results(new Check_Results(startofweek,endofweek,id));
		List<Check_Results> onemonthresult = crdao.month_check_results(new Check_Results(startofmonth,endofmonth,id));
		//　チェック結果のストレススコア、ストレス傾向を代入する
		
				
		System.out.println(onedayresult);
		System.out.println(oneweekresult);
		
		System.out.println(onemonthresult);
		int stress_score = onedayresult.get(0).getStress_score();
		String stress_factor = onedayresult.get(0).getStress_factor();
		// jspに表示されるように型を変換しています
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

		for (Check_Results result : oneweekresult) {
		    LocalDate date = result.getCreated_at(); // これは LocalDate だよね？
		    if (date != null) {
		        result.setFormattedDate(date.format(formatter));
		    } else {
		        result.setFormattedDate(""); // null 対策
		    }
		}
		
		for (Check_Results result : onemonthresult) {
		    LocalDate date = result.getCreated_at(); // これは LocalDate だよね？
		    if (date != null) {
		        result.setFormattedDate(date.format(formatter));
		    } else {
		        result.setFormattedDate(""); // null 対策
		    }
		}
		
		// jsのレーダーチャート計算に渡すスコア
		int score1 = onedayresult.get(0).getQuestion1() + onedayresult.get(0).getQuestion2() 
				+ onedayresult.get(0).getQuestion3();
		int score2 = onedayresult.get(0).getQuestion4() + onedayresult.get(0).getQuestion5() 
				+ onedayresult.get(0).getQuestion6();
		int score3 = onedayresult.get(0).getQuestion7() + onedayresult.get(0).getQuestion8() 
				+ onedayresult.get(0).getQuestion9();
		
		// レーダーチャートの座標計算
		
		List<Integer> scores = List.of(score3, score2, score1);
	    // 座標計算
	    List<Check_Results> check_results = calcPoints(scores);

	    // d属性作成
	    String polygonD = makePolygonD(check_results);

	    if (check_results == null || polygonD == null) {
	    	
		} else {
			try {
		        
		    } catch (DateTimeParseException e) {
		        // 日付の形式がおかしいときも、今の日付にする or エラー返す
		        day = LocalDate.now();
		        e.printStackTrace(); // ログにも出す
		    }
		}

	    // JSPに渡す用に座標を文字列化（例：JSON形式など）
	    // ここはJSP側で使いやすいように加工する
	    // 例として、JavaScript配列風の文字列を作成
	    StringBuilder pointsJsArray = new StringBuilder("[");
	    for (int i = 0; i < check_results.size(); i++) {
	    	Check_Results p = check_results.get(i);
	        pointsJsArray.append("{x:").append(p.getX()).append(", y:").append(p.getY()).append("}");
	        if (i != check_results.size() -1) pointsJsArray.append(",");
	    }
	    pointsJsArray.append("]");
	    // リクエスト属性にセット
	    request.setAttribute("polygonD", polygonD);
	    request.setAttribute("pointsJsArray", check_results);
	    request.setAttribute("pointsJsArray", pointsJsArray.toString());
	    request.setAttribute("scores", scores);
		
		// 週、月のチェック結果で一番高いストレス傾向を求める
		Map<String, Integer> weekcountmap = new HashMap<>();
		for (Check_Results result : oneweekresult) {
		    String type = result.getStress_factor();
		    weekcountmap.put(type, weekcountmap.getOrDefault(type, 0) + 1);
		}
		// 週の一番高いストレス傾向
		String weekmaxtrend = null;
		int weekmaxCount = 0;

		for (Map.Entry<String, Integer> entry : weekcountmap.entrySet()) {
		    if (entry.getValue() > weekmaxCount) {
		        weekmaxtrend = entry.getKey();
		        weekmaxCount = entry.getValue();
		    }
		}
		
		Map<String, Integer> monthcountmap = new HashMap<>();
		for (Check_Results result : onemonthresult) {
		    String type = result.getStress_factor();
		    monthcountmap.put(type, monthcountmap.getOrDefault(type, 0) + 1);
		}
		// 月の一番高いストレス傾向
		String monthmaxtrend = null;
		int monthmaxcount = 0;

		for (Map.Entry<String, Integer> entry : monthcountmap.entrySet()) {
		    if (entry.getValue() > monthmaxcount) {
		        monthmaxtrend = entry.getKey();
		        monthmaxcount = entry.getValue();
		    }
		}
		
		
		
		// daoのインスタンス化
		Check_CommentsDao ccdao = new Check_CommentsDao();
		One_Week_TrendsDao owdao = new One_Week_TrendsDao();
		One_Month_TrendsDao omdao = new One_Month_TrendsDao();
		// 日、週、月のコメントと、アドバイスを取得する
//		Check_Comments onedaycomments = ccdao.selectByScoreAndTrend(stress_score, stress_factor);
//		One_Week_Trends oneweekcomments = owdao.selectByScoreAndTrend(stress_score, weekmaxTrend);
		// One_Month_Trends onemonthcomments = omdao.selectByScoreAndTrend(stress_score, monthmaxtrend);
		
		// チェック結果に応じたペットコメントを一度だけ表示する場合、前回のログイン時間を記録し、今回のログイン時間と比べる
//		session.setAttribute("pet_check_comments", onedaycomments.getPet_check_comments());
//		
		boolean noData = onedayresult.isEmpty();
		request.setAttribute("noData", noData);
		
		// 週、月の始めと終わりをリクエストスコープに格納する
		request.setAttribute("startofweek", startofweek);
		request.setAttribute("endofweek", endofweek);
		request.setAttribute("startofmonth", startofmonth);
		request.setAttribute("endofmonth", endofmonth);
		
		// スコアの傾向をリクエストスコープに格納する。
		double newscore1 = score1/1.5;
		double newscore2 = score2/1.5;
		double newscore3 = score3/1.5;
		request.setAttribute("score1", newscore1);
		request.setAttribute("score2", newscore2);
		request.setAttribute("score3", newscore3);
		
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("onedayresult", onedayresult);
		request.setAttribute("oneweekresult", oneweekresult);
		request.setAttribute("onemonthresult", onemonthresult);
//		request.setAttribute("onedaycomments", onedaycomments);
//		 request.setAttribute("oneweekcomments", oneweekcomments);
//		 request.setAttribute("onemonthcomments", onemonthcomments);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/check_results.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		


		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/check_results.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
