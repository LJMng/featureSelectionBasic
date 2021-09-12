package featureSelection.basic.procedure.report;

/**
 * Generate report.
 * 
 * @author Benjamin_L
 * 
 * @see ReportListGenerated
 * @see ReportMapGenerated
 *
 * @param <Report>
 * 		Type of report.
 */
public interface ReportGenerated<Report> {
	/**
	 * Get the name of the report.
	 * 
	 * @return The name in {@link String}.
	 */
	String reportName();
	/**
	 * Get the report.
	 * 
	 * @return {@link Report}.
	 */
	Report getReport();
}