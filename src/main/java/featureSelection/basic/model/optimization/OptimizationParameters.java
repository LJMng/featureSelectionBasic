package featureSelection.basic.model.optimization;

/**
 * A parameter interface for Optimization Algorithms.
 *
 * @author Benjamin_L
 */
public interface OptimizationParameters extends Cloneable {
    OptimizationAlgorithm getOptimizationAlgorithm();

    public OptimizationParameters clone() throws CloneNotSupportedException;
}
