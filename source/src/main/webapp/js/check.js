/* ダイアログ・エラーメッセージ */
const form= document.querySelector("form");
const modal = document.getElementById("confirmModal");
const yesBtn = document.getElementById("confirmYes");
const noBtn = document.getElementById("confirmNo");
   
form.addEventListener("submit", function(e) {
  e.preventDefault();	// 一旦送信を止める
  
  let isValid = true; // 入力に不備があるかどうかのフラグ

  const questions = 10;
  for (let i = 1; i <= questions; i++) {
    const input = document.querySelector(`input[name="question${i}"]:checked`);
    const messageElem = document.getElementById(`q${i}_message`);
    messageElem.textContent = ""; // メッセージ初期化
    if (!input) {
      messageElem.textContent = "入力をお願いします";
      isValid = false;
    }
  }
  
  if(!isValid) {
	window.scroll({
      top: 0,	//ページから0pxの位置へ
      behavior: "smooth",	//スクロールして戻る
    });
  }
  
  // 入力がすべて揃っている場合のみモーダル表示
  if (isValid) {
    modal.style.display = "block";
  }
});
   
yesBtn.addEventListener("click", function() {
  modal.style.display = "none";
  HTMLFormElement.prototype.submit.call(form);	// ユーザーが「はい」を押したら送信
});
    
noBtn.addEventListener("click", function() {
 modal.style.display = "none";
 // 何もしない（フォーム送信されない）
});


/* 一番上へ戻るボタン */
const gotop = document.getElementById("gotop");

// クリック時に上に戻る
gotop.addEventListener("click", () => {
	window.scroll({
		top:0,
		behavior: "smooth",
	});
});

// スクロール時に表示・非表示を切り替え
window.addEventListener("scroll", () => {
	if (window.scrollY > 100) { // 100px以上スクロールしたら表示
		gotop.style.display = "block";
	} else {
		gotop.style.display = "none";
	}
});

// IntersectionObserverでfooterとの重なりを検知
const observer = new IntersectionObserver(entries => {
	entries.forEach(entry => {
		if (entry.isIntersecting) {
			// フッターが見えたらボタンを上に移動
			gotop.style.bottom = "100px"; // ←フッターの高さ＋余白分
		} else {
			// 見えていなければ通常位置に戻す
			gotop.style.bottom = "20px";
		}
	});
});

// フッターを監視対象にする
observer.observe(footer);