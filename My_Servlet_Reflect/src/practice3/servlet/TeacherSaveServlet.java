package practice3.servlet;

import practice3.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "TeacherSaveServlet", urlPatterns = "/TeacherSaveServlet")
public class TeacherSaveServlet extends HttpServlet {
    public TeacherSaveServlet(){
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
        service.saveTeacher(request.getParameter("id"),
                new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                new String(request.getParameter("college").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                new String(request.getParameter("major").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                request.getParameter("birthday"),
                request.getParameter("salary"));

        request.getRequestDispatcher("TeacherListServlet?page=1").forward(request, response);

    }

    public void destroy()
    {
        super.destroy();
    }
}
