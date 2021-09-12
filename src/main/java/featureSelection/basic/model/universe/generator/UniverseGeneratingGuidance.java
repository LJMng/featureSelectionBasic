package featureSelection.basic.model.universe.generator;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

import featureSelection.basic.model.universe.instance.Instance;

/**
 * A model for {@link Instance} generating guidance. The key-value map of each column
 * is saved by <code>columnValueMap</code>. The last ID of generated {@link Instance}
 * is saved by <code>lastInstanceID</code>. By calling {@link Instance#setID(int)},
 * {@link Instance}'s id is set.
 *
 * @author Benjamin_L
 */
@Data
@NoArgsConstructor
public class UniverseGeneratingGuidance {
    private Map<String, Integer>[] columnValueMap;
    private int lastInstanceID;

    /**
     * Constructor.
     *
     * @param columnSize
     * 		The column number of {@link Instance}. (Condition attributes +
     * 		Decision attribute)
     */
    @SuppressWarnings("unchecked")
    public UniverseGeneratingGuidance(int columnSize) {
        columnValueMap = new Map[columnSize];
        for (int i=0; i<columnSize; i++)	columnValueMap[i] = new HashMap<>();
        lastInstanceID = 0;
    }

    /**
     * Set the column value map of <code>columnIndex<code>.
     *
     * @param columnIndex
     * 		The index of the column. (Starts from 0)
     * @param columnValueMap
     * 		The column value {@link Map}.
     */
    public void setColumnValueMapOf(int columnIndex, Map<String, Integer> columnValueMap) {
        this.columnValueMap[columnIndex] = columnValueMap;
    }

    /**
     * The number of the column of {@link Instance}.
     *
     * @return the number of the column.
     */
    public int getColumnSize() {
        return columnValueMap==null? 0: columnValueMap.length;
    }

    /**
     * Transfer and return current {@link UniverseGeneratingGuidance} into
     * {@link UniverseGeneratingGuidanceTemplate}.
     *
     * @see UniverseGeneratingGuidanceTemplate
     *
     * @return transfered {@link UniverseGeneratingGuidanceTemplate} instance.
     */
    public UniverseGeneratingGuidanceTemplate toUniverseGeneratingGuidanceTemplate() {
        return new UniverseGeneratingGuidanceTemplate(this);
    }
}
