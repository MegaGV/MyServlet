<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Teacher" %>
<html>
<head>
    <title>TeacherList</title>
    <link type="text/css" rel="stylesheet" href="css/table.css"/>
    <link type="text/css" rel="stylesheet" href="css/information.css"/>

    <%
        List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");
        int num_every_page = (int) request.getAttribute("num_every_page");
        int last_page = (int) request.getAttribute("last_page");
        int current_page = (int) request.getAttribute("page");
        int sum = (int) (double)request.getAttribute("sum");
    %>

</head>
<body>

<div id="whole_page">
    <div id="head">
        <strong>———————————————————</strong>
        <strong style="color: #9ea09e;margin: 0 98px">教师信息管理系统</strong>
        <strong>———————————————————</strong>
    </div>

    <div id="option">
        <a href="TeacherAddPreServlet" class="btn"  style="background-color: green;float:left;text-decoration:none">新增</a>
        <form action="TeacherDeleteServlet" method="post">
            <input type="submit" onclick="return del_confirm()" class="btn" value="删除" style="background-color: red;float:left;margin-left: 15px;text-decoration:none"/>
            <input type="text" name="deletes" id="deletes" style="display: none"/>
        </form>
        <a href="TeacherSortServlet?sort=id" class="btn"  style="background-color: blue;float:left;margin-left: 15px;text-decoration:none">按id排序</a>
        <a href="TeacherSortServlet?sort=birthday" class="btn" style="background-color: yellow;float:left;margin-left: 15px;text-decoration:none">按年龄排序</a>
    </div>

    <div id="content" >
        <table id="table">
            <thead id="table_head">
            <th style="width: 54px;" class="cell"><label>
                <input type="checkbox" name="all_chk_box" onclick="checkAll()"/>
            </label></th>
            <th style="width: 81px" class="cell">序号</th>
            <th style="width: 166px" class="cell">id</th>
            <th style="width: 148px" class="cell">姓名</th>
            <th style="width: 229px" class="cell">学院</th>
            <th style="width: 136px" class="cell">专业</th>
            <th style="width: 127px" class="cell">生日</th>
            <th style="width: 123px" class="cell">薪水</th>
            <th style="width: 139px" class="cell">操作</th>
            </thead>

            <%
                if(!teachers.isEmpty())
                for(int i = 0; i < teachers.size(); i++) {
            %>
            <%if (i % 2 == 0){%>
            <tr onmouseover="over(this)" onmouseout="out(this)" style="background-color: #eef1f8">
            <%}%>
            <%if (i % 2 != 0){%>
            <tr onmouseover="over(this)" onmouseout="out(this)" style="background-color: white">
            <%}%>
                <td class="cell"><input type="checkbox" name="chk_box"></td>
                <td class="cell"><%=i + (current_page - 1) * num_every_page + 1%></td>
                <td class="cell"><%=teachers.get(i).getId()%></td>
                <td class="cell"><%=teachers.get(i).getName()%></td>
                <td class="cell"><%=teachers.get(i).getCollege()%></td>
                <td class="cell"><%=teachers.get(i).getMajor()%></td>
                <td class="cell"><%=teachers.get(i).getBirthday()%></td>
                <td class="cell"><%=teachers.get(i).getSalary()%></td>
                <td class="cell">
                    <a href="TeacherCheckServlet?id=<%=teachers.get(i).getId()%>" style="color:red;text-decoration:none">查看</a>
                    <a href="TeacherEditServlet?id=<%=teachers.get(i).getId()%>" style="color:red;text-decoration:none">修改</a>
                </td>
            </tr>
            <%} %>
            </tbody>
        </table>
    </div>

    <div id="bottom">
        <span id="data">第<%=current_page%>页,共<%=sum%>条,(每页显示<%=num_every_page%>条)</span>
        <form action="PageJumpServlet" method="post" style="line-height: 38px;">
            <span style="margin-left: 150px"></span>请输入页数
            <input type="text" name="input_pages" id="input_pages" />
            <input type="submit" onclick="return checkpage()" value="跳转" />
            <input type="submit" onclick="return page_donw()" class="btn" value="下一页" style="background-color: #d9534f;float:right;margin-left: 10px;text-decoration:none"/>
            <input type="submit" onclick="return page_up()" class="btn" value="上一页" style="background-color: #5cb85c;float:right;text-decoration:none"/>
        </form>
        <form action="TeacherDeleteAllServlet" method="post">
            <input type="submit" onclick="return del_all_confirm()" value="全部删除" style="position: relative;left:700px;top:-46px"/>
        </form>

    </div>

</div>

<script>
    function checkAll(){
        let status = document.getElementsByName("all_chk_box")[0].checked;
        let chk_boxs = document.getElementsByName("chk_box");
        for(let i=0; i<chk_boxs.length; i++){
            chk_boxs[i].checked=status;
        }
    }

    function del_all_confirm() {
        return confirm("确定要全部删除吗");
    }

    function del_confirm() {
        let current_page = <%=current_page%>;
        let num_every_page = <%=num_every_page%>;
        let answer = confirm("确定要删除吗");
        if (answer){
            let boxs = document.getElementsByName("chk_box");
            let deletebox = [];
            let i = 0;
            <% if(!teachers.isEmpty())
                for(int i = 0; i < teachers.size() ;i++){%>
            if(boxs[i].checked)
                deletebox.push(<%=teachers.get(i).getId()%>);
            i++;
            <% } %>
            let num = deletebox.length;
            if (num === 0){
                alert("未选中任何数据");
                return false;
            }
            else {
                deletes.value = deletebox.join(' ');
                return true;
            }
        }
        else
            return false;
    }

    function page_up() {
        let current_page = <%=current_page%>;
        if(current_page === 1) {
            alert("已经是第一页了");
            return false;
        }
        else {
            input_pages.value = current_page - 1;
            return true;
        }
    }
    function page_donw() {
        let current_page = <%=current_page%>;
        let last_page = <%=last_page%>;
        if (current_page === last_page){
            alert("已经是最后一页了");
            return false;
        }
        else {
            input_pages.value = current_page + 1;
            return true;
        }
    }
    function checkpage() {
        let last_page = <%=last_page%>;
        if (input_pages.value === ""){ alert("页数不能为空"); return false;}
        else if (isNaN(input_pages.value)){ alert("页数必须为数字"); return false; }
        else if (input_pages.value < 1 || input_pages.value > last_page){ alert("超出页面范围"); return false;}
        else return true;
    }

    let color;
    function over(self) {
        color = self.style.backgroundColor;
        self.setAttribute("style", "background-color:gray");
    }
    function out(self) {
        self.setAttribute("style", "background-color:" + color);
    }
</script>

</body>
</html>
