<%@ page import="practice4.service.EntityDefinition" %>
<%@ page import="practice4.service.FieldDefinition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        EntityDefinition entityDefinition = (EntityDefinition)request.getAttribute("entityDefinition");
        Object entity = (Object) request.getAttribute("entity");
    %>
    <title>EntityEdit</title>
</head>
<body>
<form action="EntitySaveServlet" method="post">
    <input type="text" style="display: none;" name="class" value="<%=entityDefinition.getClazz().getName()%>" />
    <table border="1">
        <%for(FieldDefinition fieldDefinition : entityDefinition.getFieldDefinitions()) {
            fieldDefinition.getField().setAccessible(true);
            Object value = null;
            try {
                value = fieldDefinition.getField().get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }%>
        <tr>
            <td><%=fieldDefinition.getLabel() %></td>
            <td>
                <%if (fieldDefinition.isPK()){%>
                    <input disabled type="text" value="<%=value%>" />
                    <input type="text" style="display: none;" name="<%=fieldDefinition.getFieldName()%>" value="<%=value%>" />
                <%continue;}%>
                <%if (!fieldDefinition.isPK())%>
                    <input type="text" name="<%=fieldDefinition.getFieldName()%>" value="<%=value%>" />
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
        for(let i = 0; i < values.length; i++){
            if(values[i].value === ""){
                alert("尚有数据为空");
                return false;
            }
        }
        return true;
    }
</script>
