package app;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class WebMoneyFramework {

    // Generate HTML form dynamically from annotations
    public static void htmlForm(PrintWriter writer, Class<?> clazz) {
        if (!clazz.isAnnotationPresent(WebMoneyForm.class)) return;

        WebMoneyForm formAnnot = clazz.getAnnotation(WebMoneyForm.class);

        writer.println("<h2>" + formAnnot.label() + "</h2>");
        writer.println("<form method='" + formAnnot.method() + "' action='" + formAnnot.actionUrl() + "'>");

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(WebMoneyFormField.class)) continue;

            WebMoneyFormField fieldInfo = field.getAnnotation(WebMoneyFormField.class);
            String name = fieldInfo.name().isEmpty() ? field.getName() : fieldInfo.name();

            writer.println("<label>" + fieldInfo.label() + ":</label>");
            writer.println("<input type='text' name='" + name + "' placeholder='" + fieldInfo.placeholder() + "' " +
                    (fieldInfo.required() ? "required" : "") + " />");
        }

        writer.println("<button type='submit'>Submit</button>");
        writer.println("</form>");
    }

    // Generate HTML table dynamically from annotations
    public static void htmlTable(PrintWriter writer, Class<?> clazz, List<?> tableData) {
        if (!clazz.isAnnotationPresent(WebMoneyTable.class)) return;

        WebMoneyTable tableAnnot = clazz.getAnnotation(WebMoneyTable.class);

        writer.println("<h2>" + tableAnnot.label() + "</h2>");
        writer.println("<table border='1' style='border-collapse: collapse; width: 80%;'>");

        List<Field> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(WebMoneyTableCol.class)) {
                fields.add(field);
            }
        }

        // Header row
        writer.println("<tr>");
        for (Field field : fields) {
            WebMoneyTableCol col = field.getAnnotation(WebMoneyTableCol.class);
            writer.println("<th style='padding: 8px; background-color: #27ae60; color: white;'>" + col.label() + "</th>");
        }
        writer.println("</tr>");

        // Data rows
        for (Object obj : tableData) {
            writer.println("<tr>");
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    writer.println("<td style='padding: 8px; text-align: center;'>" + value + "</td>");
                } catch (Exception e) {
                    writer.println("<td>Error</td>");
                }
            }
            writer.println("</tr>");
        }

        writer.println("</table>");
    }

    // Generate transaction history dynamically
    public static void htmlHistory(PrintWriter writer, User user) {
        writer.println("<section>");
        writer.println("<h2>Transaction History for " + user.getUsername() + "</h2>");

        List<String> history = user.getHistory();
        if (history == null || history.isEmpty()) {
            writer.println("<p>No transactions recorded yet.</p>");
        } else {
            writer.println("<ul style='list-style-type: none; padding: 0;'>");
            for (String record : history) {
                writer.println("<li style='margin: 8px; padding: 6px; border-bottom: 1px solid #ccc;'>"
                        + record + "</li>");
            }
            writer.println("</ul>");
        }

        writer.println("</section>");
    }
}
