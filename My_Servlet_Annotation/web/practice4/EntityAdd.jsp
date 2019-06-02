<%@ page import="java.util.List" %>
<%@ page import="practice4.service.EntityDefinition" %>
<%@ page import="practice4.service.FieldDefinition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        List<String> pks = (List<String>)request.getAttribute("pks");
        EntityDefinition entityDefinition = (EntityDefinition)request.getAttribute("entityDefinition");
    %>
    <title>EntityAdd</title>
</head>
<body>
<form action="EntityAddServlet" method="post">
    <input type="text" style="display: none;" name="class" value="<%=entityDefinition.getClazz().getName()%>" />
    <table border="1">
        <%for(FieldDefinition fieldDefinition : entityDefinition.getFieldDefinitions()) {
            fieldDefinition.getField().setAccessible(true);%>
        <tr>
            <td><%=fieldDefinition.getLabel() %></td>
            <td>
                <input type="text" name="<%=fieldDefinition.getFieldName()%>" />
            </td>
        </tr>
        <%}%>
    </table>
    <button type="submit" onclick="return check()" >提交</button>
    <a href="EntityListServlet?class=entity.Teacher">返回</a>
</form>
</body>
</html>

<script>
    function check() {
        let values = document.getElementsByTagName("input");
        let isPK = [];
        isPK.push(false);

        <%for(FieldDefinition fieldDefinition : entityDefinition.getFieldDefinitions()) {%>
            if(<%=fieldDefinition.isPK()%>)
                isPK.push(true);
            else
                isPK.push(false);
        <%}%>

        for(let i = 1; i < values.length; i++){
            if(values[i].value === ""){
                alert("尚有数据为空");
                return false;
            }
            if(isPK[i]){
                <%for(String pk : pks){%>
                    s = <%=pk%>;
                    if(s == values[i].value){
                        alert("已存在以该内容为主键的对象");
                        return false;
                    }
                <%}%>
            }
        }

        return true;
    }
</script>
