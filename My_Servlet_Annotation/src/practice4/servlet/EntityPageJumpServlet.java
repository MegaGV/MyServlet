package practice4.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EntityPageJumpServlet", urlPatterns = "/EntityPageJumpServlet")
public class EntityPageJumpServlet extends HttpServlet {
    public EntityPageJumpServlet(){ super(); }
    public void init() throws ServletException { super.init(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int input_page = Integer.valueOf(request.getParameter("input_pages"));
        request.getRequestDispatcher("EntityListServlet?page=" + input_page + "&class=entity.Teacher").forward(request, response);
    }

    public void destroy() { super.destroy(); }
}
