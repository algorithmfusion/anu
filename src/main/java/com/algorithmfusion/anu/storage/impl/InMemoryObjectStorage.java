package com.algorithmfusion.anu.storage.impl;

import java.util.HashMap;
import java.util.Map;

import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class InMemoryObjectStorage implements ObjectStorage {

	private Map<Object, Object> keyStorage = new HashMap<>();
	
	@Override
	public <K, V> V store(K key, V value) {
		keyStorage.put(key, value);
		return value;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <V> V retrieve(Object key, Class<V> clazz) {
		return (V) keyStorage.get(key);
	}
}