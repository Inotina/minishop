<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="text/css" rel="stylesheet" href="../style/admin.css" />

<title>Product management</title>
</head>
<body>
    <div>
	<h2>All Purchases</h2>
	<form action="adminpurchasemanager" method="get">
	<table border=1>
		<tr>
			<th>Select</th>
			<th>ClientID</th>
			<th>Phone</th>
			<th>Adress</th>
			<th>Products</th>
			<th>Date</th>
		</tr>
		<c:forEach items="${Purchase }" var="item">
			<tr>
				<td><input type="radio" name="row" value="${item.purchaseId }"></td>
				<td>${item.userId}</td>
				<td>${item.phone}</td>
				<td>${item.adress}</td>
				<td>${item.products}</td>
				<td>${item.date}</td>
			</tr>
		</c:forEach>
	</table>
	<input style="margin-left:100px;margin-top:10px;" type="submit" value="Select"/>
	</form>
    </div>
    <div style="margin-left:50px;">
	<c:if test='${not empty updatedmessage }'>
		<p style="color: green;">${updatedmessage }</p>
	</c:if>
	<c:if test='${not empty deletedmessage }'>
		<p style="color: green;">${deletedmessage }</p>
	</c:if>

	<c:if test='${not empty target }'>
		<h2>Update\Delete Form</h2>
		<form method="post" action="adminpurchasemanager" id="upform">
			PurchaseId: <input type="text" name="uppurchaseid" value="${uppurchaseid}" disabled />
			<input type="hidden" name="huppurchaseid" value="${uppurchaseid}"/>
			<input type="hidden" name="target" value="${target}"/>
			 <c:if test='${not empty upclientIdNotValid}'>
            <span style="color: red;">${upclientIdNotValid}
            </span>
        </c:if>
			ClientId: <input type="text" name="upclientid" value="${upclientid}" />
			<c:if test='${not empty upphoneNotValid}'>
				<span style="color: red;">${upphoneNotValid}
				</span>
			</c:if>
			Phone: <input type="text" name="upphone" value="${upphone }" />
			<c:if test='${not empty upadressNotValid}'>
				<span style="color: red;">${upadressNotValid}
				</span>
			</c:if>
			Adress: <input type="text" name="upadress" value="${upadress}" /> 
			     <c:if test='${not empty upproductsNotValid}'>
                <span style="color: red;">${upproductsNotValid}
                </span>
            </c:if>
            Products: <input type="text" name="upproducts" value="${upproducts}" /> 
			
			Date: <input type="text" name="update" value="${hupdate}" disabled /> 
			<input type="hidden" name="hupdate" value="${hupdate}" /> 
			<input type="submit" name="bt" value="Update"/>
			 <input type="submit" name="bt" value="Delete"/>
		</form>
	</c:if>
    </div>

<div class="refer">
	<a href="admin.jsp">Back to main admin page</a><br>
	<a href="../MainPageController">Back to main page</a>
    </div>
</body>
</html>