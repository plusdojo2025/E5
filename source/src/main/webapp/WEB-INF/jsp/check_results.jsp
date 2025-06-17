<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>チェック結果</title>
  <link rel="stylesheet" href="/E5/css/common.css" />
  <link rel="stylesheet" href="/E5/css/check_results.css" />
  <script src="/E5/js/check_results.js"></script>
</head>
<body>
	<main>
	<h2>ストレスチェック結果</h2>
<form action="/E5/ResultServlet" method="post">
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
            <label>期間変更</label>
           		<input type="date" name="calender" label="calender">
           	</div>
        </div>
		<!-- 選択された期間の内容を個別に表示する。 -->
        <div class="tab-content content1">
        
            	<input type="hidden" name="number" value="${e.number}" />
            	<input type="hidden" name="name" value="${e.name}" />
            	<input type="hidden" name="company" value="${e.company}" />
                <input type="hidden" name="department" value="${e.department}" />
                    <h3 class="card_head"><span>${e.company}</span></h3>
                   	<p><span class="cardlabel">名前：</span><span class="cardvalue">${e.name}</span></p>
                   	<p><span class="cardlabel">部署：</span><span class="cardvalue">${e.department}</span></p>
                   	<p><span class="cardlabel">住所：</span><span class="cardvalue">${e.address}</span></p>
            
        	<h1 class="check_results">ストレスチェック結果 ○○点</h1>
        	<div class="radar-container">
				<div class="label-column">
	        	    <label>kkk</label>
	    	        <label>kkk</label>
		            <label>kkk</label>
				</div>
		    	<div class="radar-chart-1">
			    <svg xmlns="http://www.w3.org/2000/svg" viewbox="0 0 200 200">
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
			        <path d="M 100.0 30.0 L 160.6 135.0 L 39.4 135.0 L 100.0 30.0" fill="#2589d030" stroke="#2589d0"/>
			        <g fill="#2589d0">
			            <circle cx="100.0" cy="30.0" r="3"/>
			            <circle cx="160.6" cy="135.0" r="3"/>
			            <circle cx="39.4" cy="135.0" r="3"/>
			        </g>
			    </svg>
			    <dl>
			        <div>
			            <dt>項目1</dt>
			            <dd>○○</dd>
			        </div>
			        <div>
			            <dt>項目2</dt>
			            <dd>○○</dd>
			        </div>
			        <div>
			            <dt>項目3</dt>
			            <dd>○○</dd>
			        </div>
			    </dl>
			</div>
			</div>
			<div class="check_comments">
				<p>コメント</p>
				<p>○○</p>
			</div>
			<div class="check_advice">
				<p>アドバイス</p>
				<p>○○</p>
			</div>
			</c:forEach>
			<a href="/E5/HomeServlet">ホーム</a>
        </div>
		<!-- 週の結果 -->
        <div class="tab-content content2">
            <p>週の結果をここに表示します。</p>
        </div>
		<!-- 月の結果 -->
        <div class="tab-content content3">
            <p>月の結果をここに表示します。</p>
        </div>

    </div>

</form>

	</main>
</body>
</html>