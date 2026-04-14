package app;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class UserAttributeListener implements HttpSessionAttributeListener {

    private void logToFile(String prefix, String message) {
        try (FileWriter fw = new FileWriter("moneyweb_audit.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " " + prefix + " " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logToFile("[ATTRIBUTE]", "Added: " + event.getName() + " = " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logToFile("[ATTRIBUTE]", "Removed: " + event.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logToFile("[ATTRIBUTE]", "Replaced: " + event.getName() + " = " + event.getValue());
    }
}
