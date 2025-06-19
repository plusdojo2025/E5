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
import javax.servlet.http.HttpSession;

import dao.Check_CommentsDao;
import dao.Check_ResultsDao;
import dao.Login_Bonus_HistoryDao;
import dao.Pet_CommentsDao;
import dao.UserItemsDao;
import model.Check_Comments;
import model.Check_Results;
import model.Login_Bonus_History;
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
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}
		
		// ログイン中ユーザーの取得(ユーザーごとのデータを扱うため)
		int userId = (int) session.getAttribute("id");
		
		//アイテムの所持数を表示
		UserItemsDao uiDao = new UserItemsDao();
		// ユーザーのアイテム所持数の取得
		UserItems itemData = uiDao.findByUserId(userId);
		request.setAttribute("itemData", itemData);
		
		
		//表情判定（チェック結果を元に）
		Check_ResultsDao crDao = new Check_ResultsDao();
        // 最新のストレスチェック結果の取得(最新の1件)
        Check_Results latestResult = crDao.findLatestByUserId(userId);
        String expression = "default";
        int stress_Score = -1;
        String stress_Factor = null;
		// 点数に応じてキャラクターの表情（画像やアニメーション）を変えるために文字列で分類
        if (latestResult != null) {
            stress_Score = latestResult.getStress_score();		// ストレス点数（0〜100）を取得
            stress_Factor = latestResult.getStress_factor();	// ストレスの傾向を取得

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
            //チェック結果に基づくコメント取得
            Check_CommentsDao ccDao = new Check_CommentsDao();
            Check_Comments commentData = ccDao.selectByScoreAndTrend(stress_Score, stress_Factor);
            if (commentData != null) {
                request.setAttribute("commentData", commentData);
            }
        }
		
		
		//ストレスチェックの促し(実施済みかの確認)
        LocalDate today = LocalDate.now();
        // 今日すでにチェック済みかを確認(今日すでにチェックをしていれば true、していなければ false)
        boolean hasCheckedToday = crDao.hasCheckResultToday(userId, today);
        // JSP側で showCheckPrompt == true なら JavaScript モーダルを表示させる構成にする
		request.setAttribute("showCheckPrompt", !hasCheckedToday); // モーダルを出すべきかどうか
		
		
		//ログインボーナス判定(付与履歴の参照)（ログインボーナスはチェック済かつ未受領時のみ付与）
		Login_Bonus_HistoryDao bonusDao = new Login_Bonus_HistoryDao();
		boolean hasReceivedBonus = bonusDao.hasReceivedBonusToday(userId, today);
        if (hasCheckedToday && !hasReceivedBonus) {
    		// アイテムをランダムに1つ付与
    		Random rand = new Random();
    		int itemNumber = rand.nextInt(8) + 1;
    		uiDao.incrementItem(userId, itemNumber);

    		// ボーナス取得履歴を登録
    		Login_Bonus_History record = new Login_Bonus_History(userId, today);
    		bonusDao.insertBonusRecord(record);

    		request.setAttribute("bonusItemId", itemNumber); // JSP/JSに渡す
    		request.setAttribute("showBonusModal", true);     // モーダル表示用フラグ
		}
		
		//挨拶・一言コメント(コメント取得)
		Pet_CommentsDao pcDao = new Pet_CommentsDao();
        int petComNumber = new Random().nextInt(8) + 1;
        // 挨拶コメントをランダムで取得
        Pet_Comments petCom = pcDao.selectComments(petComNumber);
        request.setAttribute("petCom", petCom);
        
		
		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}
		
		// ログイン中ユーザーの取得(ユーザーごとのデータを扱うため)
		int userId = (int) session.getAttribute("id");
		
		//アイテムをあげる(アイテムを使用して所持数を更新)
		// 使うアイテムのIDを取得(アイテムID（例：1＝チョコ、2＝おもちゃ）を取得。)
	    int itemId = Integer.parseInt(request.getParameter("item_id"));  // 例：formのhidden inputで送信

	    // アイテムを使う処理(→ アイテムの所持数を -1 更新（DB更新）)
	    UserItemsDao uidao = new UserItemsDao();
	    uidao.useItem(userId, itemId);
		
		
		// ホームのJSPにフォワード（数秒待ってから）
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}
