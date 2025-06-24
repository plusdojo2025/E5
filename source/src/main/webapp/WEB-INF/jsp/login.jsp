<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="<c:url value='/css/login.css' />">
<!--  <link rel="stylesheet" href="<c:url value='/css/common.css' />">-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<!-- ヘッダー（ここから） -->
	<header class="header">
	<div class="title">
		<img alt="" src="<c:url value='/images/name_w.png'/>" class="name_img">
		<img alt="" src="<c:url value='/images/logo_w.png'/>" class="logo">
	</div>
	</header>
	<!-- ヘッダー（ここまで） -->
	<!-- メイン（ここから） -->
	<main>
	
	<div id="login">
		<form id="login-form" method="POST"  action="<c:url value='/LoginServlet' />">
			<div class="login-item">
				<label for="username" class="login-label">username
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
			<div class=form_bottom>
			<button type="submit" class="loginButton login-item">ログイン</button><br>
			<a href="<c:url value='/RegisterServlet' />">アカウントがない方はこちら</a>
			</div>
		</form>
	</div>
	</main>
	<!-- メイン（ここまで） -->
	<!-- フッター（ここから） -->
	<footer class="footer">
		<p class="copyright">&copy; 2025 E5 makwm</p>
	</footer>
	<!-- フッター（ここまで） -->
	<script src="<c:url value='/js/login.js' />"></script>
</body>
</html>