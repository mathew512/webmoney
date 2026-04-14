package app;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    name = "WelcomePage",
    urlPatterns = {"/welcome"}
)
public class WelcomePage extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(false);
        String loggedInUser = (session != null) ? (String) session.getAttribute("loggedInUser") : null;

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Welcome - MoneyWeb</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2980b9; color: white; padding: 20px; text-align: center; }");
        writer.println("nav { margin-top: 20px; text-align: center; }");
        writer.println("nav a { margin: 0 15px; color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("nav a:hover { text-decoration: underline; }");
        writer.println("section { margin-top: 30px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
        writer.println("</style>");
        writer.println("</head><body>");

        if (loggedInUser != null) {
            writer.println("<header><h1>Welcome back, " + loggedInUser + "!</h1></header>");
        } else {
            writer.println("<header><h1>Welcome to MoneyWeb</h1></header>");
        }

        writer.println("<nav>");
        writer.println("<a href='about'>About Us</a>");
        writer.println("<a href='register'>Register</a>");
        writer.println("<a href='transaction'>Transactions</a>");
        writer.println("</nav>");

        writer.println("<section>");
        writer.println("<h2>Your Financial Portal</h2>");
        writer.println("<p>MoneyWeb is your gateway to understanding how online money platforms work. "
                + "From account registration to transaction simulation, our portal gives you a hands-on experience "
                + "with digital finance in a safe environment.</p>");
        writer.println("</section>");

        writer.println("<section>");
        writer.println("<h2>Get Started</h2>");
        writer.println("<p>Create your account today and explore the dashboard to manage your balance, "
                + "simulate deposits and withdrawals, and learn how financial systems operate online.</p>");
        writer.println("<p><a href='register'>Register Now &raquo;</a></p>");
        writer.println("</section>");

        writer.println("<nav>");
        writer.println("<a href='about'>About Us</a>");
        writer.println("<a href='register'>Register</a>");
        writer.println("<a href='transaction'>Transactions</a>");
        writer.println("<a href='logout' style='color:#c0392b;'>Logout</a>");
        writer.println("</nav>");

        writer.println("</body></html>");
    }
}
