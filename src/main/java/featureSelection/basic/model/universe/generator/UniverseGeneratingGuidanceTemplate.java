package featureSelection.basic.model.universe.generator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * A template for {@link UniverseGeneratingGuidance}.
 * <P>
 * This class is for saving {@link UniverseGeneratingGuidance} data into a json file,
 * data is tidied in this class.
 *
 * @see UniverseGeneratingGuidance
 *
 * @author Benjamin_L
 */
@Data
@NoArgsConstructor
public class UniverseGeneratingGuidanceTemplate {
    private String[][] columnValues;
    private int lastUniverseID;

    public UniverseGeneratingGuidanceTemplate(UniverseGeneratingGuidance guidance) {
        this.lastUniverseID = guidance.getLastInstanceID();
        loadColumnValuesByMaps(guidance.getColumnValueMap());
    }

    /**
     * Load Column value {@link Map} into <code>columnValues</code>
     *
     * @param map
     * 		The column value {@link Map}.
     */
    private void loadColumnValuesByMaps(Map<String, Integer>[] map) {
        columnValues = new String[map.length][];
        for (int c=0; c<map.length; c++) {
            columnValues[c] = new String[map[c].size()];
            for (Map.Entry<String, Integer> entry: map[c].entrySet()) {
                columnValues[c][entry.getValue()] = entry.getKey();
            }
        }
    }

    /**
     * Transfer <code>this</code> instance into {@link UniverseGeneratingGuidance}.
     *
     * @return transfered {@link UniverseGeneratingGuidance}.
     */
    public UniverseGeneratingGuidance toUniverseGeneratingGuidance() {
        UniverseGeneratingGuidance guidance = new UniverseGeneratingGuidance();
        guidance.setLastInstanceID(lastUniverseID);
        guidance.setColumnValueMap(restoreColumnMaps());
        return guidance;
    }

    /**
     * Restore column value {@link Map}s by <code>columnValues</code>
     *
     * @return column value {@link Map} array.
     */
    private Map<String, Integer>[] restoreColumnMaps() {
        @SuppressWarnings("unchecked")
        Map<String, Integer>[] map = new Map[columnValues.length];
        for (int c=0; c<columnValues.length; c++) {
            map[c] = new HashMap<>();
            for (int i=0; i<columnValues[c].length; i++) {
                map[c].put(columnValues[c][i], i);
            }
        }
        return map;
    }
}
