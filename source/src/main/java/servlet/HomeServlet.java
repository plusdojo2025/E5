package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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
import dao.Login_RewardsDao;
import dao.Pet_CommentsDao;
import dao.UserItemsDao;
import model.Check_Comments;
import model.Check_Results;
import model.Login_Bonus_History;
import model.Login_Rewards;
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
		LocalDate today = LocalDate.now();
		Check_ResultsDao crDao = new Check_ResultsDao();
        // 今日のストレスチェック結果を取得（存在すれば1件）
//        Check_Results todayResult = crDao.findByUserIdAndDate(userId, today);  // ←新たに用意したメソッドを想定
		List<Check_Results> todayResults = crDao.findByUserIdAndDate(userId, today);
		Check_Results todayResult = todayResults.isEmpty() ? null : todayResults.get(0); // 1件だけ取得

		
        String expression = "default";
        int stress_Score = -1;
        String stress_Factor = null;
        //　今日のストレスチェック結果があれば表情選択と、結果に応じたコメント選択を実行する
        if (todayResult != null) {
            stress_Score = todayResult.getStress_score();	// ストレス点数（0〜100）を取得
            stress_Factor = todayResult.getStress_factor();	// ストレスの傾向を取得

            // 点数に応じてキャラクターの表情を決定
            if (stress_Score < 30) {
                expression = "genki";
            } else if (stress_Score < 50) {
                expression = "normal";
            } else if (stress_Score < 70) {
                expression = "tired";
            } else {
                expression = "shindoi";
            }

            request.setAttribute("expression", expression);

            // チェック結果に基づくコメント取得
            Check_CommentsDao ccDao = new Check_CommentsDao();
            Check_Comments commentData = ccDao.selectByScoreAndTrend(stress_Score, stress_Factor);
            if (commentData != null) {
                request.setAttribute("commentData", commentData);
            }
        }
		
		
		//ストレスチェックの促し(実施済みかの確認)
//        LocalDate today = LocalDate.now();
        // 今日すでにチェック済みかを確認(今日すでにチェックをしていれば true、していなければ false)
        boolean hasCheckedToday = crDao.hasCheckResultToday(userId, today);
        // JSP側で showCheckPrompt == true なら JavaScript モーダルを表示させる構成にする
		request.setAttribute("showCheckPrompt", !hasCheckedToday); // モーダルを出すべきかどうか
		
		
		// 今の継続ログインの管理
		// 継続ログインの管理
		Login_RewardsDao streakDao = new Login_RewardsDao();
		Login_Rewards streak = streakDao.findByUserId(userId);

		int loginStreak = 0;

		if (streak != null) {
			LocalDate lastLogin = crDao.getLastCheckDate(userId, today); // created_atから取得
		    if (lastLogin != null && lastLogin.plusDays(1).isEqual(today)) {
		        // 昨日もログインしていた → 継続中
		        loginStreak = streak.getLogin_date() + 1;
		        if (loginStreak > 7) loginStreak = 1; // 7超えたら1に戻す
		    } else if (lastLogin == null || lastLogin.isBefore(today.minusDays(1))) {
		        // 途切れている → リセット(1日目になる)
		        loginStreak = 1;
		    } else {
		        // 今日単独で初回ログイン（継続なし、前日と関係なし）
		        loginStreak = 1;
		    }

		    // DBに更新
		    streakDao.updateLoginStreak(userId, loginStreak);
		} else {
		    // 初回ログイン
		    streakDao.insertLoginStreak(userId, 1); // 初回は1で登録
		    loginStreak = 1;
		}

		// JSPで使用するためセット
		request.setAttribute("loginStreak", loginStreak);

		// 残り日数を計算してセット（7日でボーナス）
		int remainingDays = 7 - loginStreak;
		if (remainingDays < 0) remainingDays = 0; // 念のため
		request.setAttribute("remainingDays", remainingDays);

		// ログインボーナスの判定（ストレスチェック済かつ未受領）
		Login_Bonus_HistoryDao bonusDao = new Login_Bonus_HistoryDao();
		boolean hasReceivedBonus = bonusDao.hasReceivedBonusToday(userId, today);

		if (hasCheckedToday && !hasReceivedBonus) {
		    // アイテムをランダムで1つ付与
		    Random rand = new Random();
		    int itemNumber = rand.nextInt(8) + 1;
		    uiDao.incrementItem(userId, itemNumber);

		    // ボーナス取得履歴を登録
		    Login_Bonus_History record = new Login_Bonus_History(userId, today);
		    bonusDao.insertBonusRecord(record);

		    // モーダル表示のためのフラグ
		    request.setAttribute("bonusItemId", itemNumber);
		    request.setAttribute("showBonusModal", true);
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
