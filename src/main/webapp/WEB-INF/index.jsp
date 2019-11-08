<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2019/10/19
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <script src="statics/js/jquery-3.4.1.js"></script>
    <script>
        $(function(){
            $(".delBtn").click(function(){
                var delId = $(this).attr("delId");
                $.get("deleteUser",{uid:delId},function(data){
                    if(data == 'success'){
                        $("#tr"+delId).remove();
                    }else{
                        alert("删除失败");
                    }
                })
            });
        })
    </script>
</head>
<body>
    <a href="addUser">增加用户</a><a href="add">添加请假</a>
    <shiro:hasRole name="role1">
        <a href="getHolidays">待审批假条</a>
    </shiro:hasRole>
    <table>
        <tr>
            <th>序号</th>
            <th>用户名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${userList}" var="user" varStatus="i">
            <tr id="tr${user.uid}">
                <td>${i.count}</td>
                <td>${user.uname}</td>
                <td>${user.upwd}</td>
                <td><a href="editUser?uid=${user.uid}">修改</a>
                    <a href="javascript:void(0)" class="delBtn" delId="${user.uid}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
