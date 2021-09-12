package featureSelection.basic.annotation.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for marking a class of an entity of wrapping returned results.
 *
 * @author Benjamin_L
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface ReturnWrapper {}