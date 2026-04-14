package app;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);

        String loginUri = httpRequest.getContextPath() + "/login";
        String welcomeUri = httpRequest.getContextPath() + "/welcome";
        String pageRequestUri = httpRequest.getRequestURI();

        boolean loggedIn = (session != null && session.getAttribute("SESSION_ID") != null);

        if (loggedIn || pageRequestUri.equalsIgnoreCase(loginUri)
                || pageRequestUri.equalsIgnoreCase(welcomeUri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (session != null) {
                session.invalidate();
            }
            httpResponse.sendRedirect(loginUri);
        }
    }
}
