package app;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Register", urlPatterns = {"/register"})
public class RegisterPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession(false);
        String loggedInUser = (session != null) ? (String) session.getAttribute("loggedInUser") : null;

        writer.println("<!DOCTYPE html><html><head><title>Register - MoneyWeb</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #27ae60; color: white; padding: 15px; text-align: center; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
        writer.println("h2 { color: #27ae60; }");
        writer.println("p { font-size: 16px; margin: 10px 0; }");
        writer.println("a.button { display: inline-block; margin: 10px; padding: 10px 20px; background-color: #3498db; color: white; text-decoration: none; border-radius: 4px; font-weight: bold; }");
        writer.println("a.button:hover { background-color: #217dbb; }");
        writer.println("table { margin: 20px auto; border-collapse: collapse; width: 80%; }");
        writer.println("th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
        writer.println("th { background-color: #27ae60; color: white; }");
        writer.println("</style>");
        writer.println("</head><body>");
        writer.println("<header><h1>Register for MoneyWeb</h1></header>");
        

        writer.println("<section>");
        if (loggedInUser != null) {
            // Framework generates the form dynamically from User annotations
            WebMoneyFramework.htmlForm(writer, User.class);
        } else {
            writer.println("<h2>You must login first before registering.</h2>");
            writer.println("<a href='login'>Go to Login Page</a>");
        }
        writer.println("</section>");

        writer.println("<section><a href='welcome'>&larr; Back to Welcome</a></section>");
        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String loggedInUser = (session != null) ? (String) session.getAttribute("loggedInUser") : null;

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        if (loggedInUser != null) {
            String username = req.getParameter("username");
            String email = req.getParameter("email");

            @SuppressWarnings("unchecked")
            List<User> users = (List<User>) session.getAttribute("users");
            if (users == null) users = new ArrayList<>();

            User newUser = new User(username, email, 0.0, "Completed");
            users.add(newUser);
            session.setAttribute("users", users);

            writer.println("<!DOCTYPE html><html><head><title>Register - MoneyWeb</title>");
            writer.println("<style>");
            writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
            writer.println("header { background-color: #27ae60; color: white; padding: 15px; text-align: center; }");
            writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
            writer.println("h2 { color: #27ae60; }");
            writer.println("p { font-size: 16px; margin: 10px 0; }");
            writer.println("a.button { display: inline-block; margin: 10px; padding: 10px 20px; background-color: #3498db; color: white; text-decoration: none; border-radius: 4px; font-weight: bold; }");
            writer.println("a.button:hover { background-color: #217dbb; }");
            writer.println("table { margin: 20px auto; border-collapse: collapse; width: 80%; }");
            writer.println("th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
            writer.println("th { background-color: #27ae60; color: white; }");
            writer.println("</style>");
            writer.println("</head><body>");
            writer.println("<header><h1>Register for MoneyWeb</h1></header>");
            
            
            writer.println("<section>");
            writer.println("<h2>Registration Successful</h2>");
            writer.println("<p>Welcome, <strong>" + username + "</strong>! Your account has been created with email <strong>" + email + "</strong>.</p>");
            writer.println("<p>Your starting balance is <strong>Ksh 0.00</strong>.</p>");
            writer.println("<a href='transaction'>Go to Transactions &raquo;</a>");
            writer.println("<a href='register'>Register Another Client</a>");
            writer.println("<a href='logout' style='color:#c0392b;'>Logout</a>");
            writer.println("</section>");

            // Framework generates the table dynamically from User annotations
            WebMoneyFramework.htmlTable(writer, User.class, users);

            writer.println("</body></html>");
        } else {
            writer.println("<h2>You must login first before registering.</h2>");
            writer.println("<a href='login'>Go to Login Page</a>");
        }
    }
}
