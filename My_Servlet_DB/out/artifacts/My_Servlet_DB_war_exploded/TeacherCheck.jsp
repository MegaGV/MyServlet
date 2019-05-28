<%@ page import="entity.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看教师信息</title>
    <link type="text/css" rel="stylesheet" href="css/information.css"/>
    <link type="text/css" rel="stylesheet" href="css/table.css"/>
</head>
<body>
<%
    Teacher teacher = (Teacher)request.getAttribute("teacher");
%>
<form action="TeacherCheckServlet" method="get">
    <div id="teacher_information">
        <div id="teacher_information_title">
            <p id="title" style="margin: 0;padding-left: 20px;line-height: 40px">查看教师信息</p>
        </div>
        <div id="teacher_information_content">
            id &ensp;&nbsp;
            <input disabled type="text" name="id" value="<%=teacher.getId()%>" /><br/>

            姓名
            <input disabled type="text" name="name" value="<%=teacher.getName()%>" /><br/>

            学院
            <input disabled type="text" name="college" value="<%=teacher.getCollege()%>" /><br/>

            专业
            <input disabled type="text" name="major" value="<%=teacher.getMajor()%>" /><br/>

            生日
            <input disabled type="text" name="birthday" value="<%=teacher.getBirthday()%>" /><br/>

            薪水
            <input disabled type="text" name="salary" value="<%=teacher.getSalary()%>" /><br/>
        </div>
        <div id="teacher_information_bottom">
            <a href="TeacherListServlet?page=1" class="btn"  style="background-color: red;float:right;margin:0 10px;text-decoration:none">取消</a>
        </div>
    </div>
</form>

</body>
</html>
