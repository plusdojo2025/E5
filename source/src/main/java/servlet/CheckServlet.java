package servlet;

import java.io.IOException;

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
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}
		
		//ストレスチェックページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/check.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse 
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}
		
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		// ユーザーID
		int userid = (int) session.getAttribute("id"); // セッションからユーザーID取得
	    
	    // 質問1〜10の取得
	    int[] answers = new int[10];
	    for (int i = 0; i < 10; i++) {
	        answers[i] = Integer.parseInt(request.getParameter("question" + (i + 1)));
	    }
		
		// ストレススコアの計算
		int total = 0;
		for (int score : answers) {
			total += score;
		}
		int stress_Score = total * 2;
		
		// 各カテゴリごとのスコア合計（分類は例）
		int workStress = answers[0] + answers[1] + answers[2];       // Q1〜Q3
		int humanStress = answers[3] + answers[4] + answers[5];      // Q4〜Q6
		int anxietyStress = answers[7] + answers[8] + answers[9];	 // Q8〜Q10

		// 一番高かったストレス項目を決定(複数ある場合の優先度、1:身体的 2:環境的 3:生活的)
		int max = Math.max(workStress, Math.max(humanStress, anxietyStress));

		// 該当するカテゴリがいくつあるかをチェック
		boolean isWorkMax = workStress == max;
		boolean isHumanMax = humanStress == max;
//		boolean isAnxietyMax = anxietyStress == max;

		String stress_Factor = "";
		if (isHumanMax) {
		    stress_Factor = "身体的ストレス";
		} else if (isWorkMax) {
		    stress_Factor = "環境的ストレス";
		} else {
		    stress_Factor = "生活的ストレス";
		}
		
		// 登録処理を行う(登録日の登録はDBで自動で行わせるため、いらない)
		Check_ResultsDao crDao = new Check_ResultsDao();
		Check_Results resultData = new Check_Results(
				userid, 0, stress_Score,
				answers[0], answers[1], answers[2], answers[3], answers[4],
				answers[5], answers[6], answers[7], answers[8], answers[9],
				stress_Factor
		);
//		crDao.check_insert(resultData); // 成功・失敗に関係なく進む
		boolean result = crDao.check_insert(resultData);
//		System.out.println("check_insert result: " + result);
		if (result) {
			//ストレスチェック結果ページ用Servletにリダイレクトする
			response.sendRedirect(request.getContextPath() +"/CheckResultsServlet");
			return;
		} else {
			//ストレスチェックページ用Servletにリダイレクト(戻る)
			response.sendRedirect(request.getContextPath() +"/CheckServlet");
			return;

		}
		
//		/*
//		// ストレススコアと傾向（trend）をもとにコメント取得（検索処理を行う）
//		Check_CommentsDao ccDao = new Check_CommentsDao();
//		Check_Comments commentData = ccDao.selectByScoreAndTrend(stress_Score, stress_Factor);
//		
//		// 検索結果をリクエストスコープに格納してJSPに渡す（nullチェックも含めて）
//		if (commentData != null) {
//			request.setAttribute("commentData", commentData);  // まとめてJSPに渡す
////			request.setAttribute("comment", commentData.getComments());
////			request.setAttribute("advice", commentData.getAdvice());
////			request.setAttribute("pet_comment", commentData.getPet_check_comments());
//		}
////		else {
////			request.setAttribute("comment", "該当するコメントが見つかりませんでした。");
////			request.setAttribute("advice", "適切なアドバイスがありません。");
////			request.setAttribute("pet_comment", "キャラ用コメントなし。");
////		}
//		*/
		
		//ストレスチェック結果ページ用Servletにリダイレクトする
//		response.sendRedirect(request.getContextPath() +"/CheckResultsServlet");
		/*
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/check_result.jsp");
		dispatcher.forward(request, response);
		*/
	}

}
