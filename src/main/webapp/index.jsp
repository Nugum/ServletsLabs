<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 25.02.2020
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enter</title>
</head>
<body>
    <h2>Enter</h2>
    <form action="IndexServlet" method="post">
        <label>Login</label>
        <input  name="u_login"><br>
        <label>Password</label>
        <input name="u_password" type="password"><br>
        <input type="submit" value="Enter">
    </form>
</body>
</html>
