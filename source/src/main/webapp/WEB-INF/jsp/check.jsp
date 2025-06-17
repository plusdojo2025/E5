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
              <span class="q_text">最近、些細なことでイライラしたり、怒りっぽくなっていると感じますか？</span><br>
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
            <div id="q1_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問2</span>
              <span class="q_text">気分が落ち込んだり、不安な気持ちが長く続くことがありますか？</span><br>
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
            <div id="q2_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問3</span>
              <span class="q_text">「自分には価値がない」と感じる瞬間が増えましたか？</span><br>
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
            <div id="q3_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問4</span>
              <span class="q_text">寝つきが悪かったり、夜中に何度も目が覚めることが多いですか？</span><br>
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
            <div id="q4_message"></div><br>
            
            <div class="question-block">
             <span class="question">質問5</span>
             <span class="q_text">理由もなく体がだるく感じたり、疲れが取れないと感じますか？</span><br>
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
            <div id="q5_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問6</span>
              <span class="q_text">頭痛や腹痛などの身体的な不調が頻繁にありますか？</span><br>
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
            <div id="q6_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問7</span>
              <span class="q_text">職場や家庭で、人間関係のストレスを感じることが多いですか？</span><br>
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
            <div id="q7_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問8</span>
              <span class="q_text">家事や仕事など、やるべきことに追われて休む時間がありませんか？</span><br>
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
            <div id="q8_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問9</span>
              <span class="q_text">お金や将来の生活について不安を感じることがありますか？</span><br>
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
            <div id="q9_message"></div><br>
            
            <div class="question-block">
              <span class="question">質問10</span>
              <span class="q_text">～～～？</span><br>
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
        
        <img src="/E5/images/gotop.png" alt="一番上へ戻る" id="gotop">
        
      </div><!-- class="contents_center" -->
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
  <script src="/E5/js/check.js"></script>
  <script>
  
  </script>
</body>
</html>