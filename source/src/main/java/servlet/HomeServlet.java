package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Check_CommentsDao;
import dao.Check_ResultsDao;
import dao.Pet_CommentsDao;
import dao.UserItemsDao;
import model.Check_Comments;
import model.Check_Results;
import model.Pet_Comments;
import model.UserItems;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/E5/LoginServlet");
//			return;
//		}
		int userId = (int) session.getAttribute("id");
		
		//アイテムの所持数を表示
		UserItemsDao uiDao = new UserItemsDao();
		UserItems itemData = uiDao.findByUserId(userId);
		request.setAttribute("itemData", itemData);
		
		
		//表情判定（チェック結果を元に）
		Check_ResultsDao crDao = new Check_ResultsDao();
        Check_Results latestResult = crDao.findLatestByUserId(userId);
        String expression = "default";
        int stress_Score = -1;
        String stress_Factor = null;

        if (latestResult != null) {
            stress_Score = latestResult.getStress_score();
            stress_Factor = latestResult.getStress_factor(); // 仮に存在するgetterとします

            if (stress_Score < 30) {
                expression = "genki";
            } else if (stress_Score < 50) {
                expression = "normal";
            } else if (stress_Score < 70) {
                expression = "tired";
            } else {
                expression = "shindoi";
            }
        }
        request.setAttribute("expression", expression);
		
		
		//コメント判定(チェック結果を元に)
        if (stress_Score >= 0 && stress_Factor != null) {
            Check_CommentsDao ccDao = new Check_CommentsDao();
            Check_Comments commentData = ccDao.selectByScoreAndTrend(stress_Score, stress_Factor);
            if (commentData != null) {
                request.setAttribute("commentData", commentData);
            }
        }
		
		
		//ストレスチェックの促し(実施済みかの確認)
        LocalDate today = LocalDate.now();
        boolean hasCheckedToday = crDao.hasCheckResultToday(userId, today);
        request.setAttribute("hasCheckedToday", hasCheckedToday);
		
		
		//ログインボーナス判定(付与履歴の参照)（未チェック時のみ）
        if (!hasCheckedToday) {
            Random rand = new Random();
            int itemNumber = rand.nextInt(8) + 1;
            uiDao.incrementItem(userId, itemNumber);
        }
		
		//挨拶・一言コメント(コメント取得)
		Pet_CommentsDao pcDao = new Pet_CommentsDao();
        int petComNumber = new Random().nextInt(8) + 1;
        Pet_Comments petCom = pcDao.selectComments(petComNumber);
        request.setAttribute("petCom", petCom);

		
		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/E5/LoginServlet");
//			return;
//		}
		int userId = (int) session.getAttribute("id");
		
		//アイテムをあげる(アイテムを使用して所持数を更新)
		// 使うアイテムのIDを取得
	    int itemId = Integer.parseInt(request.getParameter("item_id"));  // 例：formのhidden inputで送信

	    // アイテムを使う処理
		UserItemsDao.useItem(userId, itemId);
		
		// ホームのJSPにフォワード（数秒待ってから）
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}
