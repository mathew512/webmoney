package app;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AppContextAuditListener implements ServletContextListener {

    private void logToFile(String prefix, String message) {
        String logEntry = LocalDateTime.now() + " " + prefix + " " + message;

        try (FileWriter fw = new FileWriter("moneyweb_audit.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " " + prefix + " " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(logEntry);

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logToFile("[APP]", "MoneyWeb Application started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logToFile("[APP]", "MoneyWeb Application stopped.");
    }
}
