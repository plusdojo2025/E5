<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<form action="/E5/ResultServlet" method="get">
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
			        <path id="dataPolygon" d="M 100.0 30.0 L 160.6 135.0 L 39.4 135.0 L 100.0 30.0" fill="#2589d030" stroke="#2589d0"/>
			        <g fill="#2589d0">
			            <circle id="point0" cx="100.0" cy="30.0" r="3"/>
			            <circle id="point1" cx="160.6" cy="135.0" r="3"/>
			            <circle id="point2" cx="39.4" cy="135.0" r="3"/>
			        </g>
			    </svg>
			    <dl>
			        <div>
			            <dt>環境的ストレス</dt>
			            <dd>7</dd>
			        </div>
			        <div>
			            <dt>身体的ストレス</dt>
			            <dd>3</dd>
			        </div>
			        <div>
			            <dt>生活的ストレス</dt>
			            <dd>9</dd>
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
				
				    <div>
				        <dt>項目1</dt>
				        <dd style="width: 50%">50%</dd>
				        <dt>50</dt>
				    </div>

			</dl>
            
            <div class="week_comments">
				<p>週の傾向</p>
				<p>○○</p>
				<p>アドバイス</p>
				<p>○○</p>
			</div>
			<a href="/E5/HomeServlet">ホーム</a>
        </div>
		<!-- 月の結果 -->
        <div class="tab-content content3">
            <h1 class="check_results">06-01～06-30の記録</h1>
            <dl class="bar-chart-2">
			    <div>
			        <dt>項目1<%= request.getAttribute("score1")%></dt>
			        <dd style="width: <%= request.getAttribute("score1")%>%">50%</dd>
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
			<a href="/E5/HomeServlet">ホーム</a>
        </div>

    </div>

</form>

	</main>
</body>
		  <%Object obj =  request.getAttribute("score1");
		  int s1 = 3; // デフォルト値
		  if (obj != null && obj instanceof Integer) {
		      s1 = (Integer) obj;
		  } 
		  System.out.println(s1);
		  
		  if (obj instanceof Integer) {
			    int score = (Integer) obj;
			    System.out.println("score1 = " + score);
			} else {
			    System.out.println("score1はIntegerではありません");
			}
			
			Object obj2 =  request.getAttribute("score2");
			  int s2 = 3; // デフォルト値
			  if (obj2 != null && obj2 instanceof Integer) {
			      s2 = (Integer) obj2;
			  } 
			  System.out.println(s2);
			  
			  if (obj instanceof Integer) {
				    int score2 = (Integer) obj2;
				    System.out.println("score2 = " + score2);
				} else {
				    System.out.println("score2はIntegerではありません");
				}
				
				Object obj3 =  request.getAttribute("score3");
				  int s3 = 3; // デフォルト値
				  if (obj3 != null && obj3 instanceof Integer) {
				      s3 = (Integer) obj3;
				  } 
				  System.out.println(s3);%>
				  
				  <%if (obj3 instanceof Integer) {
					    int score3 = (Integer) obj3;
					    System.out.println("score3 = " + score3);
					} else {
					    System.out.println("score3はIntegerではありません");
					}%>
	<script>
  // Javaの値をJS変数に変換（エスケープに注意）
  console.log("dsada");
  function updateRadarChart(scores) {
	  console.log('scores:', scores);
	  const centerX = 100;
	  const centerY = 100;
	  const radius = 70;
	  const pointCount = scores.length;
	  const angleStep = (2 * Math.PI) / pointCount;
	  console.log(`score ${score} → ratio ${ratio}`);
	  // 頂点座標計算
	  const points = scores.map((score, i) => {
	    const ratio = (score - 3) / (15 - 3);  // = (score - 3) / 12
	    const angle = angleStep * i - Math.PI / 2;
	    const x = centerX + radius * ratio * Math.cos(angle);
	    const y = centerY + radius * ratio * Math.sin(angle);
	    return { x, y };
	  });

	  // ポリゴンのd属性の文字列を作る
	  // M x0 y0 L x1 y1 L x2 y2 ... L x0 y0
	  let d = `M ${points[0].x} ${points[0].y}`;
	  for(let i = 1; i < points.length; i++) {
	    d += ` L ${points[i].x} ${points[i].y}`;
	  }
	  d += ` L ${points[0].x} ${points[0].y}`; // 閉じる

	  // ポリゴンを書き換え
	  const dataPolygon = document.getElementById('dataPolygon');
	  if(dataPolygon) {
	    dataPolygon.setAttribute('d', d);
	  }
	  
	  console.log("scores:", scores);
	  console.log("points:", points);
	  console.log("d attribute:", d);
	  console.log(`score ${score} → ratio ${ratio}`);
	  // 円の座標を書き換え
	  points.forEach((p, i) => {
	    const circle = document.getElementById(`point${i}`);
	    if(circle) {
	      circle.setAttribute('cx', p.x);
	      circle.setAttribute('cy', p.y);
	    } else {
	        console.warn(`circle point${i} not found`);
	    }
	  });
	}

  
  function onTabShown(tabIndex) {

	  if (tabIndex === 0) { // content1のとき
		  const scoresFromJava = [<%= s1 %>, <%= s2 %>, <%= s3 %>];
		  <%System.out.println(s2);%>
		  <%System.out.println(s3);%>
	    console.log('scoresFromJava raw:', scoresFromJava);
	    const scores = scoresFromJava.map(s => parseInt(s, 10));
	    console.log('scores numeric:', scores);
	    updateRadarChart(scores);
	  }
	}
  </script>
</html>