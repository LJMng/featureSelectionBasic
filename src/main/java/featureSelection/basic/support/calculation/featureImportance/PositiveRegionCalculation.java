package featureSelection.basic.support.calculation.featureImportance;

import featureSelection.basic.support.calculation.FeatureImportance;

/**
 * Measure feature (subset) importance using positive region.
 */
public interface PositiveRegionCalculation<Sig extends Number>
	extends FeatureImportance<Sig>
{
	public static final String CALCULATION_NAME = "Pos";
}
