package featureSelection.basic.procedure.statistics;

import featureSelection.basic.procedure.component.TimeCountedProcedureComponent;
import org.slf4j.Logger;

/**
 * An interface for real time progress updating in {@link TimeCountedProcedureComponent}.
 * <p>
 * In implementation, one should maintain the field <code>progress</code> along with its
 * setter & getter method, and implement the notification mechanism to notify
 * <code>progress updatee</code> as <code>progress</code> updates.
 * 
 * @author Benjamin_L
 */
public interface RealtimeProgressUpdating {
	/**
	 * Get the progress. (0~1)
	 * 
	 * @return A {@link double} value as progress.
	 */
	double getProgress();
	/**
	 * Set the progress.
	 * 
	 * @param progress
	 * 		The progress value to be set. (0~1)
	 */
	void setProgress(double progress);
	
	/**
	 * Notify the updatee. This method is called as <code>progress</code> updates.
	 * 
	 * @param log
	 * 		{@link Logger} instance for logging if needed.
	 */
	void notifyUpdatee(Logger log);
	/**
	 * Set the updatee.
	 * 
	 * @param object
	 * 		The updatee to be notified when <code>progress</code> updates.
	 * @return <code>this</code>.
	 */
	RealtimeProgressUpdating setProgressUpdatee(Object object);
}