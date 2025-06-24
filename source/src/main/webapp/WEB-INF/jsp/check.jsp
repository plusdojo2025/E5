<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ストレッシュ</title>
  <link rel="stylesheet" href="<c:url value='/css/common.css'/>" >
  <link rel="stylesheet" href="<c:url value='/css/check.css'/>" >
</head>
<body>
  <div>
  <!-- ヘッダー -->
    <header id="header">
      <div id="brand">
        <a href="<c:url value='/HomeServlet'/>">
          <img alt="" src="<c:url value='/images/name_w.png'/>" class="name_img" class="noLogin">
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
    <!-- メイン（ここから） -->
    <main>
      <div class="contents_center">
        <h2>今日のストレスチェック</h2>
        <div id="check_list">
          <form action="<c:url value='/CheckServlet'/>" method="POST">
            <div class="question-block">
              <span class="question">質問1</span>
              <span class="q_text">非常にたくさんの仕事をしなければならないですか？</span><br>
            </div>
            <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="question1" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question1" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question1" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question1" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question1" value="1">
              <span class="choose">全くそう思わない</span>
            </label>
            </div>
            <div id="q1_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問2</span>
              <span class="q_text">自分のペースで仕事ができないですか？</span><br>
            </div>
            <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="question2" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question2" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question2" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question2" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question2" value="1">
              <span class="choose">全くそう思わない</span>
            </label>
            </div>
            <div id="q2_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問3</span>
              <span class="q_text">職場の仕事の方針に自分の意見を反映できないですか？</span><br>
            </div>
            <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="question3" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question3" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question3" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question3" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question3" value="1">
              <span class="choose">全くそう思わない</span>
            </label>
            </div>
            <div id="q3_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問4</span>
              <span class="q_text">ひどく疲れており、へとへとですか？ </span><br>
            </div>
            <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="question4" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question4" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question4" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question4" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question4" value="1">
              <span class="choose">全くそう思わない</span>
            </label>
            </div>
            <div id="q4_message"></div><br>
            
            <div class="question-block">
             <span class="question">質問5</span>
             <span class="q_text">気が張りつめており、落ち着かないですか？ </span><br>
            </div>
            <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="question5" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question5" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question5" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question5" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question5" value="1">
              <span class="choose">全くそう思わない</span>
            </label>
            </div>
            <div id="q5_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問6</span>
              <span class="q_text">何をするのも面倒で、気分が晴れないですか？</span><br>
            </div>
            <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="question6" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question6" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question6" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question6" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question6" value="1">
              <span class="choose">全くそう思わない</span>
            </label>
            </div>
            <div id="q6_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問7</span>
              <span class="q_text">よく眠れないですか？ </span><br>
            </div>
            <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="question7" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question7" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question7" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question7" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question7" value="1">
              <span class="choose">全くそう思わない</span>
            </label>
            </div>
            <div id="q7_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問8</span>
              <span class="q_text">上司とどのくらい気軽に話ができますか？</span><br>
            </div>
            <div class="radio_last">
            <label class="radio-item">
              <input type="radio" name="question8" value="1">
              <span class="choose">非常に</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="2">
              <span class="choose">かなり</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="3">
              <span class="choose">やや</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="4">
              <span class="choose">多少</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="5">
              <span class="choose">全くない</span>
            </label>
            </div>
            <div id="q8_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問9</span>
              <span class="q_text">同僚とどのくらい気軽に話ができますか？</span><br>
            </div>
            <div class="radio_last">
            <label class="radio-item">
              <input type="radio" name="question9" value="1">
              <span class="choose">非常に</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="2">
              <span class="choose">かなり</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="3">
              <span class="choose">やや</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="4">
              <span class="choose">多少</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="5">
              <span class="choose">全くない</span>
            </label>
            </div>
            <div id="q9_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問10</span>
              <span class="q_text">あなたが困った時、上司はどのくらい頼りになりますか？</span><br>
            </div>
            <div class="radio_last">
            <label class="radio-item">
              <input type="radio" name="question10" value="1">
              <span class="choose">非常に</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="2">
              <span class="choose">かなり</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="3">
              <span class="choose">やや</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="2">
              <span class="choose">多少</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="5">
              <span class="choose">全くない</span>
            </label>
            </div>
            <div id="q10_message"></div><br>
            
            <hr>
            <input type="submit" name="submit" value="登録" class="button">
          </form>
          
          <div id="confirmModal" class="modal">
  　         <div class="modal-content">
              <p id="text_top">本日のチェック内容を</p>
              <p id="text_bottom">登録してもよろしいですか？</p>
              <button id="confirmYes">はい</button>
              <button id="confirmNo">いいえ</button>
            </div>
          </div>
          
        </div><!-- id="check_list" -->
        
        <img src="<c:url value='/images/go-top.png'/>" alt="一番上へ戻る" id="gotop">
        
      </div><!-- class="contents_center" -->
    </main>
    <!-- メイン（ここまで） -->
    <!-- フッター（ここから） -->
    <footer>
      <div id="footer">
        <p>&copy;2025 E5 makwm</p>
      </div>
    </footer>
    <!-- フッター（ここまで） -->
  </div>
  <script src="<c:url value='/js/check.js'/>"></script>
  <script src="<c:url value='/js/common.js'/>"></script>
</body>
</html>