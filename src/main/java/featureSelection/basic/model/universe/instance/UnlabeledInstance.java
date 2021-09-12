package featureSelection.basic.model.universe.instance;

/**
 * A model for a general Universe instance, represents a line of data when in a Decision
 * Table.
 * <p>
 * Different from {@link Instance}, data of this entity doesn't contain decision value(
 * label).
 *
 * @see Instance
 *
 * @author Benjamin_L
 */
public class UnlabeledInstance extends Instance {
    public UnlabeledInstance(int[] value) {
        super(value);
    }

    /**
     * Get the attribute value by index.
     * <p>
     * <strong>NOTICE</strong>: Unlabeled Universe contains attribute values only, so
     * different from {@link Instance}, all values returned in this method are attribute
     * values.
     * <p>
     * Due to no decision value(label) in this Universe, index starts from 1(without the
     * label).
     *
     * @param index
     * 		The index of the value. (starts from 1, no decision value)
     * @return the int value./-1 if the index is illegal.
     */
    @Override
    public int getAttributeValue(int index){
        try {	return getAttributeValues()[index-1];	}catch(IndexOutOfBoundsException e) {	return -1;	}
    }

    /**
     * Get the condition attribute values.
     * <p>
     * <strong>NOTICE</strong>: Unlabeled Universe contains attribute values only, so different from
     * {@link Instance}, this method returns {@link #getAttributeValues()}.
     *
     * @return The condition attribute values.
     */
    @Override
    public int[] getConditionAttributeValues() {
        return this.getAttributeValues();
    }
}