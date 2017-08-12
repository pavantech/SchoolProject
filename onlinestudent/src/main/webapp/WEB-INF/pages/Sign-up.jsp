<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" action="addStudent" modelAttribute="userForm">
  
   <table>
   <tr>
        <td><form:label path="username">Username</form:label></td>
        <td><form:input path="username" /></td>
        <td><form:errors path="username" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="firstname">First Name</form:label></td>
        <td><form:input path="firstname" /></td>
        <td><form:errors path="firstname" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="lastname">Last Name</form:label></td>
        <td><form:input path="lastname" /></td>
        <td><form:errors path="lastname" cssClass="error" /></td>
    </tr>
    
    <tr>
        <td><form:label path="email">Email</form:label></td>
        <td><form:input path="email" /></td>
        <td><form:errors path="email" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:password  path="password" /></td>
        <td><form:errors path="password" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="phone">Mobile</form:label></td>
        <td><form:input  path="phone" /></td>
        <td><form:errors path="phone" cssClass="error" /></td>
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