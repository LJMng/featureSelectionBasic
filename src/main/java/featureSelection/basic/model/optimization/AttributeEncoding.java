package featureSelection.basic.model.optimization;

/**
 * Entity with values that encoded by attribute values.
 *
 * @author Benjamin_L
 *
 * @param <EncodedType>
 * 		Class type of encoding, from attribute values in {@link int[]} into encoded value.
 */
public interface AttributeEncoding<EncodedType> {
    /**
     * Get attributes values. (Starts from 1)
     *
     * @return attribute values in <code>int[]</code>.
     */
    int[] getAttributes();

    /**
     * Get encoded (attribute) values.
     *
     * @return encoded attribute values.
     */
    EncodedType encodedValues();

    /**
     * Get {@link String} of encoded values.
     *
     * @return {@link String} of encoded values
     */
    String encodedValuesToString();

    /**
     * Get the length of encoded values.
     *
     * @return the length of encoded values.
     */
    int encodedValuesLength();

    /**
     * Get the class of {@link EncodedType}
     *
     * @return the class of {@link EncodedType}
     */
    Class<EncodedType> encodedTypeClass();
}
