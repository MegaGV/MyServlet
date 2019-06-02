package practice4.servlet;

import annotation.Entity;
import factory.Factory;
import practice4.service.EntityDefinition;
import practice4.service.EntityHelper;
import practice4.service.EntityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EntityViewServlet", urlPatterns = "/EntityViewServlet")
public class EntityViewServlet extends HttpServlet {
    public EntityViewServlet(){ super(); }
    public void init() throws ServletException { super.init(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String PK = request.getParameter("pk");

        String entityClassName = request.getParameter("class");
        Class clazz = EntityHelper.fromClassName(entityClassName);
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(clazz);

        EntityService service = Factory.getInstance().getServiceFactory().getEntityService();
        Object entity = service.findEntityByPK(clazz, PK);

        request.setAttribute("entity", entity);
        request.setAttribute("entityDefinition", entityDefinition);
        request.getRequestDispatcher("practice4/EntityView.jsp").forward(request, response);
    }

    public void destroy() { super.destroy(); }
}
