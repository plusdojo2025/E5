package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDao;
import model.UsernamePassword;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//test
		// クエリパラメータによって重複チェックとして処理する
				String checkUsername = request.getParameter("check");
				if (checkUsername != null) {
					// Ajaxによる重複チェック処理
					UsersDao dao = new UsersDao();
					boolean exists = dao.isUsernameExists(checkUsername);

					response.setContentType("application/json; charset=UTF-8");
					response.getWriter().write("{\"exists\": " + exists + "}");
					return;
				}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//	
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username: " + username);
		System.out.println("password: " + password);

		
		UsersDao uDao = new UsersDao();
	    
	 // 登録処理を行う
		if (uDao.insert(new UsernamePassword(0,username,password))) { // 登録成功　ログイン画面へリダイレクト
	        response.sendRedirect("/E5/LoginServlet");
	        return;
		} else { // 登録失敗
			request.setAttribute("error", "ユーザーネームが重複しています。");
		}

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}
}
