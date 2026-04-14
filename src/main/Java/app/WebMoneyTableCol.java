package app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field-level annotation used to mark a class field
 * as a table column in the WebMoney Framework.
 *
 * Attributes:
 * - label: The column header text displayed in the table.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebMoneyTableCol {
    String label();
}
