<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>

<html>
<body>
<h2>Add Chocolates</h2>
<form:form action="/add.do" commandName="chocolate">
Name : <form:input path="name"/>
Brand : <form:input path="brand"/>
Price : <form:input path="price"/>
<input type="submit">
<input type="reset">
</form:form>
</body>
</html>
