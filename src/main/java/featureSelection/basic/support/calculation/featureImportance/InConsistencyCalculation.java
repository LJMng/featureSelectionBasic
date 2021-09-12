package featureSelection.basic.support.calculation.featureImportance;

import featureSelection.basic.support.calculation.FeatureImportance;

/**
 * Measure feature (subset) importance using in-consistency.
 */
public interface InConsistencyCalculation
	extends FeatureImportance<Integer>
{
	public static final String CALCULATION_NAME = "InCon";
}