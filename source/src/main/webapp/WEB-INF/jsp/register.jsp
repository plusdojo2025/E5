<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<main>
	<form id="registerForm" action="/E5/RegisterServlet" method="post">
	<h1>ストレッシュ</h1>
	<div class="regist_form" id="regist_form">
		<p>ユーザーネーム
			<button>
				<span>i</span>
			</button></p>
		<input type="text" id="username" name="username">
		<span id="username-msg"></span>
		<p>password
			<button>
				<span>i</span>
			</button></p>
		<input type="password" id="password" name="password">
		<p>password(再入力)</p>
		<input type="password" id="confirmPassword" name="confirmPassword">
		<p id="errorMessage" style="color:red;">
			<c:if test="${not empty error}">
    		${error}
  			</c:if>
		</p>
		<button type="submit">登録</button><br>
		<a href="/E5/LoginServlet">ログインはこちら</a>
	</div>
	</form>
</main>
<p>&copy;makwm</p>
<script src="/E5/js/register.js"></script>
</body>
</html>