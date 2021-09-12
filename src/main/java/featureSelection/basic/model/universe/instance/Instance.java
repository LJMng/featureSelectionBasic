package featureSelection.basic.model.universe.instance;

import featureSelection.basic.lang.dataStructure.interf.IntegerIterator;
import lombok.Getter;

import java.util.Arrays;

/**
 * A model for a general Universe instance, represents a line of data in a Decision
 * Table.
 *
 * @author Benjamin_L
 */
public class Instance {
    public static int DECISION_ATTRIBUTE_INDEX = 0;

    protected int[] attributeValues;

    @Getter
    private static int numCounter = 1;
    protected final int num;

    public Instance(int[] value){
        this(value, numCounter++);
    }
    /**
     * Construct a Universe Instance with the given <code>value</code> and the given
     * <code>num</code> as id.
     *
     * @param value
     * 		The attribute values.
     * @param num
     * 		id.
     */
    public Instance(int[] value, int num) {
        if (value!=null)	setAttributeValue(value);
        this.num = num;
    }

    /**
     * Reset <code>numCounter</code>(ID) into 1.
     */
    public static void resetID() {
        numCounter = 1;
    }

    /**
     * Set <code>numCounter</code> into the given id.
     *
     * @param id
     * 		The ID number to be set to.
     */
    public static void setID(int id) {
        numCounter = id;
    }

    /**
     * Get the index number of this Universe
     *
     * @return an int value
     */
    public int getNum(){
        return num;
    }

    /**
     * Get how many value the universe contains
     *
     * @return an int value
     */
    public int getValueLength(){
        return attributeValues.length;
    }

    /**
     * Set attribute values.
     *
     * @param value
     * 		Values to be set in order.(0 as decision value, condition attributes
     * 		start from 1)
     */
    public void setAttributeValue(int...value){
        attributeValues = value;
    }

    /**
     * Set attribute <code>index</code>'s value.
     *
     * @param index
     * 		The index of the value. (starts from 1, 0 as decision attribute)
     * @param value
     * 		Values to be set.(0 as decision value, condition attributes start from 1)
     */
    public void setAttributeValueOf(int index, int value) {
        attributeValues[index] = value;
    }

    /**
     * Get the attribute value by index.
     *
     * @param index
     * 		The index of the value. (starts from 1, 0 as decision attribute)
     * @return the int value./-1 if the index is illegal.
     */
    public int getAttributeValue(int index){
        try {	return attributeValues[index];	}catch(IndexOutOfBoundsException e) {	return -1;	}
    }

    /**
     * Get attribute values.
     *
     * @return an array of Integer values.
     */
    public int[] getAttributeValues(){
        return attributeValues;
    }

    /**
     * Get the condition attribute values.
     *
     * @return The condition attribute values.
     */
    public int[] getConditionAttributeValues() {
        return Arrays.copyOfRange(attributeValues, 1, attributeValues.length);
    }

    /**
     * Return
     * <p>
     * "<strong>Instance #</strong>" + <strong>{@link #num}</strong> + "	" +
     * <strong>{@link #getConditionAttributeValues()}</strong> + "	"+
     * "<strong>d = </strong>" + <strong>{@link #attributeValues}[0]</strong>
     */
    public String toString(){
        if(attributeValues==null)	return "Instance #"+num;

        StringBuilder builder = new StringBuilder();
        builder.append("Instance-"+num+"	");
        for (int i=1; i<attributeValues.length; i++) {
            builder.append(attributeValues[i]);
            if (i!=attributeValues.length-1)	builder.append(", ");
        }
        builder.append("	"+"d = "+attributeValues[0]);
        return builder.toString();
    }

    public int compareTo(Instance o, IntegerIterator attributes) {
        int cmp, attr;
        attributes.reset();
        for (int i=0; i<attributes.size(); i++) {
            attr = attributes.next();
            cmp = this.getAttributeValue(attr) - o.getAttributeValue(attr);
            if ( cmp!=0 )	return cmp;
        }
        return 0;
    }

    /* -------------------------------------------------------------------------------------------------- */

    /**
     * Get attribute values of the given {@link Instance} of the given
     * <code>attributes</code>.
     *
     * @param ins
     * 		The {@link Instance} to be loaded.
     * @param attributes
     * 		Attributes of {@link Instance} for loading.(Starts from 1)
     * @return loaded values in {@link int[]}.
     */
    public static int[] attributeValuesOf(Instance ins, IntegerIterator attributes) {
        attributes.reset();
        int[] values = new int[attributes.size()];
        for (int i=0; i<values.length; i++)	values[i] = ins.getAttributeValue(attributes.next());
        return values;
    }
}
