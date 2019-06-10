package servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Factory;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public LoginFilter() {

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        Factory.getInstance().init();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getRequestURI().endsWith("LoginServlet") ||  req.getRequestURI().endsWith("login.jsp")) {
            chain.doFilter(request, response);
            return;
        }
        String username = (String) req.getSession().getAttribute("username");
        if(username == null) {
            HttpServletResponse res = (HttpServletResponse)response;
            res.sendRedirect("login.jsp");
        }
        else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }

}

