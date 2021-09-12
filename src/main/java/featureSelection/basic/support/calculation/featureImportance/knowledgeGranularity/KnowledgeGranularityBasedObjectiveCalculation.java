package featureSelection.basic.support.calculation.featureImportance.knowledgeGranularity;

import featureSelection.basic.support.calculation.FeatureImportance;

/**
 * The calculation of <strong>knowledge granularity based Objective value</strong>
 * should be implemented based on the following.
 * <p>
 * The <strong>knowledge granularity based Objective value</strong> is designed based
 * on the paper
 * <a href="https://linkinghub.elsevier.com/retrieve/pii/S0020025515005460">
 * "High-dimensional feature selection via feature grouping: A Variable Neighborhood
 * Search approach"</a>. In the paper, the original Objective value function for a
 * feature subset <i>S</i> is defined as the following:
 * <p>
 * J(S) = [|S| * SU_SY] / sqrt( |S|+|S|(|S|-1) * SU_SS )
 * <p>
 * where
 * <ul>
 * 	<li>SU_SY = 1 / |S| * sum SU(S<sub>i</sub>, Y)</li>
 * 	<li>SU_SS = 2 / (|S|(|S|-1)) * (sum sum SU(S<sub>i</sub>, S<sub>j</sub>)  ) where i!=j</li>
 * </ul>
 * <p>
 * Which could be transformed into the following format:
 * <p>
 * J(S) = [SU_SY'] / sqrt( |S|+ SU_SS' )
 * where
 * <ul>
 * 	<li>SU_SY' = sum SU(S<sub>i</sub>, Y)</li>
 * 	<li>SU_SS' = sum sum SU(S<sub>i</sub>, S<sub>j</sub>) where i!=j</li>
 * </ul>
 * <p>
 * Based on the above equations, <strong>knowledge granularity based Objective value</strong> is defined
 * as follow:
 * <p>
 * J(S) = [|S| * GP_SY] / sqrt( |S|+|S|(|S|-1) * GP_SS )
 * <p>
 * where
 * <ul>
 * 	<li>GP_SY = 1 / |S| * sum GP(Si|Y)</li>
 * 	<li>GP_SS = 1 / (|S|(|S|-1)) * (sum sum (GP(Si|Sj) + GP(Sj|Si))  ) where i!=j</li>
 * </ul>
 * <p>
 * Which could be transformed into the following format:
 * <p>
 * J(S) = [GP_SY'] / sqrt( |S|+ GP_SS' )
 * where
 * <ul>
 * 	<li>GP_SY' = sum GP(Si|Y)</li>
 * 	<li>GP_SS' = sum sum (GP(Si|Sj) + GP(Sj|Si)) where i!=j</li>
 * </ul>
 *
 * @see KnowledgeGranularityCalculation
 * 
 * @author Benjamin_L
 *
 * @param <V>
 * 		Type of knowledge granularity value.
 */
public interface KnowledgeGranularityBasedObjectiveCalculation<V> 
	extends FeatureImportance<V>
{}