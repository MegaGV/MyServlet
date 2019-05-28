package practice3.servlet;

import practice3.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public LoginServlet(){ super(); }

    public void init() throws ServletException
    {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginService loginService = new LoginService();

        if(loginService.login(username, password)) {
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("TeacherListServlet?page=1").forward(request, response);
        }
        else {
            request.getRequestDispatcher("login_wrong.jsp").forward(request, response);
        }

    }

    public void destroy()
    {
        super.destroy();
    }
}
