package practice3.servlet;

import practice3.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeacherAddPreServlet", urlPatterns = "/TeacherAddPreServlet")
public class TeacherAddPreServlet extends HttpServlet {
    public TeacherAddPreServlet(){
        super();
    }

    public void init() throws ServletException
    {
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService service = new TeacherService();

        List<String> ids = service.getAllID();

        request.setAttribute("ids", ids);
        request.getRequestDispatcher("TeacherAdd.jsp").forward(request,response);
    }

    public void destroy() {
        super.destroy();
    }
}
