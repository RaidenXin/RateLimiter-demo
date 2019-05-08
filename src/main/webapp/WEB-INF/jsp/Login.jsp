<%--
  Created by IntelliJ IDEA.
  User: Raiden
  Date: 2019/4/4
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <from action="/login" method="post">
        <input type="hidden" name="token"/>
        <input type="text" name="userName" title="账号"/>
        <input type="text" name="password" title="密码"/>
    </from>
</body>
</html>
