
document.getElementById('login-form').onsubmit = function(event) {
		const username = document.querySelector('input[name="username"]').value.trim();
		const password = document.querySelector('input[name="password"]').value.trim();
		const errorEl = document.getElementById('error-message');
		errorEl.textContent = '';

		// 入力チェック
		if (username === '' && password === '') {
			errorEl.textContent = '※usernameとpasswordを入力してください！';
			event.preventDefault();
			return false;
		}
		if (username === '') {
			errorEl.textContent = '※usernameを入力してください！';
			event.preventDefault();
			return false;
		}
		if (password === '') {
			errorEl.textContent = '※passwordを入力してください！';
			event.preventDefault();
			return false;
		}
		if (!/^[a-zA-Z0-9]{5,30}$/.test(username)) {
			errorEl.textContent = '※usernameは5文字以上の半角英字で入力してください！';
			event.preventDefault();
			return false;
		}
		if (!/^(?=.*[a-zA-Z])(?=.*\d)[ -~]{8,24}$/.test(password)) {
    		errorEl.textContent = '※passwordは8～24文字で、半角英数字混合で入力してください！';
    		event.preventDefault();
    		return false;
		}



		// すべてOKなら何もせず送信継続
	};