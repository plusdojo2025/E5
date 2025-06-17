package servlet;

import java.io.IOException;
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

import dao.Check_ResultsDao;
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
		
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		
		int id = (Integer) session.getAttribute("id");
		if (request.getParameter("day") == null) {
			day = LocalDate.now(); 
			
		} else {
			String Stringday = request.getParameter("day");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        day = LocalDate.parse(Stringday, formatter);
		}
		
		
		// 日、週、月の結果を取得して渡す ユーザーidと今日の日付を取得し、今日のチェック結果を探す。
		 // yyyy-MM-dd形式の日付のみ取得
		
		Check_ResultsDao crdao = new Check_ResultsDao();
		List<Check_Results> onedayresult = crdao.check_results(new Check_Results(id,day));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("onedayresult", onedayresult);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/check_resultsjsp");
		dispatcher.forward(request, response);
		
		
	}

}
