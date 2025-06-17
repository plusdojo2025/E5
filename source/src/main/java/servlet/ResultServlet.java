package servlet;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import model.Check_Comments;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean noData = true;
		request.setAttribute("noData", noData);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/check_results.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		LocalDate day;
		
		// 日、週、月の結果を取得して渡す ユーザーidと今日の日付を取得し、今日のチェック結果を探す。
		
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		
		int id = (Integer) session.getAttribute("id");
		
		// パラメータから日付取得（例：2025-06-17）
		if (request.getParameter("day") == null) {
			day = LocalDate.now(); 
		} else {
			String Stringday = request.getParameter("day");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        day = LocalDate.parse(Stringday, formatter);
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
		int stress_score = onedayresult.get(0).getStress_score();
		String stress_factor = onedayresult.get(0).getStress_factor();
		
		// 週、月のチェック結果のストレス傾向、コメントを選択する。
		
		// 週、月のチェック結果のストレス傾向、コメントを取得する
		
		// daoのインスタンス化
		Check_CommentsDao ccdao = new Check_CommentsDao();
		One_Week_TrendsDao owdao = new One_Week_TrendsDao();
		One_Month_TrendsDao omdao = new One_Month_TrendsDao();
		// 日、週、月のコメントと、アドバイスを取得する
		Check_Comments onedaycomments = ccdao.selectByScoreAndTrend(stress_score, stress_factor);
		// One_Week_Trends oneweekcomments = owdao.selectByScoreAndTrend(stress_score, stress_factor);
		// One_Month_Trends onemonthcomments = omdao.selectByScoreAndTrend(stress_score, stress_factor);
		
		// チェック結果に応じたペットコメントを一度だけ表示する場合、前回のログイン時間を記録し、今回のログイン時間と比べる
		session.setAttribute("pet_check_comments", onedaycomments.getPet_check_comments());
		
		boolean noData = onedayresult.isEmpty();
		
		request.setAttribute("noData", noData);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("onedayresult", onedayresult);
		request.setAttribute("oneweekresult", oneweekresult);
		request.setAttribute("onemonthresult", onemonthresult);
		request.setAttribute("onedaycomments", onedaycomments);
		// request.setAttribute("oneweekcomments", oneweekcomments);
		// request.setAttribute("onemonthcomments", onemonthcomments);


		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/check_results.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
