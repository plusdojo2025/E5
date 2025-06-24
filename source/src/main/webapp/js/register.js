/**
 * 
 */
 'use strict'

/* HTML要素をオブジェクトとして取得する */
let formObj = document.getElementById('regist_form');
let errorMessageObj = document.getElementById('errorMessage');

document.getElementById("registerForm").addEventListener("submit", function(event) {

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const errorMessage = document.getElementById("errorMessage");
    let messages = [];

    // username未入力
    if (username === "") {
      messages.push("※usernameを入力してください。");
    }

    // password未入力
    if (password === "") {
      messages.push("※passwordを入力してください。");
    }
    
    // password未入力
    if (confirmPassword === "") {
      messages.push("※password再入力を入力してください。");
    }

    // passwordの英数混合チェック（8文字以上24文字以内）
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+[\]{}|;:,.<>?`~\\-]{8,24}$/;
    if (password !== "" && !passwordRegex.test(password)) {
      messages.push("※passwordは英数字混合で8文字以上24文字以内で入力してください。");
    }
    
    // usernameの半角英数字（5文字以上30文字以内）
    const usernameRegex = /^[a-zA-Z0-9\d]{5,30}$/;
    if (username !== "" && !usernameRegex.test(username)) {
      messages.push("※usernameは半角英数字5～30文字で入力してください。");
    }

    // password再入力と一致するか
    if (password !== confirmPassword) {
      messages.push("※passwordとpassword再入力が一致していません。");
    }

    if (messages.length > 0) {
      errorMessage.innerHTML = messages.join("<br>");
      errorMessage.style.display = "block";
      event.preventDefault(); // 送信をキャンセル
    } else {
      errorMessage.style.display = "none";
    }
    
    
    //test
    document.getElementById("username").addEventListener("input", function () {
    const username = this.value.trim();
    const msgElem = document.getElementById("username-msg");

    if (username.length === 0) {
        msgElem.textContent = "";
        return;
    }

    fetch(`/E5/RegisterServlet?check=${encodeURIComponent(username)}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                msgElem.textContent = "※このusernameは既に使われています";
                msgElem.style.color = "red";
            } else {
                msgElem.textContent = "使用可能です";
                msgElem.style.color = "green";
            }
        })
        .catch(error => {
            console.error("重複チェックエラー:", error);
        });
});

  });