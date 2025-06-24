<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ホーム</title>
<link rel="stylesheet" href="<c:url value='/css/common.css'/>" >
<link rel="stylesheet" href="<c:url value='/css/check.css'/>" >
<link rel="stylesheet" href="<c:url value='/css/home.css'/>" >
</head>
<body>
    <!-- ヘッダー -->
    <header class="header">
      <div id="brand">
         <a href="<c:url value='/HomeServlet'/>">
          <img alt="" src="<c:url value='/images/name_w.png'/>" class="name_img noLogin">
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
        <div class="container">
        <!-- アイテムの枠 
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
		
			<div>
				<c:choose>
					<%-- 表情がセットされているとき（＝表情変化演出を表示） 
					<c:when test="${isFirstDisplay eq true}">
						<img src="<c:url value='/images/emotion/${expression}.png'/>" alt="感情アイコン" width="50">
					</c:when>--%>
		
					<%-- 通常時：コメントを表示 --%>
					<c:when test="${not empty commentData and useCheckComment}">
						${commentData.pet_check_comments}
					</c:when>
					<c:when test="${not empty petCom}">
						${petCom.pet_comments}
						<%--<p>expressionの値: ${expression}</p>
						<p>isFirstDisplayの値: ${isFirstDisplay}</p>--%>
					</c:when>
					<c:otherwise>
						コメントが取得できませんでした。
					</c:otherwise>
				</c:choose>
			</div>
			</div>
			
			<!-- 背景の壁 -->
			<div class="haikei_kabe">
			</div>
		
			<div class="character">
				<img src="<c:url value='/images/happy_character_c.gif'/>" alt="ハッピーなキャラ">
				<%--<c:choose>
					<%-- 表情が指定されていれば、表情に応じたキャラ画像を表示 
					<c:when test="${isFirstDisplay}">
						<img src="<c:url value='/images/character/${expression}_character.gif'/>" alt="${expression}なキャラ">
					</c:when>
			
					<%-- 通常キャラ画像 
					<c:otherwise>
						<img src="<c:url value='/images/happy_character.gif'/>" alt="ハッピーなキャラ">
					</c:otherwise>
				</c:choose>--%>
			</div>

        
	        <div id="confirmModal" class="modal">
	  　         <div class="modal-content">
	              <p id="text_top">本日はまだ行っていません。</p>
	              <p id="text_bottom">ストレスチェックを行いますか？</p>
	              <button id="confirmYes">はい</button>
	              <button id="confirmNo">いいえ</button>
	            </div>
	         </div>
				
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
    
    <script>
    window.shouldShowPrompt = "${showCheckPrompt}" === "true";  // true or false のまま渡す
    console.log("shouldShowPrompt =", window.shouldShowPrompt);
	</script>

    <script src="<c:url value='/js/home.js'/>"></script>
    <script src="<c:url value='/js/common.js'/>"></script>
    <script>
    	const hukidashi_PC = '<c:url value="/images/hukidashi.png"/>'
    	const hukidashi_SP = '<c:url value="/images/hukidashi_mobile.png"/>'
   </script>

</html>
