package practice4.servlet;

import factory.Factory;
import practice4.service.EntityHelper;
import practice4.service.EntityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EntityDeleteAllServlet", urlPatterns = "/EntityDeleteAllServlet")
public class EntityDeleteAllServlet extends HttpServlet {
    public EntityDeleteAllServlet() { super(); }
    public void init() throws ServletException  { super.init(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityClassName = request.getParameter("class");
        Class clazz = EntityHelper.fromClassName(entityClassName);

        EntityService service = Factory.getInstance().getServiceFactory().getEntityService();
        service.deleteAll(clazz);

        request.getRequestDispatcher("EntityListServlet?class=entity.Teacher").forward(request, response);
    }

    public void destroy() { super.destroy(); }
}
