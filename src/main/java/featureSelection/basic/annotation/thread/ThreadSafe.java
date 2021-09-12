package featureSelection.basic.annotation.thread;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Implemented with policies ensuring <strong>secured thread safety</strong>.
 *
 * @author Benjamin_L
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {}
