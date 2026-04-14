package app;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogViewer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>Audit Logs - MoneyWeb</title>");
        writer.println("<meta http-equiv='refresh' content='5'>"); // Auto-refresh every 5 seconds
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #34495e; color: white; padding: 15px; text-align: center; }");
        writer.println("pre { background: white; padding: 20px; border-radius: 5px; overflow-x: auto; max-height: 500px; }");
        writer.println("</style>");
        writer.println("</head><body>");
        writer.println("<header><h1>Audit Logs (auto-refresh every 5s)</h1></header>");

        writer.println("<pre>");
        try {
            Files.lines(Paths.get("moneyweb_audit.log")).forEach(writer::println);
        } catch (IOException e) {
            writer.println("Error reading log file: " + e.getMessage());
        }
        writer.println("</pre>");

        writer.println("<a href='welcome'>Back to Welcome</a>");
        writer.println("</body></html>");
    }
}
