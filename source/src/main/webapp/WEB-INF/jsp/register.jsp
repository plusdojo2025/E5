<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" href="<c:url value='/css/register.css' />">
<!--  <link rel="stylesheet" href="<c:url value='/css/common.css' />"> -->
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
			<div class=form_bottom>
			<button type="submit" class="registerButton register-item">登録</button><br>
			<a href="<c:url value='/LoginServlet' />">ログインはこちら</a>
			</div>
		</form>
	</div>
	</main>
	<!-- メイン（ここまで） -->
	<!-- フッター（ここから） -->
	<footer class="footer">
		<p>&copy; 2025 E5 makwm</p>
	</footer>
	<!-- フッター（ここまで） -->
	<script src="<c:url value='/js/register.js' />"></script>
</body>
</html>