package featureSelection.basic.procedure.timer;

import java.util.Map;

/**
 * Time sum mark. With this interface, execution time will be sum up and get by
 * {@link #getTime()}.
 * <p>
 * Also, time details group by tag can be get by {@link #getTimeDetailByTags()}
 * 
 * @author Benjamin_L
 */
public interface TimeSum {
	/**
	 * Get the time sum.
	 * 
	 * @return time in nano.
	 */
	long getTime();
	/**
	 * Get the time sum detail group by tags.
	 * 
	 * @return {@link Map} with tags as keys and times in nano as values.
	 */
	Map<String, Long> getTimeDetailByTags();
}
