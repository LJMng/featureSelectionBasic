package featureSelection.basic.model.universe.instance;

import java.util.Arrays;

/**
 * {@link Instance} with missing data.
 *
 * @see #MISSING_VALUE
 */
public class IncompleteInstance extends Instance {

    /**
     * A value for missing.
     */
    public final static int MISSING_VALUE = -1;

    public IncompleteInstance(int[] value) {
        super(value);
    }
    public IncompleteInstance(int[] value, int num) {
        super(value, num);
    }

    /**
     * Set the given attribute's value into {@link #MISSING_VALUE}.
     *
     * @param attribute
     * 		The attribute of the Universe instance.(starts from 1)
     */
    public void setValueMissing(int attribute) {
        getAttributeValues()[attribute-1]=MISSING_VALUE;
    }

    /**
     * Get if the given attribute's value is {@link #MISSING_VALUE}.
     *
     * @see #getAttributeValue(int)
     *
     * @param attribute
     * 		The attribute of the Universe instance.(starts from 1, 0 as decision value)
     * @return true if it is missing.
     */
    public boolean isValueMissing(int attribute) {
        return getAttributeValue(attribute)==MISSING_VALUE;
    }

    /**
     * Get if contains missing value.
     *
     * @see #MISSING_VALUE
     *
     * @return true if contains {@link #MISSING_VALUE}.
     */
    public boolean containsMissingValue() {
        return Arrays.stream(this.getAttributeValues()).anyMatch(v->v==MISSING_VALUE);
    }

    /**
     * Return <strong>"*" + {@link Instance#toString()} + " (missing value)"</strong>.
     */
    @Override
    public String toString() {
        return "*"+super.toString() +" (missing value)";
    }
}