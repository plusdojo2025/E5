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
import dao.Pet_CommentsDao;
import model.Check_Comments;
import model.Check_Results;
import model.Pet_Comments;

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
		
		/* 一旦無くす（余裕があれば再度取り組む）
		//アイテムの所持数を表示
		UserItemsDao uiDao = new UserItemsDao();
		// ユーザーのアイテム所持数の取得
		UserItems itemData = uiDao.findByUserId(userId);
		request.setAttribute("itemData", itemData);
		*/
		
		//表情判定（チェック結果を元に）
		LocalDate today = LocalDate.now();
//		String todayStr = today.toString();
//		boolean alreadyShown = false;
		Check_ResultsDao crDao = new Check_ResultsDao();
        // 今日のストレスチェック結果を取得（存在すれば1件）
//        Check_Results todayResult = crDao.findByUserIdAndDate(userId, today);  // ←新たに用意したメソッドを想定
		List<Check_Results> todayResults = crDao.findByUserIdAndDate(userId, today);
		Check_Results todayResult = todayResults.isEmpty() ? null : todayResults.get(0); // 1件だけ取得
		
		/* 一旦無くす（余裕があれば再度取り組む）
		// クッキーの確認
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("expressionShownDate") && cookie.getValue().equals(todayStr)) {
		            alreadyShown = true;
		            break;
		        }
		    }
		}
		
		System.out.println("【debug】クッキー読み込み開始");
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        System.out.println("【debug】cookie name: " + cookie.getName() + ", value: " + cookie.getValue());
		        if (cookie.getName().equals("expressionShownDate") && cookie.getValue().equals(todayStr)) {
		            alreadyShown = true;
		            System.out.println("【debug】すでに表示済みと判定");
		            break;
		        }
		    }
		} else {
		    System.out.println("【debug】クッキーは存在しません");
		}
		System.out.println("【debug】alreadyShown: " + alreadyShown);
		*/
		
//        String expression = "default"; //　一旦無くす（余裕があれば再度取り組む）
        int stress_Score = -1;
        String stress_Factor = null;
//        boolean isFirstDisplay = false;
        //　今日のストレスチェック結果があれば表情選択と、結果に応じたコメント選択を実行する(すでに今日の表情が表示されていない場合のみ、実行)
        if (todayResult != null) {
            stress_Score = todayResult.getStress_score();	// ストレス点数（0〜100）を取得
            stress_Factor = todayResult.getStress_factor();	// ストレスの傾向を取得
//            boolean isFirstDisplay = false;
            /* 一旦無くす（余裕があれば再度取り組む）
            // 点数に応じてキャラクターの表情を決定
            if (!alreadyShown) {
                // 表情設定
                if (stress_Score < 30) {
                    expression = "genki";
                } else if (stress_Score < 50) {
                    expression = "normal";
                } else if (stress_Score < 70) {
                    expression = "tired";
                } else {
                    expression = "shindoi";
                }
//                request.setAttribute("expression", expression);
                
                // フラグON
                isFirstDisplay = true;

                // クッキーで「表示済み」を記録
                Cookie cookie = new Cookie("expressionShownDate", today.toString());
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            } else {
                // 表情だけは表示用に設定（演出は不要でも）
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
            request.setAttribute("isFirstDisplay", isFirstDisplay);
			*/

            // チェック結果に基づくコメント取得
            Check_CommentsDao ccDao = new Check_CommentsDao();
            Check_Comments commentData = ccDao.selectByScoreAndTrend(stress_Score, stress_Factor);
            if (commentData != null) {
                request.setAttribute("commentData", commentData);
            }
            /*
            // クッキーに記録を残す（有効期限：24時間）
            Cookie cookie = new Cookie("expressionShownDate", todayStr);
            cookie.setMaxAge(60 * 60 * 24); // 1日
            response.addCookie(cookie);*/
        }
		
		
		//ストレスチェックの促し(実施済みかの確認)
//        LocalDate today = LocalDate.now();
        // 今日すでにチェック済みかを確認(今日すでにチェックをしていれば true、していなければ false)
        boolean hasCheckedToday = crDao.hasCheckResultToday(userId, today);
        // JSP側で showCheckPrompt == true なら JavaScript モーダルを表示させる構成にする
		request.setAttribute("showCheckPrompt", !hasCheckedToday); // モーダルを出すべきかどうか
		
		
		/*　一旦無くす（余裕があれば再度取り組む）
		// 継続ログインの管理
		Login_RewardsDao streakDao = new Login_RewardsDao();
		Login_Rewards streak = streakDao.findByUserId(userId);
		int loginStreak;

		if (streak != null) {
		    // 最終チェック日（昨日までで最新）を取得
		    LocalDate lastLogin = crDao.getLastCheckDate(userId, today);

		    if (lastLogin != null && lastLogin.plusDays(1).isEqual(today)) {
		        // 昨日もログインしていた → 継続中
		        loginStreak = streak.getLogin_date() + 1;
		        if (loginStreak > 7) loginStreak = 1; // 上限7日でループ
		    } else {
		        // 初日 or 継続途切れ → 1日目にリセット
		        loginStreak = 1;
		    }

		    // ストリーク情報を更新
		    streakDao.updateLoginStreak(userId, loginStreak);
		} else {
		    // 初回ログイン
		    loginStreak = 1;
		    streakDao.insertLoginStreak(userId, loginStreak);
		}

		// 表示用にJSPに渡す
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
		*/
		
		
		//挨拶・一言コメント(コメント取得)
		Pet_CommentsDao pcDao = new Pet_CommentsDao();
        int petComNumber = new Random().nextInt(10) + 1;
        // 挨拶コメントをランダムで取得
        Pet_Comments petCom = pcDao.selectComments(petComNumber);
        request.setAttribute("petCom", petCom);
        
        // ★ここに追加！（チェック済みでもランダムにpetComを使うかどうか）
        boolean useCheckComment = false;
//        if (todayResult != null && !isFirstDisplay) {
        if (todayResult != null) {
            Random rand = new Random();
            useCheckComment = rand.nextBoolean(); // true = checkコメント, false = petCom
        }
        request.setAttribute("useCheckComment", useCheckComment);
        
		
		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
	
	/* 一旦無くす（余裕があれば再度取り組む）
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
	*/
}
