<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2019/10/25
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="saveHoliday" method="post">
        开始日期：<input type="date" name="startDate"/><br>
        结束日期:<input type="date" name="endDate"/><br>
        请假理由：<input type="text" name="reason"><br>
        <input type="submit">
    </form>
</body>
</html>
