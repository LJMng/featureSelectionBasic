package featureSelection.basic.lang.dataStructure;


import java.util.Arrays;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A wrapped entity for <code>int[]</code> as key in {@link java.util.Map Map}. The
 * following methods are overridden and is of importance:
 * <ul>
 * 	<li>{@link IntArrayKey#equals(Object)}</li>
 * 	<li>{@link IntArrayKey#hashCode()}</li>
 * </ul>
 * <p>
 * When constructing, {@link IntArrayKey#IntArrayKey(int...)} is recommended.
 * <p>
 * Call {@link IntArrayKey#key()} to get the <code>int[]</code>.
 *
 * @author Benjamin_L
 */
@NoArgsConstructor
public class IntArrayKey {
    @Setter @Getter private int[] key;

    public IntArrayKey(int...key) {
        this.key = key;
    }

    public int[] key() {
        return key;
    }

    /**
     * Indicates whether the given {@link Object} <code>k</code> is "equal to"
     * <code>this</code> one:
     * <p>
     * <ul>
     * 	<li><code>k</code> is <code>int[]</code> or {@link IntArrayKey}</li>
     * 	<li>{@link Arrays#equals(int[], int[])} returns true</li>
     * </ul>
     *
     * @see Arrays#equals(int[], int[])
     *
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(Object k) {
        if (k instanceof int[])				return Arrays.equals((int[])k, key);
        else if (k instanceof IntArrayKey)	return Arrays.equals(((IntArrayKey)k).key(), key);
        else 								return false;
    }

    /**
     * The hashcode of current instance. Set based on instance field {@link #key}:
     * <pre>return Arrays.hashCode(key);</pre>
     *
     * @see  Arrays#hashCode(int[])
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(key);
    }

    public String toString() {
        return Arrays.toString(key);
    }
}