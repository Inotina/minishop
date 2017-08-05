<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Super admin page</title>
</head>
<body>
    <div style="text-align:center;">
<h2>Welcome ${User.name}!</h2>
<a href="adminproductmanager">Product management</a><br>
<a href="adminpurchasemanager">Purchase management</a>
    </div>
</body>
</html>