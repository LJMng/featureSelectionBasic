package common.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtils {
	
	public static int randomUniqueInt(int start, int size, Random random) {
		if (start<0 || size<=0) {	
			throw new RuntimeException("Illegal parameter(s) : start="+start+", size="+size);
		}else {	
			return start+random.nextInt(size);
		}
	}
	
	/**
	 * Generate random distinct integers.
	 * 
	 * @param start
	 * 		Skip index 0(if start>0)~start-1.
	 * @param size
	 * 		Range of random int. 
	 * 		If start=0 and size=10, the range is [0,9]. 
	 * 		If start=10 and size=10, the range is [10,19]. 
	 * @param resultSize
	 * 		The size of return numbers.
	 * @param random
	 * 		{@link Random}.
	 * @return A {@link Set} of {@link Integer}.
	 */
	public static Set<Integer> randomUniqueInts(int start, int size, int resultSize, Random random) {
		if (start<0 || size<=0)
			throw new RuntimeException("Illegal parameter(s) : start="+start+", size="+size);
		int max = start+size-1, min = start;

		Set<Integer> set = new HashSet<>(resultSize);
		if (size==resultSize) {
			for (int v=start; v<=max; v++)	set.add(v);
			return set;
		}else {
			int r;
			while (set.size() < resultSize) {
				while(set.contains(max)) {	max--;	}
				while(set.contains(min)) {	min++;	}
				r = random.nextInt(max-min+1) + min + 1;
				while (set.contains(r)) {	r++;	}
				if (r>max) { 				r = min;}
				set.add(r);
			}
		}
		
		return set;
	}

	public static void shuffleArray(int[] array, Random random) {
		int nextIndex, value;
		for (int i=array.length-1; i>0; i--) {
			nextIndex = random.nextInt(i+1);	// 在array[0~i]范围内取一个随机下标
			value = array[i];					// 确认array[i]的值 = array[nextIndex]
			array[i] = array[nextIndex];		// array[i]与array[nextIndex]更换位置
			array[nextIndex] = value;
		}
	}
	
	public static class ShuffleArrayIterator {
		private int[] array;
		private Integer[] result;
		private int pointer;
		private Random random;
		public ShuffleArrayIterator(int[] array, Random random) {
			this.array = array;
			this.random = random;
			result = new Integer[array.length];
			pointer = 0;
		}
		
		public boolean hasNext() {
			return pointer != array.length;
		}
		
		public int next() {
			int value,
				buffPointer = array.length-pointer - 1, 
				nextIndex = random.nextInt(array.length-pointer);
			if (nextIndex>=buffPointer) {
				value = result[nextIndex]==null? array[nextIndex]: result[nextIndex];
			}else {
				if (result[nextIndex]==null) {
					value = array[nextIndex];
					result[nextIndex] = result[buffPointer]==null? array[buffPointer]: result[buffPointer];
				}else {
					value = result[nextIndex];
					result[nextIndex] = result[buffPointer]==null?array[buffPointer]: result[buffPointer];
				}
			}
			pointer++;
			return value;
		}
	}
	
	public static boolean probability(double trueRate, Random random) {
		return trueRate * 100 - random.nextInt(101) >= 0;
	}
}