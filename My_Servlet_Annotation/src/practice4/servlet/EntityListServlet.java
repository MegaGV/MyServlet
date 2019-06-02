package practice4.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Factory;
import practice4.service.EntityDefinition;
import practice4.service.EntityHelper;
import practice4.service.EntityService;

@WebServlet(name="EntityListServlet", urlPatterns = "/EntityListServlet")
public class EntityListServlet extends HttpServlet {
    public EntityListServlet() { super(); }

    public void init() throws ServletException  { super.init(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityClassName = request.getParameter("class");
        Class clazz = EntityHelper.fromClassName(entityClassName);
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(clazz);

        EntityService service = Factory.getInstance().getServiceFactory().getEntityService();

        String sPage = request.getParameter("page");
        int page;
        page = (sPage == null ? 1 : Integer.valueOf(sPage));
        int num_every_page = 10;
        double sum = service.size(clazz);
        int last_page = (int) Math.ceil(sum / num_every_page);
        if(sum == 0)
            last_page = 1;

        List<Object> entities = service.getEntities(clazz, page, num_every_page);

        request.setAttribute("entities", entities);
        request.setAttribute("entityDefinition", entityDefinition);

        request.setAttribute("page", page);
        request.setAttribute("num_every_page", num_every_page);
        request.setAttribute("last_page", last_page);
        request.setAttribute("sum",sum);

        request.getRequestDispatcher("practice4/Main.jsp").forward(request, response);
    }

    public void destroy() { super.destroy(); }
}

