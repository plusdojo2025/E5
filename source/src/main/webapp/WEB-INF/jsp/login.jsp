<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/common.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<header class="header"></header>
	<main>
	<div class="title">
		<h1 class="title-text">ストレッシュ</h1>
		<img alt="" src="images/logo.png" class="logo">
	</div>
	<div id="login">
		<form id="login-form" method="POST"  action="/E5/LoginServlet">
			<div class="login-item">
				<label for="username" class="login-label">ユーザーネーム
					<button class="tooltip">
						<span class="tooltip-text">半角英数字5～30文字</span>
					i</button>
				</label><br>
				<input type="text" name="username">
			</div>
			<div class="login-item">
				<label for="password" class="login-label">password
					<button class="tooltip">
						<span class="tooltip-text">半角英数字8～24文字</span>
					i</button>
				</label><br>
				<input type="password" name="password">
			</div>
			<span id="error-message"></span>
			<span id="error-area">
			<% String error = (String) request.getAttribute("errorMessage");
						 if (error != null) {
							 out.print("※" + error);
							 } %>
			</span><br>
			<button type="submit" class="loginButton login-item">ログイン</button><br>
			<a href="/E5/RegisterServlet">アカウントがない方はこちら</a>
		</form>
	</div>
	</main>
	<footer class="footer">
		<p class="copyright">&copy;makwm</p>
	</footer>
	<script src="js/login.js"></script>
</body>
</html>