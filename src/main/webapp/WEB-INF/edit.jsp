<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2019/10/19
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="updateUser" method="post">
        <input type="hidden" name="uid" value="${user.uid}">
        用户名：<input type="text" name="uname" value="${user.uname}">
        密码：<input type="password" name="upwd" value="${user.upwd}">
        <input type="submit">
    </form>
</body>
</html>
