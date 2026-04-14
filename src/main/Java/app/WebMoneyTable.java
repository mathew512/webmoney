package app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class-level annotation used to mark a class
 * as capable of generating a table in the WebMoney Framework.
 *
 * Attributes:
 * - label: The title displayed above the table.
 * - tableUrl: The servlet URL that will render the table view.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebMoneyTable {
    String label();
    String tableUrl();
}
