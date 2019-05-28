package servlet;

import entity.Teacher;
import practice1.service.TeacherServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeacherCheckServlet", urlPatterns = "/TeacherCheckServlet")
public class TeacherCheckServlet extends HttpServlet {
    public TeacherCheckServlet(){
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

        TeacherServiceImp service = new TeacherServiceImp();
        Teacher teacher = service.findTeacherById(id);

        request.setAttribute("teacher", teacher);

        request.getRequestDispatcher("TeacherCheck.jsp").forward(request,response);
    }

    public void destroy()
    {
        super.destroy();
    }
}