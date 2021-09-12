package common.utils;

import java.util.Arrays;

import lombok.experimental.UtilityClass;

/**
 * Utilities for array.
 * 
 * @author Benjamin_L
 */
@UtilityClass
public class ArrayUtils {
	/**
	 * Transfer <code>int[]</code> to {@link String} with limited shown length of
	 * String.
	 * 
	 * @see Arrays#toString(int[])
	 * 
	 * @param array
	 * 		The {@link int[]} to be transferred.
	 * @param maxShownEle
	 * 		The max length of <code>int</code> shown.
	 * @return {@link String}.
	 */
	public static String intArrayToString(int[] array, int maxShownEle) {
		if (array==null)	return "";
		if (maxShownEle>0 && array.length>maxShownEle) {
			StringBuilder builder = new StringBuilder();
			builder.append("\"[");
			for (int i=0; i<maxShownEle; i++) {
				builder.append(array[i]);
				builder.append(",");
			}
			builder.append("...]\"");
			return builder.toString();						
		}else {
			return "\""+Arrays.toString(array)+"\"";
		}
	}

	/**
	 * Transfer <code>byte[]</code> to {@link String} with limited shown length of
	 * String.
	 * 
	 * @see Arrays#toString(byte[])
	 * 
	 * @param array
	 * 		The {@link byte[]} to be transferred.
	 * @param maxShownEle
	 * 		The max length of <code>byte</code> shown.
	 * @return {@link String}.
	 */
	public static String byteArrayToString(byte[] array, int maxShownEle) {
		if (array==null)	return "";
		if (array.length>maxShownEle) {
			StringBuilder builder = new StringBuilder();
			builder.append("\"[");
			for (int i=0; i<maxShownEle; i++) {
				builder.append(array[i]+",");
				builder.append(",");
			}
			builder.append("...]\"");
			return builder.toString();						
		}else {
			return "\""+Arrays.toString(array)+"\"";
		}
	}

	/**
	 * Transfer <code>double[]</code> to {@link String} with limited shown length of
	 * String.
	 * 
	 * @see Arrays#toString(double[])
	 * 
	 * @param array
	 * 		The {@link double[][]} to be transferred.
	 * @return {@link String}.
	 */
	public static String doubleArrayToString(double[][] array) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int a=0; a<array.length; a++) {
			builder.append("[");
			for (int b=0; b<array[a].length; b++) {
				builder.append(array[a][b]);
				if (b!=array[a].length-1)	builder.append(",");
			}
			builder.append("]");
			if (a!=array.length-1)	builder.append(",");
		}
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * Initiate <code>int[]</code> with incremental elements.
	 * <p>
	 * This method is just like <code>np.arange()</code> in <code>Python</code>.
	 * 
	 * @param length
	 * 		The length of the <code>int[]</code> returned.
	 * @param startValue
	 * 		The 1st <code>int</code> value in array.
	 * @param increment
	 * 		Increment of each value in array. (Step)
	 * @return Constructed array.
	 */
	public static int[] initIncrementalValueIntArray(
			int length, int startValue, int increment
	) {
		int[] array = new int[length];
		for (int i=0, value=startValue; i<length; i++, value+=increment)
			array[i] = value;
		return array;
	}

	/**
	 * Sum a row of a 2-D double array.
	 * 
	 * @param data
	 * 		The 2-d double array.
	 * @param row
	 * 		The row index to sum.(starts from 0)
	 * @return sum value in {@link double}
	 */
	public static double rowSum(double[][] data, int row) {
		return Arrays.stream(data[row]).sum();
	}
	
	/**
	 * Sum a column in a 2-D double array.
	 * 
	 * @param data
	 * 		The 2-d double array.
	 * @param col
	 * 		The column index to sum.(starts from 0)
	 * @return sum value in {@link double}
	 */
	public static double colSum(double[][] data, int col) {
		return Arrays.stream(data).mapToDouble(row->row[col]).sum();
	}

	public static boolean equals(int[] array1, int[] array2) {
		if (array1==null && array2==null)				return true;
		else if (array1==null /* i.e. array2!=null */)	return false;
		else if (array1!=null /* i.e. array2==null */)	return false;
		return array1.length==array2.length && Arrays.equals(array1, array2);
	}
}