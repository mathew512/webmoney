package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterPage extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(true);

        String username = req.getParameter("username");
        String email = req.getParameter("email");

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Register - MoneyWeb</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #27ae60; color: white; padding: 15px; text-align: center; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
        writer.println("input[type=text], input[type=email] { padding: 8px; margin: 10px; width: 250px; border: 1px solid #ccc; border-radius: 4px; }");
        writer.println("input[type=submit] { background-color: #27ae60; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }");
        writer.println("input[type=submit]:hover { background-color: #219150; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head><body>");

        writer.println("<header><h1>Register for MoneyWeb</h1></header>");

        if (username != null && email != null) {
            // Save username in session
            session.setAttribute("username", username);
            session.setAttribute("balance", 0.0); // initialize balance

            writer.println("<section>");
            writer.println("<h2>Registration Successful</h2>");
            writer.println("<p>Welcome, <strong>" + username + "</strong>! Your account has been created with email <strong>" + email + "</strong>.</p>");
            writer.println("<p>Your starting balance is <strong>$0.00</strong>.</p>");
            writer.println("<p><a href='transaction'>Go to Transactions &raquo;</a></p>");
            writer.println("</section>");
        } else {
            writer.println("<section>");
            writer.println("<h2>Create Your Account</h2>");
            writer.println("<form method='GET' action='register'>");
            writer.println("<input type='text' name='username' placeholder='Enter Username' required><br>");
            writer.println("<input type='email' name='email' placeholder='Enter Email' required><br>");
            writer.println("<input type='submit' value='Register'>");
            writer.println("</form>");
            writer.println("</section>");
        }

        writer.println("<section>");
        writer.println("<a href='welcome'>&larr; Back to Welcome</a>");
        writer.println("</section>");

        writer.println("</body></html>");
    }
}
