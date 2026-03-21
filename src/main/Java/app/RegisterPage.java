package app;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Display the registration form when accessed via GET
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

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

        // Registration form (POST method now)
        writer.println("<section>");
        writer.println("<h2>Create Your Account</h2>");
        writer.println("<form method='POST' action='register'>");
        writer.println("<input type='text' name='username' placeholder='Enter Username' required><br>");
        writer.println("<input type='email' name='email' placeholder='Enter Email' required><br>");
        writer.println("<input type='submit' value='Register'>");
        writer.println("</form>");
        writer.println("</section>");

        writer.println("<section>");
        writer.println("<a href='welcome'>&larr; Back to Welcome</a>");
        writer.println("</section>");

        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle form submission when accessed via POST
        HttpSession session = req.getSession(true);

        String username = req.getParameter("username");
        String email = req.getParameter("email");

        // Save user data in session
        session.setAttribute("username", username);
        session.setAttribute("balance", 0.0); // initialize balance

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Register - MoneyWeb</title></head><body>");
        writer.println("<header><h1>Register for MoneyWeb</h1></header>");

        // Confirmation message
        writer.println("<section>");
        writer.println("<h2>Registration Successful</h2>");
        writer.println("<p>Welcome, <strong>" + username + "</strong>! Your account has been created with email <strong>" + email + "</strong>.</p>");
        writer.println("<p>Your starting balance is <strong>$0.00</strong>.</p>");
        writer.println("<p><a href='transaction'>Go to Transactions &raquo;</a></p>");
        writer.println("</section>");

        writer.println("<section>");
        writer.println("<a href='welcome'>&larr; Back to Welcome</a>");
        writer.println("</section>");

        writer.println("</body></html>");
    }
}