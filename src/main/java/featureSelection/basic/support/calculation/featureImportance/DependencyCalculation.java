package featureSelection.basic.support.calculation.featureImportance;

import featureSelection.basic.support.calculation.FeatureImportance;

/**
 * Measure feature (subset) importance using dependency.
 */
public interface DependencyCalculation
	extends FeatureImportance<Double>
{
	public static final String CALCULATION_NAME = "Dep.";
}
