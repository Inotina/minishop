<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="./style/main.css" />
<title>Register page page</title>
</head>
<body>
	<h2>Registration Form</h2>
	<div class="regist">
		<form method="post" action="register" id="registerform">
			<c:if test="${not empty invalidEmail}">
				<div style="color: red; padding-top: 20px;">${invalidEmail }</div>
			</c:if>
			<c:if test="${empty invalidEmail}">
				<div style="color: red; padding-top: 38px;"></div>
			</c:if>
			<div style="padding-top: 5px;">
				&emsp; &emsp; &emsp;&emsp;Email: <input type="text" name="email"
					value="${email}" />
			</div>

			<c:if test="${not empty invalidLogin }">
				<div style="color: red; padding-top: 10px;">${invalidLogin }</div>
			</c:if>
			<c:if test="${empty invalidLogin }">
				<div style="color: red; padding-top: 28px;"></div>
			</c:if>
			<div style="padding-top: 5px;">
				&emsp;&emsp; &emsp; &emsp;Login: <input type="text" name="login"
					value="${login}" />
			</div>

			<c:if test="${not empty invalidPassword }">
				<div style="color: red; padding-top: 10px;">${invalidPassword }</div>
			</c:if>
			<c:if test="${empty invalidPassword }">
				<div style="color: red; padding-top: 28px;"></div>
			</c:if>
			<div style="padding-top: 5px;">
				&emsp;&emsp;&emsp;Password: <input type="password" name="password" />
			</div>

			<c:if test="${not empty invalidPasswordTwo }">
				<div style="color: red; padding-top: 10px;">${invalidPasswordTwo }</div>
			</c:if>
			<c:if test="${empty invalidPasswordTwo }">
				<div style="color: red; padding-top: 28px;"></div>
			</c:if>
			<div style="padding-top: 5px;">
				Repeat Password: <input type="password" name="passwordtwo" />
			</div>
		</form>
		<button type="submit" style="margin-top: 15px;" form="registerform"
			value="Submit">Register</button>
	</div>
</body>
</html>