package practice4.servlet;

import factory.Factory;
import practice4.service.EntityDefinition;
import practice4.service.EntityHelper;
import practice4.service.EntityService;
import practice4.service.FieldDefinition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "EntitySaveServlet", urlPatterns = "/EntitySaveServlet")
public class EntitySaveServlet extends HttpServlet {
    public EntitySaveServlet(){ super(); }
    public void init() throws ServletException { super.init(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object entity = null;

        String entityClassName = request.getParameter("class");
        Class clazz = EntityHelper.fromClassName(entityClassName);
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(clazz);

        try {
            entity = clazz.newInstance();
            for(FieldDefinition fieldDefinition : entityDefinition.getFieldDefinitions()) {
                fieldDefinition.getField().setAccessible(true);
                if(!fieldDefinition.isString())
                    fieldDefinition.getField().set(entity, Integer.valueOf(request.getParameter(fieldDefinition.getFieldName())));
                else
                    fieldDefinition.getField().set(entity, new String(request.getParameter(fieldDefinition.getFieldName()).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        EntityService entityService = Factory.getInstance().getServiceFactory().getEntityService();
        entityService.saveEntity(entity);

        request.getRequestDispatcher("EntityListServlet?class=entity.Teacher").forward(request, response);
    }

    public void destroy() { super.destroy(); }
}
