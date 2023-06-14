<%--
  Created by IntelliJ IDEA.
  User: qlqkt
  Date: 2023-06-14
  Time: 오후 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/register" method="POST">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="level">Level:</label>
    <input type="text" id="level" name="level" required><br><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="role">Role:</label>
    <input type="text" id="role" name="role" required><br><br>

    <input type="submit" value="Submit">
</form>

</body>
</html>
