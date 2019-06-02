<%@ page import="practice4.service.EntityDefinition" %>
<%@ page import="practice4.service.FieldDefinition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        EntityDefinition entityDefinition = (EntityDefinition)request.getAttribute("entityDefinition");
        Object entity = (Object) request.getAttribute("entity");
    %>
    <title>EntityView</title>
</head>

<body>
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
        <td><input disabled type="text" value="<%=value%>" /></td>
    </tr>
    <%}%>
</table>
<a href="EntityListServlet?class=entity.Teacher">返回</a>
</body>
</html>
