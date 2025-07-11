/* アイテム用メニュー　
'use strict'
document.addEventListener('DOMContentLoaded', function () {
    const itemsMenu = document.querySelector('.items_menu');
    const items = document.querySelector('.items');

    // メニューが開かれているか、いないかによって（条件式）displayの表示をnone（非表示）にしたりflex（表示）にしたりする
    itemsMenu.addEventListener('click', function () {
    items.style.display = items.style.display === 'flex' ? 'none' : 'flex';
    });
});
*/

/* ストレスチェック促し */
  // DOM読み込み完了時に判定
window.addEventListener("DOMContentLoaded", function () {
  // JSPで埋め込んだグローバル変数 window.shouldShowPrompt を使う
  console.log("shouldShowPrompt =", window.shouldShowPrompt);

  if (window.shouldShowPrompt === true) {
    document.getElementById("confirmModal").style.display = "block";
  }

  // モーダルのボタン処理
  const yesBtn = document.getElementById("confirmYes");
  const noBtn = document.getElementById("confirmNo");
  const modal = document.getElementById("confirmModal");

  yesBtn.addEventListener("click", function () {
    modal.style.display = "none";
    // チェック画面に遷移
    window.location.href = "CheckServlet";
  });

  noBtn.addEventListener("click", function () {
    modal.style.display = "none";
  });
});

// PC/スマホ画像切り替え
window.addEventListener("DOMContentLoaded", function () {
    function updatehukidashiImage() {
        const img = document.querySelector('.home_comment img');
        if (!img) return;

        const isMobile = window.innerWidth <= 400;
        img.src = isMobile ? hukidashi_SP : hukidashi_PC;
    }
    
     // 初回表示時にも呼び出す
    updatehukidashiImage();
	 
	 // 画面サイズ変化時にも適用
    window.addEventListener("resize", () => {
        // resizeで毎回正しく反映されるように、少し遅延させる
        setTimeout(updatehukidashiImage, 50);
    });
});