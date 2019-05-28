package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import entity.Teacher;
import practice1.service.TeacherServiceImp;

@WebServlet(name = "TeacherListServlet", urlPatterns = "/TeacherListServlet")
public class TeacherListServlet extends HttpServlet {
    public TeacherListServlet(){
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
        String username = (String) request.getSession().getAttribute("username");
        if(username == null)
            request.getRequestDispatcher("login.jsp").forward(request, response);

        TeacherServiceImp service = new TeacherServiceImp();
        List<Teacher> teachers = service.getTeachers();

        int page = Integer.valueOf(request.getParameter("page"));
        int num_every_page = 10;
        int last_page = (int) Math.ceil((double)teachers.size() / num_every_page);
        if(teachers.isEmpty())
            last_page = 1;

        request.setAttribute("teachers", teachers);
        request.setAttribute("page", page);
        request.setAttribute("num_every_page", num_every_page);
        request.setAttribute("last_page", last_page);

        request.getRequestDispatcher("TeacherList.jsp").forward(request, response);
    }

    public void destroy()
    {
        super.destroy();
    }
}
