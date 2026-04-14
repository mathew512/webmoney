package app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class-level annotation used to mark a class
 * as capable of generating a form in the WebMoney Framework.
 * 
 * Attributes:
 * - label: The title displayed above the form.
 * - method: The HTTP method (default is POST).
 * - actionUrl: The servlet URL that will handle the form submission.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebMoneyForm {
    String label() default "Register";
    String method() default "POST";
    String actionUrl();
}
