package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class TransactionPage extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(true);

        // Get username and balance from session
        String username = (String) session.getAttribute("username");
        Double balance = (Double) session.getAttribute("balance");
        if (balance == null) balance = 0.0;

        // Handle transaction
        String action = req.getParameter("action");
        String amountStr = req.getParameter("amount");

        if (action != null && amountStr != null) {
            try {
                double amount = Double.parseDouble(amountStr);
                if ("Deposit".equalsIgnoreCase(action)) {
                    balance += amount;
                } else if ("Withdraw".equalsIgnoreCase(action)) {
                    balance -= amount;
                }
                session.setAttribute("balance", balance);
            } catch (NumberFormatException e) {
                // ignore invalid input
            }
        }

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Transactions - MoneyWeb</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #8e44ad; color: white; padding: 20px; text-align: center; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
        writer.println("input[type=number] { padding: 8px; margin: 10px; width: 200px; border: 1px solid #ccc; border-radius: 4px; }");
        writer.println("input[type=submit] { background-color: #8e44ad; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }");
        writer.println("input[type=submit]:hover { background-color: #732d91; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head><body>");

        // Header
        writer.println("<header><h1>Transaction Dashboard</h1></header>");

        // Personalized greeting
        writer.println("<section>");
        if (username != null) {
            writer.println("<h2>Welcome, " + username + "!</h2>");
        } else {
            writer.println("<h2>Welcome to MoneyWeb!</h2>");
        }
        writer.println("<p>Your current balance is <strong>$" + String.format("%.2f", balance) + "</strong>.</p>");
        writer.println("</section>");

        // Transaction form
        writer.println("<section>");
        writer.println("<h2>Make a Transaction</h2>");
        writer.println("<form method='GET' action='transaction'>");
        writer.println("<input type='number' step='0.01' name='amount' placeholder='Enter amount' required><br>");
        writer.println("<input type='submit' name='action' value='Deposit'>");
        writer.println("<input type='submit' name='action' value='Withdraw'>");
        writer.println("</form>");
        writer.println("</section>");

        // Navigation
        writer.println("<section>");
        writer.println("<a href='welcome'>&larr; Back to Welcome</a>");
        writer.println("</section>");

        writer.println("<section>");
        writer.println("<a href='welcome'>&larr; Back to Welcome</a> | ");
        writer.println("<a href='logout' style='color:#c0392b; font-weight:bold;'>Logout</a>");
        writer.println("</section>");


        writer.println("</body></html>");
    }
}
