package com.algorithmfusion.anu.generic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.algorithmfusion.anu.generic.api.MultiValueMap;

/**
 * 
 * @author Hallo Khaznadar
 */
public abstract class MultiValueMapImpl<KEY, VALUE> implements MultiValueMap<KEY, VALUE> {

	protected final Map<KEY, Collection<VALUE>> keyToValuesMap;
	
	public MultiValueMapImpl() {
		this.keyToValuesMap = new HashMap<KEY, Collection<VALUE>>();
	}

	@Override
	public Collection<VALUE> put(KEY key, VALUE value) {
		Collection<VALUE> newValues = getOrCreateEmptyValuesIfAbsent(key);
		newValues.add(value);
		return newValues;
	}

	@Override
	public Collection<VALUE> putAll(KEY key, VALUE[] values) {
		Collection<VALUE> newValues = getOrCreateEmptyValuesIfAbsent(key);
		Collections.addAll(newValues, values);
		return newValues;
	}

	@Override
	public Collection<VALUE> putAll(KEY key, Collection<VALUE> values) {
		Collection<VALUE> newValues = getOrCreateEmptyValuesIfAbsent(key);
		newValues.addAll(values);
		return newValues;
	}

	@Override
	public Collection<VALUE> get(KEY key) {
		return keyToValuesMap.get(key);
	}

	private Collection<VALUE> getOrCreateEmptyValuesIfAbsent(KEY key) {
		return keyToValuesMap.computeIfAbsent(key, k -> new ArrayList<VALUE>());
	}
}