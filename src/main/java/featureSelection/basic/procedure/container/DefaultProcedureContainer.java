package featureSelection.basic.procedure.container;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.utils.LoggerUtil;
import common.utils.StringUtils;
import featureSelection.basic.model.optimization.OptimizationParameters;
import featureSelection.basic.procedure.ProcedureComponent;
import featureSelection.basic.procedure.ProcedureContainer;
import featureSelection.basic.procedure.parameter.ProcedureParameterValue;
import featureSelection.basic.procedure.parameter.ProcedureParameters;
import featureSelection.basic.procedure.statistics.RealtimeProgressUpdating;
import org.slf4j.Logger;

import lombok.Getter;

/**
 * An abstract class for Default {@link ProcedureContainer}. In this container,
 * {@link #shortName()} is used as description also.
 * <p>
 * Specially, this container can notify {@link #progressUpdatee} as its {@link #progress}
 * changes. In order to do so, {@link #progressUpdatee} need to be set before
 * {@link #exec()} by calling {@link #setProgressUpdatee(Object)}.
 * <pre>
 * Object anObject = ...
 * ...
 * 
 * DefaultProcedureContainer container = new DefaultProcedureContainer(parameters);
 * ...
 * container.setProgressUpdatee(anObject);
 * ...
 * T result = container.exec();
 * // Then container executes ...
 * // Container does something ...
 * // Container updates progress ...
 * // (anObject.notify();)	// once {@link #progress} changes, anObject.notify() will be called by the container.
 * // Container does something ...
 * </pre>
 * 
 * @author Benjamin_L
 *
 * @param <T>
 * 		Type of Procedure Container execution result.
 */
public abstract class DefaultProcedureContainer<T> 
	implements ProcedureContainer<T>,
				RealtimeProgressUpdating
{
	public static int idCounter = 0;
	public int id = ++idCounter;
	
	private Logger log;
	private ProcedureParameters parameters;
	private List<ProcedureComponent<?>> components;
	
	public DefaultProcedureContainer(ProcedureParameters parameters) {
		this.parameters = parameters;
		components = new LinkedList<>();
	}
	public DefaultProcedureContainer(Logger log, ProcedureParameters parameters) {
		this.log = log;
		this.parameters = parameters;
		components = new LinkedList<>();
	}

	public int id() {
		return id;
	}
	
	@Override
	public String description() {
		return shortName();
	}

	@Override
	public ProcedureParameters getParameters() {
		return parameters;
	}

	@Override
	public List<ProcedureComponent<?>> getComponents() {
		return components;
	}
	
	public abstract ProcedureComponent<?>[] initComponents();

	@SuppressWarnings("unchecked")
	@Override
	public T exec() throws Exception {
		parametersDisplay(log);
		
		for (ProcedureComponent<?> each : initComponents())	this.components.add(each);
		int finished = 0;
		Object result = null;
		for (ProcedureComponent<?> component: components) {
			result = component.exec();
			this.setProgress((++finished) / (double) components.size());
		}
		destoryThreadPool();
		
		return (T) result;
	}
	
	private void parametersDisplay(Logger log) throws IllegalArgumentException, IllegalAccessException {
		if (log!=null) {
			LoggerUtil.printLine(log, "=", 70);
			log.info("Parameters of 【"+this.description()+"】 : ");
			LoggerUtil.printLine(log, "-", 70);
			for (Entry<String, ProcedureParameterValue<?>> entry: this.getParameters().getParameters().entrySet()) {
				if(entry.getValue().getValue() instanceof OptimizationParameters) {
					for (Field field: entry.getValue().getValue().getClass().getDeclaredFields()) {
						field.setAccessible(true);
						parametersDisplayOne(field.getName(), 
											field.get(entry.getValue().getValue()), 
											entry.getValue().getClassOfValue()
						);
					}
				}else  {
					parametersDisplayOne(entry.getKey(), entry.getValue().getValue(),
										entry.getValue().getClassOfValue()
					);
				}
			}
			LoggerUtil.printLine(log, "=", 70);
		}
	}
	
	private void parametersDisplayOne(String key, Object value, Class<?> valueClass) {
		if (value instanceof Collection) {
			log.info("	|{}| = {}, {}", key, 
										((Collection<?>) value).size(), 
										valueClass.getSimpleName()
					);
		}else if (value instanceof Map) {
			log.info("	|{}| = {}, {}", key, 
										((Map<?,?>) value).size(), 
										valueClass.getSimpleName()
					);
		}else if (value instanceof int[]) {
			log.info("	|{}| = {}, {}", key, 
										((int[]) value).length, 
										"int[]"
					);
		}else  {
			log.info("	{} = {}", key, value==null? null: StringUtils.shortString(value.toString(), 95));
		}
	}

	/* -------------------------------------------------------------------------------------------------- */
	
	@Getter private double progress;
	/**
	 * Set progress.
	 * 
	 * @param progress
	 * 		The progress value to be set. (0~1)
	 */
	public void setProgress(double progress) {
		this.progress = progress;
		notifyUpdatee(log);
	}
	
	private Object progressUpdatee;
	private ExecutorService executorService;

	/**
	 * Notify {@link #progressUpdatee}
	 * 
	 * @param log
	 * 		{@link Logger} instance. (Ignored)
	 */
	public void notifyUpdatee(Logger log) {
		if (progressUpdatee!=null) {
			if (executorService==null || executorService.isShutdown()) {
				executorService = Executors.newFixedThreadPool(1);
			}
			executorService.execute(new Thread("Procedure-Updatee-Notify-thread"){
				@Override public void run() {
					synchronized(progressUpdatee) {	
						progressUpdatee.notify();	
					}
				}
			});
		}
	}
	
	/**
	 * Set progress updatee. Once execution {@link #progress} changes, the given object will be notified.
	 * 
	 * @param object
	 * 		The object to be called notify().
	 * @return <code>this</code> instance.
	 */
	public RealtimeProgressUpdating setProgressUpdatee(Object object) {
		progressUpdatee = object;
		return this;
	}
	
	/**
	 * Shutdown the {@link #executorService}.
	 */
	private void destoryThreadPool() {
		if (executorService!=null) {
			executorService.shutdown();
			executorService = null;
		}
	}
}