package app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field-level annotation used to mark a class field
 * as a form input in the WebMoney Framework.
 *
 * Attributes:
 * - label: The text displayed next to the input field.
 * - name: The HTML name attribute (defaults to the field name if empty).
 * - placeholder: The placeholder text inside the input field.
 * - required: Whether the field is mandatory (default true).
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebMoneyFormField {
    String label();
    String name() default "";
    String placeholder() default "";
    boolean required() default true;
}
