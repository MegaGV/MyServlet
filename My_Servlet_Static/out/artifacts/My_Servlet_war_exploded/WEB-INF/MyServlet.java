import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;


public class MyServlet extends HttpServlet{
    private String message;

    public void init() throws ServletException
    {
        super.init();
        message = "hello!world";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html");
        // 实际处理
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "<h1>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }

    public void destroy()
    {
        // 什么也不做
    }
}
