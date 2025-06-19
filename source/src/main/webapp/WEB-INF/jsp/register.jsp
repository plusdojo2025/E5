<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" href="c:url value='/css/login.css' ">
<link rel="stylesheet" href="c:url value='/css/common.css' ">
</head>
<body>
	<!-- ヘッダー（ここから） -->
	<header class="header"></header>
	<!-- ヘッダー（ここまで） -->
	<!-- メイン（ここから） -->
	<main>
	<div class="title">
		<h1 class="title-text">ストレッシュ</h1>
		<img alt="" src="<c:url value='/images/logo.png'/>" class="logo">
	</div>
	<div id="register">
		<form id="registerForm" action="<c:url value='/RegisterServlet' />" method="POST">
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
			<a href="<c:url value='/LoginServlet' />">ログインはこちら</a>
		</form>
	</div>	
	</main>
	<!-- メイン（ここまで） -->
	<!-- フッター（ここから） -->
	<footer class="footer">
		<p>&copy;makwm</p>
	</footer>
	<!-- フッター（ここまで） -->
	<script src="<c:url value='/js/register.js' />"></script>
</body>
</html>