package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AboutUsPage extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>About Us - MoneyWeb</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2c3e50; color: white; padding: 15px; }");
        writer.println("section { margin-top: 20px; padding: 15px; background: white; border-radius: 5px; }");
        writer.println("img { float: right; margin-left: 20px; max-width: 250px; border-radius: 8px; }");
        writer.println("a { color: #34980b; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head><body>");

        // Header
        writer.println("<header><h1>About MoneyWeb</h1></header>");

        // Who we are with image
        // writer.println("<section>");
        // writer.println("<img src='https://drive.google.com/uc?export=view&id=YOUR_FILE_ID' alt='MoneyWeb illustration'/>");
        // writer.println("<h2>Who We Are</h2>");
        // writer.println("<p>MoneyWeb is a financial management portal designed to help users simulate and understand how digital money platforms operate. "
        //             + "It provides a simple environment to register accounts, track balances, and perform transactions in a way that mirrors real-world online banking systems.</p>");
        // writer.println("</section>");
                

        // Mission
        writer.println("<section>");
        writer.println("<h2>Our Mission</h2>");
        writer.println("<p>We aim to make financial literacy accessible by showing how deposits, withdrawals, and transfers work in a secure, web-based environment. "
                     + "MoneyWeb demonstrates the flow of funds, account creation, and transaction history so users can grasp the essentials of money management online.</p>");
        writer.println("</section>");

        // Features
        writer.println("<section>");
        writer.println("<h2>Features</h2>");
        writer.println("<ul>");
        writer.println("<li>Account registration with clear forms</li>");
        writer.println("<li>Transaction simulation (deposits, withdrawals, transfers)</li>");
        writer.println("<li>Dashboard with balance overview</li>");
        writer.println("<li>Secure session handling for each user</li>");
        writer.println("</ul>");
        writer.println("</section>");

        // Registration alignment
        writer.println("<section style='text-align:center;'>");
        writer.println("<h2>Get Started</h2>");
        writer.println("<p>Ready to experience MoneyWeb? Head over to the registration page and create your account. "
                     + "Once registered, you’ll be able to log in, view your dashboard, and start simulating transactions.</p>");
        writer.println("<p><a href='register'>Go to Registration &raquo;</a></p>");
        writer.println("</section>");

        // Navigation
        writer.println("<section>");
        writer.println("<a href='welcome'>&larr; Back to Welcome</a>");
        writer.println("</section>");

        writer.println("</body></html>");
    }
}
