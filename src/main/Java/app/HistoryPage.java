package app;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HistoryPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession(false);
        @SuppressWarnings("unchecked")
        List<User> users = (session != null) ? (List<User>) session.getAttribute("users") : null;

        writer.println("<!DOCTYPE html><html><head><title>Transaction History</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2980b9; color: white; padding: 20px; text-align: center; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; }");
        writer.println("h2 { color: #2980b9; }");
        writer.println("ul { list-style-type: none; padding: 0; }");
        writer.println("li { margin: 5px 0; padding: 8px; background: #ecf0f1; border-radius: 4px; }");
        writer.println("</style></head><body>");

        writer.println("<header><h1>Transaction History</h1></header>");

        if (users != null && !users.isEmpty()) {
            for (User u : users) {
                writer.println("<section>");
                writer.println("<h2>" + u.getUsername() + " (" + u.getEmail() + ")</h2>");
                if (!u.getHistory().isEmpty()) {
                    writer.println("<ul>");
                    for (String record : u.getHistory()) {
                        writer.println("<li>" + record + "</li>");
                    }
                    writer.println("</ul>");
                } else {
                    writer.println("<p>No transactions yet.</p>");
                }
                writer.println("</section>");
            }
        } else {
            writer.println("<section><h2>No clients registered yet.</h2></section>");
        }

        writer.println("<section>");
        writer.println("<a href='transaction'>Back to Transactions</a> | ");
        writer.println("<a href='logout' style='color:#c0392b; font-weight:bold;'>Logout</a>");
        writer.println("</section>");

        writer.println("</body></html>");
    }
}
