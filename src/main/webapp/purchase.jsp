<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Purchase page</title>
    <link type="text/css" rel="stylesheet" href="./style/main.css" />
<body>
	<h2>Super shop</h2>
	<div>
		<h2>Your Cart</h2>
		<c:if test='${not empty emptyCart}'>
		${emptyCart}<br>
		</c:if>
		<c:if test='${not empty stockmessage}'>
			<p style="color: red;">${stockmessage }</p>
		</c:if>
		<div class="cart">
			<c:forEach items="${Cart}" var="item">
				<div style="padding-bottom: 5px;">
					<span>${item.key}</span>: ${item.value} <a
						href="cart?name=${item.key }&cart=remove&id=${Product.id}"><input
						type="button" value="Remove" /></a>
				</div>
			</c:forEach>
			<div style="padding-bottom: 7px;">
				<a href="cart?cart=clear&id=${Product.id}"><input type="button"
					value="Clear" /></a>
			</div>
		</div>
		<c:if test="${not empty Cart}">
			<div class="regist">
				<form action="purchase" method="post" id="purform">
					<c:if test='${not empty idNotValid}'>
						<div style="color: red; padding-top: 10px;">${idNotValid}</div>
					</c:if>
					<c:if test='${empty idNotValid}'>
						<div style="color: red; padding-top: 28px;"></div>
					</c:if>
					<div style="padding-top: 5px;">
						Your ID: <input type="text" name="clientId" value="${clientId }">
					</div>
					<c:if test='${not empty phoneNotValid}'>
						<div style="color: red; padding-top: 10px;">${phoneNotValid}
						</div>
					</c:if>
					<c:if test='${empty phoneNotValid}'>
						<div style="color: red; padding-top: 28px;"></div>
					</c:if>
					<div style="padding-top: 5px;">
						Phone:&emsp;<input type="text" name="phone" value="${phone }">
					</div>
					<c:if test='${not empty adressNotValid}'>
						<div style="color: red; padding-top: 10px;">${adressNotValid}</div>
					</c:if>
					<c:if test='${empty adressNotValid}'>
						<div style="color: red; padding-top: 28px;"></div>
					</c:if>
					<div style="padding-top: 5px;">
						Adress:&emsp;<input type="text" name="adress" value="${adress }"/>
					</div>
				</form>
				<button style="margin-top:5px;" type="submit" form="purform" value="Submit">Submit</button>
			</div>
		</c:if>
	</div>
	<a href="MainPageController">Back to Mainpage</a>
</body>
</html>
