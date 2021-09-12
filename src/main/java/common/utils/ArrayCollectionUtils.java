package common.utils;

import java.util.Collection;

import lombok.experimental.UtilityClass;

/**
 * Utilities for array-collection conversion.
 * 
 * @author Benjamin_L
 */
@UtilityClass
public class ArrayCollectionUtils {
	/**
	 * Get the int array by an Integer array
	 * 
	 * @param array
	 * 		an Integer array
	 * @return an int array of the Integer array's elements
	 */
	public static int[] getIntArrayByInteger(Integer[] array) {
		int[] result = new int[array.length];
		int i=0;	for ( int each : array ) {	result[i++] = each;	}
		return result;
	}
	
	/**
	 * Get int array by collection
	 * 
	 * @param <K>
	 * 		A class instance which extends collection, and its 
	 * 		elements' types are Integer.
	 * @param collection
	 * 		The collection instance
	 * @return an int array of the collection's elements.
	 */
	public static <K extends Collection<Integer>> int[] getIntArrayByCollection(K collection) {
		if (collection==null) 			{	return null;		}
		else if (collection.isEmpty())	{	return new int[0];	}
		return collection.stream().mapToInt(v->v).toArray();
	}

}