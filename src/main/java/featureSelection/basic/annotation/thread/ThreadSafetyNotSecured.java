package featureSelection.basic.annotation.thread;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Implemented without paying attention to whether it is thread-safe or not.
 * <p>
 * Meaning it is highly possible that it is <strong>unsafe in multi-thread</strong>
 * executions.
 *
 * @author Benjamin_L
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafetyNotSecured {}