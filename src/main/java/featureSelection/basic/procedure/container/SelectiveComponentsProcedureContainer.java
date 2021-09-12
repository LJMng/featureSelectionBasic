package featureSelection.basic.procedure.container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import featureSelection.basic.procedure.ProcedureComponent;
import featureSelection.basic.procedure.parameter.ProcedureParameters;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link DefaultProcedureContainer} with <strong>selective</strong> and
 * <strong>replacable</strong> {@link ProcedureComponent}s.
 * <p>
 * {@link #setComponent} to replace a particular {@link ProcedureComponent} by key.
 * 
 * @see DefaultProcedureContainer
 * 
 * @author Benjamin_L
 *
 * @param <T>
 * 		Type of execution return result.
 */
@Slf4j
public abstract class SelectiveComponentsProcedureContainer<T> 
	extends DefaultProcedureContainer<T> 
{
	@Getter private Map<String, ProcedureComponent<?>> componentMap;
	
	public SelectiveComponentsProcedureContainer(
			boolean logOn, ProcedureParameters parameters
	) {
		super(logOn? log: null, parameters);
		componentMap = new ConcurrentHashMap<>();

		initDefaultComponents(logOn);
	}

	/**
	 * Initiate components to be executed.
	 */
	public ProcedureComponent<?>[] initComponents(){
		ProcedureComponent<?> component;
		String[] componentKeys = componentsExecOrder();
		ProcedureComponent<?>[] componentArray = new ProcedureComponent[componentKeys.length];
		for (int i=0; i<componentKeys.length; i++) {
			component = componentMap.get(componentKeys[i]);
			if (component==null) {
				throw new IllegalStateException("No such component named '"+componentKeys[i]+"' is set.");
			}else {
				componentArray[i] = component;
			}
		}
		return componentArray;
	}

	public abstract void initDefaultComponents(boolean logOn);
	public abstract String[] componentsExecOrder();

	/**
	 * Get if contains {@link ProcedureComponent} named by the given <code>key</code>.
	 *
	 * @param key
	 * 		The name of the {@link ProcedureComponent} to search.
	 * @return true if {@link ProcedureComponent} with the given name is found in
	 * 		{@link #componentMap}.
	 */
	public boolean containsComponent(String key) {
		return componentMap.containsKey(key);
	}

	/**
	 * Set the name of the given {@link ProcedureComponent}.
	 *
	 * @param key
	 * 		Unique name of <code>component</code> as key in {@link #componentMap}.
	 * @param component
	 * 		The {@link ProcedureComponent} to be saved.
	 */
	public void setComponent(String key, ProcedureComponent<?> component) {
		componentMap.put(key, component);
	}

	/**
	 * Get {@link ProcedureComponent} by its name.
	 *
	 * @param key
	 * 		Name of the {@link ProcedureComponent} to search for.
	 * @return {@link ProcedureComponent} if found. / <code>null</code> if not found.
	 */
	public ProcedureComponent<?> getComponent(String key){
		return componentMap.get(key);
	}
}