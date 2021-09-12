package common.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CollectionUtils {
	/**
	 * Copy {@link HashMap}: 
	 * <pre>
	 * Map<Key, Value> copy = new HashMap<>();
	 * for (Map.Entry<Key, Value> entry: map.entrySet())
	 * 	copy.put(entry.getKey(), entry.getValue());
	 * </pre>
	 * 
	 * @param <Key>
	 * 		the type of keys maintained by this map
	 * @param <Value>
	 * 		 the type of mapped values
	 * @param map
	 * 		The map to be copied.
	 * @return {@link Map}.
	 */
	public static <Key, Value> Map<Key, Value> copyHashMap(Map<Key, Value> map){
		Map<Key, Value> copy = new HashMap<>();
		for (Map.Entry<Key, Value> entry: map.entrySet())
			copy.put(entry.getKey(), entry.getValue());
		return copy;
	}

	/**
	 * Map the given keys and values into {@link HashMap} in order.
	 *
	 * @param <Key>
	 *     	Type of key in {@link Map}
	 * @param <Value>
	 *     	Type of value in {@link Map}
	 * @param k
	 * 		Keys in order.
	 * @param v
	 * 		Values in oder, correspondent to keys in <code>k</code>.
	 * @return Loaded {@link HashMap}.
	 */
	public static <Key, Value> Map<Key, Value> toHashMap(Key[] k, Value[] v){
		Map<Key, Value> map = new HashMap<>(k.length);
		for (int i=0; i<k.length; i++)	map.put(k[i], v[i]);
		return map;
	}
	
	
	/**
	 * Transfer {@link Map#toString()} back to {@link Map}.
	 * 
	 * @param str
	 * 		The content of {@link Map#toString()}.
	 * @return Transfered {@link Map} instance.
	 */
	public static Map<String, String> stringToMap(String str){
		String[] array = str.substring(1, str.length()-1).split(",");
		Map<String, String> map = new HashMap<>(array.length);
		String[] keyValue;
		for (String each: array) {
			keyValue = each.split("=");
			map.put(keyValue[0], keyValue[1]);
		}
		return map;
	}

	/**
	 * Transfer key-values in {@link Properties} into {@link Map}
	 * 
	 * @param prop
	 * 		{@link Properties} instance.
	 * @return {@link Map}
	 */
	public static Map<String, String> propertiesToMap(Properties prop){
		return Collections.unmodifiableMap(
				prop.entrySet().stream().collect(
					Collectors.toMap(
							entry->entry.getKey().toString(),
							entry->entry.getValue().toString()
					)
				)
			);
	}
	/**
	 * Collect key-values in {@link Properties} into {@link String[]}.
	 * 
	 * @param prop
	 * 		{@link Properties} instance.
	 * @return {@link Collection}
	 */
	public static Collection<String[]> propertiesToMapEntries(Properties prop){
		Enumeration<Object> keys = prop.keys();
		Collection<String[]> collections = new LinkedList<>();
		while (keys.hasMoreElements()) {
			String ele = keys.nextElement().toString();
			collections.add(new String[] {ele, prop.get(ele).toString()});
		}
		return collections;
	}

	/**
	 * Search <code>key</code>(with prefix) in the given {@link Map}.
	 * <p>
	 * For every prefix, it is concatenated with key and search in {@link Map}. Value is returned once is
	 * found, otherwise return <code>map.get(key)</code>.  
	 * 
	 * @param <V>
	 * 		Type of value.
	 * @param map
	 * 		The {@link Map} to be search in.
	 * @param key
	 * 		The key for searching.
	 * @param prefix
	 * 		Prefixes for concatenations.
	 * @return Value if found. / null if no value is found.
	 */
	public static <V> V searchWithPrefix(Map<String, V> map, String key, String...prefix){
		if (prefix!=null && prefix.length>0) {
			V v;
			for (String p: prefix) {
				v = map.get(p+key);
				if (v!=null)	return v;
			}
		}
		return map.get(key);
	}


	public static <V> V firstOf(Iterable<V> itr){
		return itr.iterator().next();
	}
}
