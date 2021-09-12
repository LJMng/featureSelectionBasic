package featureSelection.basic.procedure;

import java.util.HashMap;
import java.util.Map;

import featureSelection.basic.procedure.component.action.AfterComponentExecutedAction;
import featureSelection.basic.procedure.component.action.BeforeComponentExecutedAction;
import featureSelection.basic.procedure.component.action.ComponentExecution;
import featureSelection.basic.procedure.parameter.ProcedureParameters;
import featureSelection.basic.procedure.statistics.Statistics;
import featureSelection.basic.procedure.statistics.StatisticsCalculated;
import lombok.Getter;
import lombok.Setter;

/**
 * Implement {@link Procedure} for procedure components.
 *
 * @author Benjamin_L
 *
 * @param <T>
 * 		Execution result type.
 */
@Getter
public abstract class ProcedureComponent<T>
        implements Procedure, StatisticsCalculated
{
    private final String tag;
    private String description;

    private final ProcedureParameters parameters;
    private final Map<String, ProcedureContainer<?>> subProcedureContainers;

    private final Statistics statistics;

    private final BeforeComponentExecutedAction beforeAction;
    private final AfterComponentExecutedAction<T> afterAction;
    private final ComponentExecution<T> execution;

    @Setter private Object[] localParameters;

    public ProcedureComponent(String tag, ProcedureParameters parameters,
                              BeforeComponentExecutedAction beforeAction,
                              ComponentExecution<T> execution,
                              AfterComponentExecutedAction<T> afterAction
    ) {
        this.tag = tag;
        this.parameters = parameters;
        this.beforeAction = beforeAction;
        this.afterAction = afterAction;
        this.execution = execution;

        subProcedureContainers = new HashMap<>();
        statistics = new Statistics();
        init();
    }

    public ProcedureComponent<T> setDescription(String desc){
        this.description = desc;
        return this;
    }

    public <V> V getParameterValue(String key) {
        return parameters.get(key);
    }

    public boolean containsStatistics(String key) {
        return this.statistics.containsKey(key);
    }

    public ProcedureComponent<T> setStatistics(String key, Object value){
        this.statistics.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <V> V getStatisticsValue(String key) {
        return (V) this.statistics.get(key);
    }

    /**
     * Initiate the Procedure Component.
     */
    public abstract void init();

    /**
     * Execute the Procedure Component:
     * <ul>
     * 	<li>Execute {@link #beforeAction} if it is not null.</li>
     * 	<li>Execute {@link #execution}.</li>
     * 	<li>Execute {@link #afterAction} if it is not null.</li>
     * </ul>
     *
     * @return execution result in {@link T}.
     * @throws Exception if exceptions occur when executing.
     */
    public T exec() throws Exception {
        if (beforeAction!=null)	beforeAction.exec(this);
        T result = execution.exec(this, localParameters);
        if (afterAction!=null)	afterAction.exec(this, result);
        return result;
    }

    public ProcedureComponent<T> setSubProcedureContainer(String key, ProcedureContainer<?> container) {
        subProcedureContainers.put(key, container);
        return this;
    }
}
