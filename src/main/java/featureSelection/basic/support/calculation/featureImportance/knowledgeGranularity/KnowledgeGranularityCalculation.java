package featureSelection.basic.support.calculation.featureImportance.knowledgeGranularity;

import featureSelection.basic.support.calculation.FeatureImportance;

/**
 * The calculation of <strong>knowledge granularity</strong> should be implemented
 * based on the paper
 * <a href="https://www.sciencedirect.com/science/article/pii/S0020025516308775">
 * "An incremental attribute reduction approach based on knowledge granularity with a
 * multi-granulation view"</a> by Yunge Jing, Tianrui Li, Hamido Fujita, etc.
 * <p>
 * According to the paper, <strong>knowledge granularity</strong> was first introduced
 * and defined in the paper
 * <a href="http://www.sysengi.com/CN/abstract/abstract108859.shtml">"The Calculation of
 * Knowledge Granulation and Its Application"</a> by Miao Duoqian, Fan Shidong.
 * <p>
 * The <strong>knowledge granularity</strong> of C <strong>GP<sub>U</sub>(C)</strong> is
 * defined as follow:
 * <p>
 * GP<sub>U</sub>(C) = &Sigma;<sub>i=1</sub><sup>v</sup>(|X<sub>i</sub>|<sup>2</sup>/|U|<sup>2</sup>).
 * <p>
 * where |·| is the cardinal number of an instance set.(i.e. the number of universe
 * instance inside)
 * <p>
 * The <strong>knowledge granularity</strong> of C relative to D
 * <strong>GP<sub>U</sub>(D|C)</strong> is defined as follow:
 * <p>
 * GP<sub>U</sub>(D|C) = GP<sub>U</sub>(C) - GP<sub>U</sub>(C ∪ D)
 * 
 * @author Benjamin_L
 *
 * @param <V>
 * 		Type of knowledge granularity value.
 */
public interface KnowledgeGranularityCalculation<V> 
	extends FeatureImportance<V>
{}