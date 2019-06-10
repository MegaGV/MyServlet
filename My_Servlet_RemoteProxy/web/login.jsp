<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师管理系统登录</title>
</head>
<body>

<%
    String msg = (String)request.getAttribute("msg") ;
    msg = (msg ==null ? "" : msg);
%>

<div style="width:1000px; height:600px; margin:0 auto;">
    <form action="LoginServlet" method="post" style="border: solid 3px;font-size: 20px; text-align: center">
        <h1 style="margin-top: 80px; text-align: center;">教师管理系统</h1>
        <p style="margin-top:80px;">
            帐号：<label>
            <input type="text" name="username" />
        </label>
        </p>
        <p style="margin-top:80px;">
            密码：<label>
            <input type="password" name="password" />
        </label></p>
        <p style="color:red"><%=msg %></p>
        <p style="margin-top:80px;">
            <input type="submit" value="登录" style="background-color: RGB(22, 155, 213); color: white;padding:20px 100px;" />
        </p>
    </form>
</div>
</body>
</html>
