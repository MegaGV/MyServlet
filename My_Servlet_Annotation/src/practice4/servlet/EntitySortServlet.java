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

@WebServlet(name = "EntitySortServlet", urlPatterns = "/EntitySortServlet")
public class EntitySortServlet extends HttpServlet {
    public EntitySortServlet(){ super(); }
    public void init() throws ServletException { super.init(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");

        EntityService service = Factory.getInstance().getServiceFactory().getEntityService();
        service.sort(sort);

        request.getRequestDispatcher("EntityListServlet?class=entity.Teacher").forward(request, response);
    }

    public void destroy() { super.destroy(); }
}
