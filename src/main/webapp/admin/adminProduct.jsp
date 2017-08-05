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
	<h2>All Products</h2>
        <div>
	<form action="adminproductmanager" method="get">
	<table border=1 class="tab">
		<tr>
			<th>Select</th>
			<th>ID</th>
			<th>Name</th>
			<th>Price</th>
			<th>In Stock</th>
		</tr>
		<c:forEach items="${Products }" var="item">
			<tr>
				<td><input type="radio" name="row" value="${item.id }"></td>
				<td>${item.id}</td>
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td>${item.count}</td>
			</tr>
		</c:forEach>
	</table>
	<input style="margin-left:100px;margin-top:10px;" type="submit" value="Select"/>
</form>
    </div>
	<div style="margin-left:50px;">
	<h2>Add Product Form</h2>
	<c:if test='${not empty addedmessage }'>
		<p style="color: green;">${addedmessage }</p>
	</c:if>
	<form method="post" action="adminproductmanager" id="addform">
	   <c:if test='${not empty nameNotValid}'>
            <span style="color: red;">${nameNotValid}
            </span>
        </c:if>
		Name: <input type="text" name="name" value="${addname}" />
		<c:if test='${not empty priceNotValid}'>
			<span style="color: red;">${priceNotValid}
			</span>
		</c:if>
		Price: <input type="text" name="price" value="${addprice }" />
		<c:if test='${not empty countNotValid}'>
			<span style="color: red;">${countNotValid}
			</span>
		</c:if>
		Stock: <input type="text" name="count" value="${addcount}" /> <input
			type="submit" name="bt" value="Add">
			<br>
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
		<form method="post" action="adminproductmanager" id="upform">
			Id: <input type="text" name="upid" value="${upid}" disabled />
			<input type="hidden" name="hupid" value="${upid}"/>
			<input type="hidden" name="target" value="${target}"/>
			 <c:if test='${not empty upnameNotValid}'>
            <span style="color: red;">${upnameNotValid}
            </span>
        </c:if>
			Name: <input type="text" name="upname" value="${upname}" />
			<c:if test='${not empty uppriceNotValid}'>
				<span style="color: red;">${uppriceNotValid}
				</span>
			</c:if>
			Price: <input type="text" name="upprice" value="${upprice }" />
			<c:if test='${not empty upcountNotValid}'>
				<span style="color: red;">${upcountNotValid}
				</span>
			</c:if>
			Stock: <input type="text" name="upcount" value="${upcount}" /> <input
				type="submit" name="bt" value="Update"/> <input type="submit"
				name="bt" value="Delete"/>
		</form>
	</c:if>
        </div>

        <div class="refer">
	<a href="admin.jsp">Back to main admin page</a><br>
	<a href="../MainPageController">Back to main page</a>
        </div>
</body>
</html>