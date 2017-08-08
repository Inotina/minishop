<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="./style/main.css" />
<title>Login page</title>
</head>
<body>
	<div class="dlogin">
		<div class="login">
			<form method="post" name="lform" action="login" id="loginform" onsubmit="return checkLogin()">
				<c:if test="${not empty invalidLogin }">
					<div style="color: red; padding-top: 10px;">${invalidLogin }</div>
				</c:if>
				
				<c:if test="${empty invalidLogin }">
					<div style="color: red; padding-top: 28px;"></div>
				</c:if>
				<label id="labelLogin" style="color:red;" hidden></label>
				<div style="padding-top: 5px;">
					Login: &emsp; <input id="log" type="text" name="login" value="${login }" />
				</div>
				<c:if test="${not empty invalidPassword }">
					<div style="color: red; padding-top: 10px;">${invalidPassword }</div>
				</c:if>
				<c:if test="${empty invalidPassword }">
					<div style="color: red; padding-top: 28px;"></div>
				</c:if>
				<label id="labelPassword" style="color:red;" hidden></label>
				<div style="padding-top: 5px;">
					Password: <input id="pass" type="password" name="password" />
				</div>
			</form>
			<button type="submit" style="margin-top: 15px;" form="loginform"
				value="Submit">Login</button>
		</div>
	</div>
	<script type="text/javascript" src="./js/login.js"></script>
</body>
</html>