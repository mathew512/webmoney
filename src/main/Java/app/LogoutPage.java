package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class LogoutPage extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(false); // get existing session, don’t create new

        if (session != null) {
            session.invalidate(); // clear all session data
        }

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Logout - MoneyWeb</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #c0392b; color: white; padding: 20px; text-align: center; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head><body>");

        writer.println("<header><h1>You Have Logged Out</h1></header>");
        writer.println("<section>");
        writer.println("<p>Your session has been cleared. Thank you for using MoneyWeb.</p>");
        writer.println("<p><a href='welcome'>Return to Home Page &raquo;</a></p>");
        writer.println("</section>");

        writer.println("</body></html>");
    }
}
