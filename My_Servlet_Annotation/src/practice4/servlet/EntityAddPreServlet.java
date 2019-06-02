package practice4.servlet;

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
import java.util.List;

@WebServlet(name = "EntityAddPreServlet", urlPatterns = "/EntityAddPreServlet")
public class EntityAddPreServlet extends HttpServlet {
    public EntityAddPreServlet(){ super(); }
    public void init() throws ServletException { super.init(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityClassName = request.getParameter("class");
        Class clazz = EntityHelper.fromClassName(entityClassName);
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(clazz);

        EntityService service = Factory.getInstance().getServiceFactory().getEntityService();
        List<String> pks = service.getAllPK(clazz);

        request.setAttribute("pks", pks);
        request.setAttribute("entityDefinition", entityDefinition);
        request.getRequestDispatcher("practice4/EntityAdd.jsp").forward(request, response);
    }

    public void destroy() { super.destroy(); }
}
