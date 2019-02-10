<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
    
<html>
<body>
<h2>Update Chocolates</h2>

<form:form action="update.do" commandName="updateChocolate">
Name : ${updateChocolate.name}
<form:hidden path="name"/>
Brand : ${updateChocolate.brand}
<form:hidden path="brand"/>
Price : <form:input path="price"/>
<input type="submit">
<input type="reset">
</form:form>
</body>
</html>