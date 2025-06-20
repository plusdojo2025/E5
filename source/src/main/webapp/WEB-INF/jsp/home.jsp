<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link rel="stylesheet" href="<c:url value='/css/common.css'/>" >
<link rel="stylesheet" href="<c:url value='/css/check.css'/>" >
</head>
<body>
    <!-- ヘッダー -->
    <header class="header">
      <div id="brand">
        <h1 id="h1">ストレッシュ</h1>
        <a href="<c:url value='/HomeServlet'/>">
          <img src="<c:url value='/images/logo_w.png'/>" alt="ストレッシュロゴ" id="logo">
        </a>
      </div>
      
      <div id="menu">メニュー</div>

	  <div class="menu_boxWrapWrap">
        <div class="menu_boxWrapBg"></div>
        <div class="menu_boxWrap">
            <nav id="nav">
                <div class="menu_box_close">
                    メニュー　×
                </div>
                <ul class="menu_box_list">
                    <li class="menu_box_list_items">
                        <a class="menu_box_list_items_link" href="<c:url value='/HomeServlet'/>">
                            ホーム
                        </a>
                    </li>
                    <li class="menu_box_list_items">
                        <a class="menu_box_list_items_link" href="<c:url value='/CheckServlet'/>">
                            ストレスチェック
                        </a>
                    </li>
                    <li class="menu_box_list_items">
                        <a class="menu_box_list_items_link" href="<c:url value='/ResultServlet'/>">
                            統計
                        </a>
                    </li>
                    <li class="menu_box_list_items">
                        <a class="menu_box_list_items_link" href="<c:url value='/LogoutServlet'/>">
                            ログアウト
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    </header>
    <!-- ヘッダー（ここまで） -->

    <main>
        <!-- アイテムの枠 -->
        <nav class="items_waku">
            <div class="items_menu">アイテムを選択</div>
            <ul class="items">
                <li>アイテム１</li>
                <li>アイテム２</li>
                <li>アイテム３</li>
            </ul>
        </nav>
        <!-- 背景の枠div -->
        <div class="haikei">
            <!-- コメントの枠div -->
            <div class="home_comment">
				<img src="<c:url value='/images/hukidashi.png'/>" alt="吹き出し">
				<div>happy</div>
            </div>
            <!-- キャラクターの枠 -->
            <div class="character">
				<img src="<c:url value='/images/happy_character.gif'/>" alt="ハッピーなキャラ">
            </div>
        </div>
    </main>
    <!-- フッター（ここから） -->
    <footer>
    <div id="footer">
        <p>&copy; 2025 E5 makwm</p>
    </div>
    </footer>
    <!-- フッター（ここまで） -->
    <script src="<c:url value='/js/home.js'/>"></script>
    <script src="<c:url value='/js/common.js'/>"></script>
</body>
</html>