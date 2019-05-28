package servlet;

import factory.Factory;
import practice1.service.TeacherServiceImp;
import service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeacherSortServlet", urlPatterns = "/TeacherSortServlet")
public class TeacherSortServlet extends HttpServlet {
    public TeacherSortServlet(){
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
        String sort = request.getParameter("sort");

        TeacherService service = Factory.getInstance().getServiceFactory().getTeacherService();
        service.sort(sort);

        request.getRequestDispatcher("TeacherListServlet?page=1").forward(request, response);
    }
}
