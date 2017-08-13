<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
    <link href="<c:url value="/asserts/css/main.css" />" rel="stylesheet">
</head>
<body>

<h2>Login</h2>
<form:form method="POST" action="request" modelAttribute="loginForm">
  
   <table>
   <tr>
        <td><form:label path="username">Username</form:label></td>
        <td><form:input path="username" /></td>
        <td><form:errors path="username" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:password  path="password" /></td>
        <td><form:errors path="password" cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>