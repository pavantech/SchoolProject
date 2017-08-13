<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Maven + Spring MVC Web Project Example</h1>

<h2>Message : ${message}</h2>
<h2>Counter : ${counter}</h2>
<table align="center">
            <tr>
                <td><a href="login">Login</a>
                </td>
                <td><a href="register">Register</a>
                </td>
            </tr>

        </table>
        <c:if test="${not empty lists}">

		<ul>
			<c:forEach var="listValue" items="${lists}">
				<li>${listValue.getUsername()}</li>
				<li>${listValue.getPassword()}</li>
			</c:forEach>
		</ul>

	</c:if>
        

</body>
</html>