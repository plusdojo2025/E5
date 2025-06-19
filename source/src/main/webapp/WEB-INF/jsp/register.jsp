<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" href="css/register.css">
<link rel="stylesheet" href="css/common.css">
</head>
<body>
	<header class="header"></header>
	<main>
	<div class="title">
		<h1 class="title-text">ストレッシュ</h1>
		<img alt="" src="images/logo.png" class="logo">
	</div>
	<div id="register">
		<form id="registerForm" action="/E5/RegisterServlet" method="post">
			<div class="register-item">
			<label for="username" class="register-label">ユーザーネーム
				<button class="tooltip">
					<span class="tooltip-text">半角英数字5～30文字</span>
				i</button>
			</label><br>
			<input type="text" id="username" name="username">
			</div>
			<div class="register-item">
				<label for="password" class="register-label">password
					<button class="tooltip">
						<span class="tooltip-text" id="username-msg">半角英数字8～24文字</span>
					i</button>
				</label><br>
			<input type="password" id="password" name="password">
			</div>
			<div class="register-item">
				<label for="confirmPassword" class="register-label">password(再入力)
				
				</label><br>
				<input type="password" id="confirmPassword" name="confirmPassword">
			</div>
			<span id="errorMessage"></span>
			<span id="error-area">
			<c:if test="${not empty error}">
    		${error}
  			</c:if>
			</span><br>
			<button type="submit" class="registerButton register-item">登録</button><br>
			<a href="/E5/LoginServlet">ログインはこちら</a>
		</form>
	</div>	
	</main>
	<footer class="footer">
		<p>&copy;makwm</p>
	</footer>
	<script src="/E5/js/register.js"></script>
</body>
</html>