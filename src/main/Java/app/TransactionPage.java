package app;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TransactionPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession(false); // don't create new session if none
        @SuppressWarnings("unchecked")
        List<User> users = (session != null) ? (List<User>) session.getAttribute("users") : null;

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Transactions - MoneyWeb</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #8e44ad; color: white; padding: 20px; text-align: center; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; text-align: center; }");
        writer.println("table { margin: 20px auto; border-collapse: collapse; width: 80%; }");
        writer.println("th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
        writer.println("th { background-color: #8e44ad; color: white; }");
        writer.println("input[type=number] { padding: 8px; margin: 10px; width: 200px; border: 1px solid #ccc; border-radius: 4px; }");
        writer.println("input[type=submit] { background-color: #8e44ad; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }");
        writer.println("input[type=submit]:hover { background-color: #732d91; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head><body>");

        // Header
        writer.println("<header><h1>Transaction Dashboard</h1></header>");

        if (users != null && !users.isEmpty()) {
            // Show all accounts in a table
            writer.println("<section><h2>All Accounts</h2>");
            writer.println("<table>");
            writer.println("<tr><th>Username</th><th>Email</th><th>Status</th><th>Balance</th></tr>");
            for (User u : users) {
                writer.println("<tr>");
                writer.println("<td>" + u.getUsername() + "</td>");
                writer.println("<td>" + u.getEmail() + "</td>");
                writer.println("<td>" + u.getRegistrationStatus() + "</td>");
                writer.println("<td>Ksh " + String.format("%.2f", u.getBalance()) + "</td>");
                writer.println("</tr>");
            }
            writer.println("</table></section>");

            // Transaction form with dropdown
            writer.println("<section><h2>Make a Transaction</h2>");
            writer.println("<form method='POST' action='transaction'>");
            writer.println("<select name='selectedUser'>");
            for (User u : users) {
                writer.println("<option value='" + u.getUsername() + "'>" + u.getUsername() + "</option>");
            }
            writer.println("</select><br>");
            writer.println("<input type='number' step='0.01' name='amount' placeholder='Enter amount' required><br>");
            writer.println("<input type='submit' name='action' value='Deposit'>");
            writer.println("<input type='submit' name='action' value='Withdraw'>");
            writer.println("</form></section>");
        } else {
            writer.println("<section><h2>No users registered. Please <a href='register'>register</a>.</h2></section>");
        }

        // Navigation (updated)
        writer.println("<section>");
        writer.println("<a href='register'>Register Another Client</a> | ");
        writer.println("<a href='history'>View Transaction History</a> | ");
        writer.println("<a href='logout' style='color:#c0392b; font-weight:bold;'>Logout</a>");
        writer.println("</section>");

        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login");
            return;
        }

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) session.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
            session.setAttribute("users", users);
        }

        String selectedUser = req.getParameter("selectedUser");
        String action = req.getParameter("action");
        String amountStr = req.getParameter("amount");

        try {
            double amount = Double.parseDouble(amountStr);
            for (User u : users) {
                if (u.getUsername().equals(selectedUser)) {
                    // Updated transaction logic with history
                    if ("Deposit".equalsIgnoreCase(action)) {
                        u.setBalance(u.getBalance() + amount);
                        u.addTransaction("Deposited", amount);
                    } else if ("Withdraw".equalsIgnoreCase(action)) {
                        u.setBalance(u.getBalance() - amount);
                        u.addTransaction("Withdrew", amount);
                    }
                }
            }
            session.setAttribute("users", users);
        } catch (NumberFormatException e) {
            // ignore invalid input
        }

        resp.sendRedirect("transaction");
    }
}
