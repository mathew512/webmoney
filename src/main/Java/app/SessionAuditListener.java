package app;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class SessionAuditListener implements HttpSessionListener {

    private void logToFile(String prefix, String message) {

        

        try (FileWriter fw = new FileWriter("moneyweb_audit.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " " + prefix + " " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logToFile("[SESSION]", "Created: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logToFile("[SESSION]", "Destroyed: " + se.getSession().getId());
    }
}
