package featureSelection.basic.procedure.report;

import java.util.List;

/**
 * An interface for {@link ReportGenerated} whose items are collected in a {@link List}.
 * 
 * @author Benjamin_L
 * 
 * @see List
 * @see ReportMapGenerated
 *
 * @param <Item>
 * 		Type of report item in {@link List}.
 */
public interface ReportListGenerated<Item> extends ReportGenerated<List<Item>> {

}
