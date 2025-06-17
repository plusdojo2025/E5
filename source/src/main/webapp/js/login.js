
document.getElementById('login-form').onsubmit = function(event) {
		const username = document.querySelector('input[name="username"]').value.trim();
		const password = document.querySelector('input[name="password"]').value.trim();
		const errorEl = document.getElementById('error-message');
		errorEl.textContent = '';

		// 入力チェック
		if (username === '' && password === '') {
			errorEl.textContent = '※ユーザーネームとパスワードを入力してください！';
			event.preventDefault();
			return false;
		}
		if (username === '') {
			errorEl.textContent = '※ユーザーネームを入力してください！';
			event.preventDefault();
			return false;
		}
		if (password === '') {
			errorEl.textContent = '※パスワードを入力してください！';
			event.preventDefault();
			return false;
		}
		if (!/^[a-zA-Z]{5,}$/.test(username)) {
			errorEl.textContent = '※ユーザーネームは5文字以上の半角英字で入力してください！';
			event.preventDefault();
			return false;
		}
		if (!/^(?=.*[a-zA-Z0-9])(?=.*\d)[a-zA-Z\d]{8,}$/.test(password)) {
			errorEl.textContent = '※パスワードは8文字以上の半角英数字混合で入力してください！';
			event.preventDefault();
			return false;
		}

		// すべてOKなら何もせず送信継続
	};