<%--
  Created by IntelliJ IDEA.
  User: qlqkt
  Date: 2023-06-15
  Time: 오전 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>
<form action="login" method="POST">
    <label for="id">Id:</label>
    <input type="text" id="id" name="id"><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
