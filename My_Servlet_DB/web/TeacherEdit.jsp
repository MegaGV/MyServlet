<%@ page import="practice2.entity.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改教师信息</title>
    <link type="text/css" rel="stylesheet" href="css/information.css"/>
    <link type="text/css" rel="stylesheet" href="css/table.css"/>
</head>
<body>
<%
    Teacher teacher = (Teacher)request.getAttribute("teacher");
%>
<form action="TeacherSaveServlet" method="post">
    <div id="teacher_information">
        <div id="teacher_information_title">
            <p id="title" style="margin: 0;padding-left: 20px;line-height: 40px">修改教师信息</p>
        </div>
        <div id="teacher_information_content">
            id &ensp;&nbsp;
            <input type="text" name="id" style="display: none" value="<%=teacher.getId() %>">
            <input disabled type="text" value="<%=teacher.getId() %>" /><br/>

            姓名
            <input type="text" name="name" id="Name" value="<%=teacher.getName() %>" /><br/>

            学院
            <input type="text" name="college" id="college" value="<%=teacher.getCollege() %>" /><br/>

            专业
            <input type="text" name="major" id="major" value="<%=teacher.getMajor() %>" /><br/>

            生日
            <input type="text" name="birthday" id="birthday" value="<%=teacher.getBirthday() %>" /><br/>

            薪水
            <input type="text" name="salary" id="salary" value="<%=teacher.getSalary() %>" /><br/>
        </div>
        <div id="teacher_information_bottom">
            <a href="TeacherListServlet?page=1" class="btn"  style="background-color: red;float:right;margin:0 10px;text-decoration:none">取消</a>
            <input type="submit" onclick="return check()" class="btn" id="operate" style="background-color: #5cb85c;float:right"/>
        </div>
    </div>
</form>

<script>
    function check() {
        if (Name.value === ""){ alert("姓名不能为空"); return false; }
        else if (college.value === ""){ alert("学院不能为空"); return false; }
        else if (major.value === ""){ alert("专业不能为空"); return false; }
        else if (birthday.value === ""){ alert("生日不能为空"); return false; }
        else if (isNaN(birthday.value)){ alert("生日必须为数字"); return false; }
        else if (salary.value === ""){ alert("薪水不能为空"); return false; }
        else if (isNaN(salary.value)){ alert("薪水必须为数字"); return false; }
        else { alert("保存成功"); return true;}
    }
</script>
</body>
</html>


