function checkLogin() {
	var login = document.forms["lform"]["login"].value;
	var password = document.forms["lform"]["password"].value;
	var labelLogin = document.getElementById("labelLogin");
	var labelPassword = document.getElementById("labelPassword");
	var loginOk = true;
	if (login.length < 3) {
		labelLogin.innerHTML = "Login is too short.";
		labelLogin.removeAttribute("hidden");
		loginOk = false;
	} else if (login.indexOf(" ") >= 0) {
		labelLogin.innerHTML = "Spaces are not allowed";
		labelLogin.removeAttribute("hidden");
		loginOk = false;
	} else {
		labelLogin.innerHTML = "";
	}
	if (password.length < 8) {
		labelPassword.innerHTML = "Password is less than 8 characters";
		labelPassword.removeAttribute("hidden");
		loginOk = false;
	} else if (password.indexOf(" ") >= 0) {
		labelPassword.innerHTML = "Spaces are not allowed.";
		labelPassword.removeAttribute("hidden");
		loginOk = false;
	} else {
		labelPassword.innerHTML = "";
	}
	return loginOk;
}

var isOkLogin = false;
var isOkEmail = false;
var isOkPassword = false;
var isOkPassword2 = false;
var regLogin = document.getElementById("login");
var regEmail = document.getElementById("email");
var regPassword = document.getElementById("password");
var regPassword2 = document.getElementById("passwordtwo");

regLogin.onchange = function() {
	var loginValue = regLogin.value;
	var jsLoginMsg = document.getElementById("jsLoginMsg")
	if (loginValue.length < 4) {
		jsLoginMsg.innerHTML = "Login should contain more than 3 characters.";
		jsLoginMsg.style.color = "red";
	} else if (loginValue.indexOf(" ") >= 0) {
		jsLoginMsg.innerHTML = "Spaces are not allowed.";
		jsLoginMsg.style.color = "red";
	} else {
		jsLoginMsg.innerHTML = "Login is ok!";
		jsLoginMsg.style.color = "green";
		isOkLogin = true;
	}
	jsLoginMsg.removeAttribute("hidden");
}

regEmail.onchange = function() {
	var emailValue = regEmail.value;
	var jsEmailMsg = document.getElementById("jsEmailMsg")
	if (emailValue.length < 5) {
		jsEmailMsg.innerHTML = "Email should contain more than 4 characters.";
		jsEmailMsg.style.color = "red";
	} else if (emailValue.indexOf(" ") >= 0 || emailValue.indexOf("@") < 0) {
		jsEmailMsg.innerHTML = "Email is not valid.";
		jsEmailMsg.style.color = "red";
	} else {
		jsEmailMsg.innerHTML = "Email is ok!";
		jsEmailMsg.style.color = "green";
		isOkEmail = true;
	}
	jsEmailMsg.removeAttribute("hidden");
}

regPassword.onchange = function() {
	var passwordValue = regPassword.value;
	var jsPasswordMsg = document.getElementById("jsPasswordMsg")
	if (passwordValue.length < 8) {
		jsPasswordMsg.innerHTML = "Password should contain more than 7 characters.";
		jsPasswordMsg.style.color = "red";
	} else if (passwordValue.indexOf(" ") >= 0) {
		jsPasswordMsg.innerHTML = "Spaces are not allowed.";
		jsPasswordMsg.style.color = "red";
	} else {
		jsPasswordMsg.innerHTML = "Password is ok!";
		jsPasswordMsg.style.color = "green";
		isOkPassword = true;
	}
	jsPasswordMsg.removeAttribute("hidden");
	pass();
}

regPassword2.onchange = pass;
function pass() {
	var password2Value = regPassword2.value;
	var jsPassword2Msg = document.getElementById("jsPassword2Msg")
	if (password2Value.length > 7 && password2Value === regPassword.value) {
		jsPassword2Msg.innerHTML = "Password match!";
		jsPassword2Msg.style.color = "green";
		isOkPassword2 = true;
	} else {
		jsPassword2Msg.innerHTML = "Password doesn't match.";
		jsPassword2Msg.style.color = "red";
	}
	jsPassword2Msg.removeAttribute("hidden");
}

function regOk() {
	return isOkLogin && isOkEmail && isOkPassword && isOkPassword2;
}
