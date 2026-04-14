package app;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    name = "LoginPage",
    urlPatterns = {"/login"},
    initParams = {
        @WebInitParam(name = "username", value = "admin"),
        @WebInitParam(name = "password", value = "1234")
    }
)
public class LoginPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Login - MoneyWeb</title>");
        out.println("<style>");
        out.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        out.println("header { background-color: #3498db; color: white; padding: 15px; text-align: center; }");
        out.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
        out.println("input[type=text], input[type=password] { padding: 8px; margin: 10px; width: 250px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type=submit] { background-color: #3498db; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }");
        out.println("input[type=submit]:hover { background-color: #217dbb; }");
        out.println("a { color: #27ae60; text-decoration: none; font-weight: bold; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header><h1>Login to MoneyWeb</h1></header>");
        out.println("<section>");
        out.println("<form action='login' method='post'>");
        out.println("<input type='text' name='username' placeholder='Enter Username' required><br>");
        out.println("<input type='password' name='password' placeholder='Enter Password' required><br>");
        out.println("<input type='submit' value='Login'>");
        out.println("</form>");
        out.println("</section>");
        out.println("<section>");
        out.println("<a href='welcome'>&larr; Back to Welcome</a>");
        out.println("</section>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletConfig config = getServletConfig();
        ServletContext context = getServletContext();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null) username = "";
        if (password == null) password = "";

        String usernameConfig = config.getInitParameter("username");
        String passwordConfig = config.getInitParameter("password");

        boolean allowed = username.equalsIgnoreCase(usernameConfig)
                && password.equalsIgnoreCase(passwordConfig);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (allowed) {
            HttpSession session = req.getSession(true);
            session.setAttribute("loggedInUser", username);
            session.setAttribute("SESSION_ID", session.getId());

            resp.sendRedirect("./register");
        } else {
            req.getSession().invalidate();
            out.println("<h2>Access denied. Invalid credentials.</h2>");
            out.println("<a href='login'>Try Again</a>");
        }
    }
}
