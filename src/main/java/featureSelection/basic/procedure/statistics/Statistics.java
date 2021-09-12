package featureSelection.basic.procedure.statistics;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * An entity for statistics collecting. Uses {@link HashMap} to contain statistics.
 */
public class Statistics {

	@Getter private Map<String, Object> data;

	public Statistics(){
		data = new HashMap<>();
	}

	public <V> V get(String key) {
		return (V) data.get(key);
	}

	public Object put(String key, Object value){
		return data.put(key, value);
	}

	public boolean containsKey(String key){ return data.containsKey(key); }
}
