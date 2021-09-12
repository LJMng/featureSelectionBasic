package featureSelection.basic.lang.dataStructure.impl.integerIterator;

import featureSelection.basic.lang.dataStructure.interf.IntegerIterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Implemented {@link IntegerIterator} for <code>int[]</code> instance.
 * <p>
 * <strong>PS</strong>: Call {@link #reset()} before an iteration to reset and
 * initialise the iterator.
 *
 * @author Benjamin_L
 */
public class IntegerArrayIterator implements IntegerIterator {
    private final int[] intArray;
    private int index;

    public IntegerArrayIterator(int...array) {
        this.intArray = array;
        index = 0;
    }

    /**
     * Reset the pointer of the iterator. {@link #index} is set to 0, so the next time
     * {@link IntegerArrayIterator#next()} is called, the 1st value in {@link #intArray}
     * is returned.
     *
     * @return <code>this</code> {@link IntegerArrayIterator}.
     */
    @Override
    public IntegerArrayIterator reset() {
        index = 0;
        return this;
    }

    /**
     * Skip elements. <code>length</code> must be less than elements left to be called
     * in {@link #intArray}.
     *
     * @return true if skip elements successfully. / false if the length given is illegal.
     */
    @Override
    public boolean skip(int length) {
        if (index+length>=intArray.length)	return false;
        else								index += length;
        return true;
    }

    @Override
    public int next() {
        return intArray[index++];
    }

    @Override
    public int size() {
        return intArray.length;
    }

    @Override
    public boolean hasNext() {
        return index<intArray.length;
    }

    @Override
    public int currentIndex() {
        return index;
    }

    @Override
    public IntegerArrayIterator clone() {
        return new IntegerArrayIterator(intArray);
    }

    @Override
    public String toString() {
        return "IntegerArrayIterator" + Arrays.toString(intArray);
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){
            private int pointer = 0;
            @Override public boolean hasNext() {	return pointer<=intArray.length;	}
            @Override public Integer next() {		return intArray[pointer++];			}
            @Override public void remove() {
                throw new UnsupportedOperationException("Implemented method!");
            }
        };
    }
}