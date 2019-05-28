package servlet;

import entity.Teacher;
import factory.Factory;
import service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeacherEditServlet", urlPatterns = "/TeacherEditServlet")
public class TeacherEditServlet extends HttpServlet {
    public TeacherEditServlet(){
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
        String id = request.getParameter("id");

        TeacherService service = Factory.getInstance().getServiceFactory().getTeacherService();
        Teacher teacher = service.findTeacherById(id);

        request.setAttribute("teacher", teacher);

        request.getRequestDispatcher("TeacherEdit.jsp").forward(request,response);

    }

    public void destroy()
    {
        super.destroy();
    }
}
