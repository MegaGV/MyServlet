package practice1.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageJumpServlet", urlPatterns = "/PageJumpServlet")
public class PageJumpServlet extends HttpServlet {
    public PageJumpServlet(){ super(); }

    public void init() throws ServletException
    {
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int input_page = Integer.valueOf(request.getParameter("input_pages"));
        request.getRequestDispatcher("TeacherListServlet?page=" + input_page).forward(request, response);
    }

    public void destroy() {
        super.destroy();
    }
}
