package featureSelection.basic.annotation.theory;

import java.lang.annotation.*;

/**
 * Rough Set Theory based.
 * <p>
 * Check out the article
 * <a href="https://www.sciencedirect.com/science/article/pii/S0020025506001484">
 * "Rudiments of rough sets"</a> and other articles or books about Rough Set Theory
 * for more info.
 *
 * @author Benjamin_L
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface RoughSet {}
