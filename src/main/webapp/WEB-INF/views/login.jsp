<%--
  Created by IntelliJ IDEA.
  User: imooc
  Date: 2018/7/11
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="startLogin" method="get">
    <input type="text" id="username" name="username"/>
    <br>
    <input type="text" id="password" name="password"/>
    <br>
    <input type="text" id="kaptcha" name="kaptcha"/>
    <br>
    <img src="captcha.jpg" onclick="refresh(this)">
    <br>
    <input type="submit" value="登陆">
</form>
    <script>
        function refresh(obj){
            obj.src = "captcha.jpg?t="+new Date().getMilliseconds();
        }
    </script>
</body>
</html>
