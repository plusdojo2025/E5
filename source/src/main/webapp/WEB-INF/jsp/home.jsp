<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/E5/js/home.js"></script>
<link rel="stylesheet" href="/E5/css/home.css">
<link rel="stylesheet" href="/E5/css/common.css">
</head>
<body>
    <!-- ヘッダー -->
    <header class="header">
      <h1>ストレッシュ
      <a href="/E5/HomeServlet">
        <img src="/E5/images/logo_white2.png" alt="ストレッシュロゴ">
      </a>
      </h1>
      <div id="menu">メニュー</div>
      <nav id="nav">
        <ul>
          <li><a href="/E5/HomeServlet">ホーム</a>
          <li><a href="/E5/CheckServlet">ストレスチェック</a>
          <li><a href="/E5/ResultServlet">統計</a>
          <li><a href="/E5/LogoutServlet">ログアウト</a>
        </ul>
      </nav>
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
				<img src="/E5/images/hukidashi.png" alt="吹き出し">
				<div>happy</div>
            </div>
            <!-- キャラクターの枠 -->
            <div class="character">
				<img src="/E5/images/happy_character.gif" alt="ハッピーなキャラ">
            </div>
        </div>
    </main>
    <!-- フッター（ここから） -->
    <footer>
    <div id="footer">
        <p>&copy;Copyright E5</p>
    </div>
    </footer>
    <!-- フッター（ここまで） -->
</body>
</html>