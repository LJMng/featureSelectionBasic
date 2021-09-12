package common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtils {
	
	public static class Max{
		public static double[] top2Max(double[] array) {
			Double first = null, second = null;
			for (double v: array) {
				if (first!=null && second!=null) {
					if (Double.compare(second, v)<0) {
						if (Double.compare(first, v)<0) {
							second = first;
							first = v;
						}else {
							second = v;
						}
					}
				}else if (first==null) {
					first = v;
				}else {
					if (Double.compare(first, v)<0) {
						second = first;
						first = v;
					}else {
						second = v;
					}
				}			
			}
			return new double[] {first, second};
		}
		
		public static double[] top2MaxOfPlus(double[]...arrays) {
			double v;
			Double first = null, second = null;
			for (int i=0; i<arrays[0].length; i++) {
				v=0;	for (double[] d: arrays)	v+=d[i];
				if (first!=null && second!=null) {
					if (Double.compare(second, v)<0) {
						if (Double.compare(first, v)<0) {
							second = first;
							first = v;
						}else {
							second = v;
						}
					}
				}else if (first==null) {
					first = v;
				}else {
					if (Double.compare(first, v)<0) {
						second = first;
						first = v;
					}else {
						second = v;
					}
				}
			}
			return new double[] {first, second};
		}
	}
	
	public static class Calculation{
		/**
		 * Do subtraction by d1-d2, and return 0 if the value is less than deviation.
		 * 
		 * @param d1
		 * 		{@link double} value 1.
		 * @param d2
		 * 		{@link double} value 2.
		 * @param deviation
		 * 		Acceptable deviation in {@link double}.
		 * @return 0 or subtraction result in {@link double}.
		 */
		public static double subtraction(double d1, double d2, double deviation) {
			double cmp = d1-d2;
			return Double.compare(Math.abs(cmp), deviation) <=0? 0: cmp;
		}
	}
	
	/**
	 * Combinatorial number of 2.
	 * <p> 2的组合值 : C (suffix, 2).
	 * <pre>suffix==0 ? 0 : suffix * (suffix-1.0) / 2.0;</pre>
	 * 
	 * @param suffix
	 * 		The parameter down.
	 * @return A double value.
	 */
	public static double combinatorialNumOf2(int suffix) {
		if (suffix<0) {	throw new RuntimeException("Illegal parameter : "+suffix);	}
		return suffix==0 ? 0 : suffix * (suffix-1.0) / 2.0;
	}
	
}
