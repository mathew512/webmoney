package app;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AboutUsPage implements Servlet {
    // Holds servlet configuration info (like init parameters)
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Called once when the servlet is first created
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        // Returns the servlet configuration object
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        // Called for every request. Here we generate the HTML response.
        resp.setContentType("text/html"); // Tell browser this is HTML
        PrintWriter writer = resp.getWriter(); // Writer to send output

        // Build the HTML page
        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>About Us - MoneyWeb</title></head><body>");
        writer.println("<h1>About MoneyWeb</h1>");
        writer.println("<p>This servlet demonstrates the raw Servlet interface implementation.</p>");

        // Mission section
        writer.println("<h2>Our Mission</h2>");
        writer.println("<p>We aim to make financial literacy accessible by showing how deposits, withdrawals, "
                     + "and transfers work in a secure, web-based environment.</p>");

        // Features section
        writer.println("<h2>Features</h2>");
        writer.println("<ul>");
        writer.println("<li>Account registration with clear forms</li>");
        writer.println("<li>Transaction simulation (deposits, withdrawals, transfers)</li>");
        writer.println("<li>Dashboard with balance overview</li>");
        writer.println("<li>Secure session handling for each user</li>");
        writer.println("</ul>");

        // Navigation links
        writer.println("<p><a href='register'>Go to Registration &raquo;</a></p>");
        writer.println("<p><a href='welcome'>&larr; Back to Welcome</a></p>");

        writer.println("</body></html>");
    }

    @Override
    public String getServletInfo() {
        // Returns a short description of this servlet
        return "AboutUsPage implemented via Servlet interface";
    }

    @Override
    public void destroy() {
        // Called once when the servlet is being taken out of service
        // Cleanup logic can go here if needed
    }
}
