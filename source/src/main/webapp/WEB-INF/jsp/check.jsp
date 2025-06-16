<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ストレッシュ</title>
  <link rel="stylesheet" href="/E5/css/common.css" />
  <link rel="stylesheet" href="/E5/css/check.css" />
  <style>
    h1 {
      background-color: #f2acac;
    }
    img {
      width: 100px;
    }
    
    .contents_center {
      text-align: center;
    }
    h2 {
      padding: 10px;
      margin: auto;
      margin-bottom: 10px;
      border: 1px solid #333333;
      border-radius: 10px;
      background-color: #9fdfec;
      width: 500px;
    }
    #check_list {
      padding: 10px;
      padding-top: 25px;
      margin: auto;
      margin-bottom: 10px;
      border: 1px solid #333333;
      border-radius: 50px;
      width: 600px;
    }
    .question {
      background-color: #9fdfec;
    }
    span {
      font-size: 125%;
    }
    .question-block {
      text-align: left;
      margin-left: 50px; /* 必要に応じて調整 */
      margin-bottom: 10px;
    }
    .choose {
      font-size: 50%;
    }
    .radio-item {
      display: inline-flex;
      flex-direction: column; /* ←テキストを下に */
      align-items: center;
      margin: 10px;
      cursor: pointer;
      font-size: 165%;
    }
    input[type="radio"] {
      /* accent-color: #f00; */
      transform: scale(1.5);
      margin-bottom: 8px;
    }
    hr {
      width: 90%;
    }
    
  </style>
</head>
<body>
  <div>
  <!-- ヘッダー -->
    <header class="header">
      <h1>ストレッシュ
      <a href="/E5/HomeServlet">
        <img src="/E5/images/logo_w.png" alt="ストレッシュロゴ">
      </a>
      </h1>
      <div id="menu">メニュー</div>
      <nav id="nav">
        <ul>
          <li><a href="/E5/HomeServlet">ホーム</a>
          <li><a href="/E5/CheckServlet">ストレスチェック</a>
          <li><a href="/E5/ResultsServlet">統計</a>
          <li><a href="/E5/LogoutServlet">ログアウト</a>
        </ul>
      </nav>
    </header>
    <!-- ヘッダー（ここまで） -->
    <!-- メイン（ここから） -->
    <main>
      <div class="contents_center">
        <h2>今日のストレスチェック</h2>
        <div id="check_list">
          <form action="/E5/CheckServlet" method="POST">
            <div class="question-block">
              <span class="question">質問1</span>
              <span>～～～？</span><br>
            </div>
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
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問2</span>
              <span>～～～？</span><br>
            </div>
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
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問3</span>
              <span>～～～？</span><br>
            </div>
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
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問4</span>
              <span>～～～？</span><br>
            </div>
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
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
             <span class="question">質問5</span>
             <span>～～～？</span><br>
            </div>
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
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問6</span>
              <span>～～～？</span><br>
            </div>
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
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問7</span>
              <span>～～～？</span><br>
            </div>
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
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問8</span>
              <span>～～～？</span><br>
            </div>
            <label class="radio-item">
              <input type="radio" name="question8" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question8" value="1">
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問9</span>
              <span>～～～？</span><br>
            </div>
            <label class="radio-item">
              <input type="radio" name="question9" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question9" value="1">
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <div class="question-block">
              <span class="question">質問10</span>
              <span>～～～？</span><br>
            </div>
            <label class="radio-item">
              <input type="radio" name="question10" value="5">
              <span class="choose">とてもそう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="4">
              <span class="choose">そう思う</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="3">
              <span class="choose">どちらでもない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="2">
              <span class="choose">あまりそう思わない</span>
            </label>
            <label class="radio-item">
              <input type="radio" name="question10" value="1">
              <span class="choose">思わない</span>
            </label>
            <div class="message"></div><br>
            
            <hr>
            <input type="submit" name="submit" value="登録">
          </form>
        </div>
      </div>
    </main>
    <!-- メイン（ここまで） -->
    <!-- フッター（ここから） -->
    <footer>
      <div id="footer">
        <p>&copy;Copyright E5</p>
      </div>
    </footer>
    <!-- フッター（ここまで） -->
  </div>
</body>
</html>