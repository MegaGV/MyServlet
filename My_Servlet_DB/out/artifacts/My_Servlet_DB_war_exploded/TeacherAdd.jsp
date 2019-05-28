<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>添加教师信息</title>
    <link type="text/css" rel="stylesheet" href="css/information.css"/>
    <link type="text/css" rel="stylesheet" href="css/table.css"/>
</head>
<body>
<%
    List<String> ids = (List<String>)request.getAttribute("ids");
%>
<form action="TeacherAddServlet" method="post">
    <div id="teacher_information">
        <div id="teacher_information_title">
            <p id="title" style="margin: 0;padding-left: 20px;line-height: 40px">查看教师信息</p>
        </div>
        <div id="teacher_information_content">
            id &ensp;&nbsp;
            <input type="text" name="id" id="id" /><br/>

            姓名
            <input type="text" name="name" id="Name" /><br/>

            学院
            <input type="text" name="college" id="college" /><br/>

            专业
            <input type="text" name="major" id="major" /><br/>

            生日
            <input type="text" name="birthday" id="birthday" /><br/>

            薪水
            <input type="text" name="salary" id="salary" /><br/>
        </div>
        <div id="teacher_information_bottom">
            <a href="TeacherListServlet?page=1" class="btn"  style="background-color: red;float:right;margin:0 10px;text-decoration:none">取消</a>
            <input onclick="return check()" type="submit" class="btn" value="提交" id="operate" style="background-color: #5cb85c;float:right"/>
        </div>
    </div>
</form>

<script>
    function check() {
        let ids = [];
        <% for (String id : ids) { %>
            ids.push(<%=id%>);
        <% } %>

        if (id.value === ""){ alert("id不能为空"); return false; }
        else if (isNaN(id.value)) { alert("id必须为数字"); return false; }
        else if (ids.indexOf(id.value) > -1) { alert("已存在该id的教师"); return false; }
        else if (Name.value === ""){ alert("姓名不能为空"); return false; }
        else if (college.value === ""){ alert("学院不能为空"); return false; }
        else if (major.value === ""){ alert("专业不能为空"); return false; }
        else if (birthday.value === ""){ alert("生日不能为空"); return false; }
        else if (isNaN(birthday.value)){ alert("生日必须为数字"); return false; }
        else if (salary.value === ""){ alert("薪水不能为空"); return false; }
        else if (isNaN(salary.value)){ alert("薪水必须为数字"); return false; }
        else { alert("添加成功!"); return true; }
    }
</script>

</body>
</html>
