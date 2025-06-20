<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>チェック結果</title>
  <link rel="stylesheet" href="<c:url value='/css/check_results.css' />"  />
  <link rel="stylesheet" href="<c:url value='/css/common.css' />"  />
  <script src="<c:url value='/js/check_results.js' />"></script>
</head>

<body>
	<main>
	<h2>ストレスチェック結果</h2>
<form action="<c:url value='/CheckServlet' />" method="get">
	<div class="tab-container">
		○○の部分はservletからデータを取ってくる
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
		  <p>チェックは行いませんでした。</p>
		  </c:when>
		  <c:otherwise>
        	<h1 class="check_results">ストレスチェック結果 ${onedayresult.stress_score}点</h1>
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
			            <dd><%= request.getAttribute("score1")%></dd>
			        </div>
			        <div>
			            <dt>身体的ストレス</dt>
			            <dd><%= request.getAttribute("score2")%></dd>
			        </div>
			        <div>
			            <dt>生活的ストレス</dt>
			            <dd><%= request.getAttribute("score3")%></dd>
			        </div>
			    </dl>
			</div>
			</div>
			<div class="check_comments">
				<p>コメント</p>
				<p>${onedaycomments.comments}</p>
			</div>
			<div class="check_advice">
				<p>アドバイス</p>
				<p>${onedaycomments.advice}</p>
			</div>
			<a href="/E5/HomeServlet">ホーム</a>
			</c:otherwise>
			</c:choose>
        </div>
		<!-- 週の結果 -->
        <div class="tab-content content2">
            <h1 class="check_results">${starttoweek}～${endofweek}の記録</h1>
            <dl class="bar-chart-1">
					<c:forEach var="item" items="${oneweekresult}">
					    <!-- データ表示 -->
				    <div>
				        <dt>${item.created_at}</dt>
				        <dd style="width: ${item.stress_score}%">${item.stress_score}%</dd>
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
            <h1 class="check_results">06-01～06-30の記録</h1>
            <dl class="bar-chart-2">
			    <div>
			        <dt>項目1<%= request.getAttribute("score1")%></dt>
			        <dd style="width: <%= request.getAttribute("score1")%>%"><%= request.getAttribute("score1")%>%</dd>
			        <dt><%= request.getAttribute("score1")%></dt>
			    </div>
			    
			    <div class="container" style="display: flex; gap: 20px; ">
				  <div class="labels">
				        	<div>項目1</div>
				    <!-- ここに項目を縦にずらっと -->
				  </div>
				
				  <div class="bars">
				      <div style="width: 50%;">
							50
				      </div>
				  </div>
				  <div class="score">
				        	<div>50</div>
				    <!-- ここに項目を縦にずらっと -->
				  </div>
				  <div class="labels">
				        	<div>項目2</div>
				    <!-- ここに項目を縦にずらっと -->
				  </div>
				
				  <div class="bars">
				      <div style="width: 60%;">
							60
				      </div>
				  </div>
				  <div class="score">
				        	<div>60</div>
				    <!-- ここに項目を縦にずらっと -->
				  </div>
				  
				</div>
			</dl>
            
            <div class="week_comments">
				<p>月の傾向</p>
				<p>○○</p>
			</div>
			<a href="<c:url value='/HomeServlet' />">ホーム</a>
        </div>

    </div>

</form>

	</main>
</body>
		  
</html>