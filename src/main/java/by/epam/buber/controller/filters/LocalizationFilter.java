package by.epam.buber.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebFilter(urlPatterns = { "/*" })
public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        if (session.getAttribute("language") == null)  {
            ResourceBundle resource = ResourceBundle.getBundle("configuration");
            String language = resource.getString("init.language");
            session.setAttribute("language", language);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
