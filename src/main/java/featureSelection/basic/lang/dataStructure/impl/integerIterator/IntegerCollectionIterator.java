package featureSelection.basic.lang.dataStructure.impl.integerIterator;

import featureSelection.basic.lang.dataStructure.interf.IntegerIterator;

import java.util.Collection;
import java.util.Iterator;

/**
 * Implemented {@link IntegerIterator} for integer values in a {@link Collection}.
 * <p>
 * <strong>PS</strong>: Call {@link #reset()} before an iteration to reset and
 * initialise the iterator.
 *
 * @author Benjamin_L
 */
public class IntegerCollectionIterator implements IntegerIterator {
    private final Collection<Integer> collection;
    private Iterator<Integer> iterator;
    private int index;

    public IntegerCollectionIterator(Collection<Integer> collection) {
        this.collection = collection;
        index = 0;
    }

    @Override
    public IntegerCollectionIterator reset() {
        iterator = collection.iterator();
        index = 0;
        return this;
    }

    @Override
    public boolean skip(int length) {
        if (index+length>=collection.size()) {
            return false;
        }else {
            index += length;
            for (int i=0; i<length; i++)	iterator.next();
            return true;
        }
    }

    @Override
    public int next() {
        index++;
        return iterator.next();
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public int currentIndex() {
        return index;
    }

    @Override
    public IntegerCollectionIterator clone() {
        return new IntegerCollectionIterator(collection);
    }

    @Override
    public String toString() {
        return "IntegerCollectionIterator " + collection;
    }


    @Override
    public Iterator<Integer> iterator() {
        return collection.iterator();
    }
}