<%@page import="practice4.service.FieldDefinition"%>
<%@page import="practice4.service.EntityDefinition"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%
		List<Object> entities = (List<Object>)request.getAttribute("entities");
		EntityDefinition entityDefinition = (EntityDefinition)request.getAttribute("entityDefinition");
		int num_every_page = (int) request.getAttribute("num_every_page");
		int last_page = (int) request.getAttribute("last_page");
		int current_page = (int) request.getAttribute("page");
		int sum = (int) (double)request.getAttribute("sum");
	%>
<meta charset="UTF-8">
<title><%=entityDefinition.getLabel() %></title>
</head>
<body>
<a href="EntityPreAddServlet?class=<%=entityDefinition.getClazz().getName()%>">新增</a><br>
<a href="EntitySortServlet?sort=id" >按id排序</a>
<a href="EntitySortServlet?sort=birthday" style="margin-left: 10px">按年龄排序</a>
<table border="1">
	<tr>
	<%
		for(FieldDefinition fieldDefinition : entityDefinition.getFieldDefinitions()) {
			fieldDefinition.getField().setAccessible(true);
	%>
		<td><%=fieldDefinition.getLabel() %></td>
	<%
		}
	%>
		<td>操作</td>
	</tr>
	<% for(Object entity: entities)  { %>
	  <tr>
		<% 
		StringBuilder pk= new StringBuilder();
		for(FieldDefinition fieldDefinition:entityDefinition.getFieldDefinitions()) {
			Object value = null;
			try {
				value = fieldDefinition.getField().get(entity);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(fieldDefinition.isPK())
				pk.append(value).append(" ");
		%>
		<td><%=value%></td>
		<%} %>

		  <td>
			<a href="EntityViewServlet?pk=<%=pk.toString() %>&class=<%=entityDefinition.getClazz().getName()%>">查看</a>
			<a href="EntityEditServlet?pk=<%=pk.toString() %>&class=<%=entityDefinition.getClazz().getName()%>">编辑</a>
			<a href="EntityDeleteServlet?pk=<%=pk.toString() %>&class=<%=entityDefinition.getClazz().getName()%>" onclick="return del_confirm()">删除</a>
		  </td>
	  </tr>
	<%} %>
</table>
<span id="data">第<%=current_page%>页,共<%=sum%>条,(每页显示<%=num_every_page%>条)</span>
<form action="EntityDeleteAllServlet?class=<%=entityDefinition.getClazz().getName()%>" method="post">
	<input type="submit" onclick="return del_all_confirm()" value="全部删除" />
</form>
<form action="EntityPageJumpServlet" method="post">
	<span style="margin-left: 10px"></span>请输入页数
	<input type="text" name="input_pages" id="input_pages" />
	<input type="submit" onclick="return checkpage()" value="跳转" /><br>
	<input type="submit" onclick="return page_up()" value="上一页" />
	<input type="submit" onclick="return page_donw()"  value="下一页" />
</form>

<div>
	<a href="TeacherListServlet?page=1">返回</a>
</div>
</body>

<script>
	function del_confirm() {
		return confirm("确定要删除吗");
	}
	function del_all_confirm() {
		return confirm("确定要全部删除吗");
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
</script>
</html>