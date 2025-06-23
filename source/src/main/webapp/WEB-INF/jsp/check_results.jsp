<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ストレッシュ</title>
  <link rel="stylesheet" href="<c:url value='/css/check_results.css' />"  />
  <link rel="stylesheet" href="<c:url value='/css/common.css' />"  />
  <script src="<c:url value='/js/check_results.js' />"></script>
</head>

<body>
<header id="header">
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
	<main>
	<h2>ストレスチェック結果</h2>
<form action="<c:url value='/ResultServlet' />" method="get">
	<div class="tab-container">
        <input type="radio" name="tabs" id="tab1" class="tab-input" checked>
        <input type="radio" name="tabs" id="tab2" class="tab-input">
        <input type="radio" name="tabs" id="tab3" class="tab-input">
		<!-- 期間を選択する。 -->
        <div class="tab-labels">
        	<div class="tab-group_left">
	            <label for="tab1" class="tab-label label1">日</label>
	            <label for="tab2" class="tab-label label2">週</label>
	            <label for="tab3" class="tab-label label3">月</label>
	        </div>
            <div class="tab-group_right">
           		<input type="date" name="day">
           		<input type="submit" class="period_change" name="submit">
           	</div>
        </div>
		<!-- 選択された期間の内容を個別に表示する。 -->
        <div class="tab-content content1">
        <c:choose>
          <c:when test="${noData}">
		  <p>チェック結果がありません。</p>
		  </c:when>
		  <c:otherwise>
        	<h1 class="check_results">ストレスチェック結果 ${onedayresult[0].stress_score}点</h1>
        	<div class="radar-container">
				<div class="label-column">
	        	    <label>環境的ストレスがやや高いです。</label>
	    	        <label>生活的ストレスがやや高いです。</label>
		            <label>身体的ストレスがやや高いです。</label>
				</div>
		    	<div class="radar-chart-1">
			    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200">
			        <g stroke="#dce5eb">
			            <path d="M 100 100 L 100.0 0.0"/>
			            <path d="M 100 100 L 186.6 150.0"/>
			            <path d="M 100 100 L 13.4 150.0"/>
			        </g>
			        <g stroke="#dce5eb" fill="none">
			            <path d="M 100.0 0.0 L 186.6 150.0 L 13.4 150.0 L 100.0 0.0"/>
			            <path d="M 100.0 33.3 L 157.7 133.3 L 42.3 133.3 L 100.0 33.3"/>
			            <path d="M 100.0 66.7 L 128.9 116.7 L 71.1 116.7 L 100.0 66.7"/>
			        </g>
			        <path id="dataPolygon" d="${polygonD}" fill="#2589d030" stroke="#2589d0"/>
			        
			        <g fill="#2589d0">
			        
			           <c:forEach var="point" items="${points}" varStatus="status">
						    <circle id="point${status.index}" cx="${point.x}" cy="${point.y}" r="3" fill="red"/>
						</c:forEach>
			        </g>
			    </svg>
			    
			    <dl>
			        <div>
			            <dt>環境的ストレス</dt>
			            <dd><%= request.getAttribute("score3")%></dd>
			        </div>
			        <div>
			            <dt>身体的ストレス</dt>
			            <dd><%= request.getAttribute("score2")%></dd>
			        </div>
			        <div>
			            <dt>生活的ストレス</dt>
			            <dd><%= request.getAttribute("score1")%></dd>
			        </div>
			    </dl>
			</div>
			</div>
			<div class="check_comments">
				<p class="check_item">コメント</p>
				<p>・${onedaycomments.comments}サンプルテキスト</p>
			</div>
			<div class="check_advice">
				<p class="check_item">アドバイス</p>
				<p>・${onedaycomments.advice}サンプルテキスト</p>
			</div>
			<a href="/E5/HomeServlet">ホーム</a>
			</c:otherwise>
			</c:choose>
        </div>
		<!-- 週の結果 -->
        <div class="tab-content content2">
            <h1 class="check_results">${startofweek}～${endofweek}の記録</h1>
            <dl class="bar-chart-1">
					<c:forEach var="item" items="${oneweekresult}">
					    <!-- データ表示 -->
				    <div class="bar-item">
				        <dt>${item.formattedDate}</dt>
				        <dd style="width: ${item.stress_score * 7.5}px">${item.stress_score}%</dd>
				        <dt>${item.stress_score}</dt>
				    </div>
					</c:forEach>
			</dl>
            
            <div class="week_comments">
				<p>週の傾向</p>
				<p>${oneweekcomments}</p>
				<p>アドバイス</p>
				<p>${oneweekcomments}</p>
			</div>
			<a href="/E5/HomeServlet">ホーム</a>
        </div>
		<!-- 月の結果 -->
        <div class="tab-content content3">
            <h1 class="check_results">${startofmonth}～${endofmonth}の記録</h1>
            <dl class="bar-chart-2">
			    <c:forEach var="item" items="${onemonthresult}">
					    <!-- データ表示 -->
				    <div>
				        <dt>${item.formattedDate}</dt>
				        <dd style="width: ${item.stress_score * 7.5}px">${item.stress_score}%</dd>
				        <dt>${item.stress_score}</dt>
				    </div>
					</c:forEach>
			    
			</dl>
            
            <div class="week_comments">
				<p>月の傾向</p>
				<p>${onemonthcomments}</p>
			</div>
			<a href="<c:url value='/HomeServlet' />">ホーム</a>
        </div>

    </div>

</form>

	</main>
	<footer>
      <div id="footer">
        <p>&copy;2025 E5 makwm</p>
      </div>
    </footer>
	<script src="<c:url value='/js/check_results.js'/>"></script>
	<script src="<c:url value='/js/common.js'/>"></script>
</body>
		  
</html>