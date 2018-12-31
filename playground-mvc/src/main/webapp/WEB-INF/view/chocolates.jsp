<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<body>
<h2>All Chocolates</h2>
<table>
<th>
<td>Name</td>
<td>Brand</td>
<td>Price</td>
<td>Action</td>
</th>
<c:forEach  items="${chocolates}" var="chocolate">
<tr>
<td>${chocolate.name}</td>
<td>${chocolate.brand}</td>
<td>${chocolate.price}</td>
<td><a href="/buy.do?name=${chocolate.name}">buy</a></td>
</tr>
</c:forEach>
</table>
<hr>
<h2>Session Chocolates</h2>
<table>
<th>
<td>Name</td>
<td>Brand</td>
<td>Price</td>
</th>
<c:forEach  items="${sessionChocolates}" var="chocolate">
<tr>
<td>${chocolate.name}</td>
<td>${chocolate.brand}</td>
<td>${chocolate.price}</td>
</tr>
</c:forEach>
</table>

<hr>
<h2>Request Chocolates</h2>
<table>
<th>
<td>Name</td>
<td>Brand</td>
<td>Price</td>
</th>
<c:forEach  items="${requestChocolates}" var="chocolate">
<tr>
<td>${chocolate.name}</td>
<td>${chocolate.brand}</td>
<td>${chocolate.price}</td>
</tr>
</c:forEach>
</table>
</body>
</html>
