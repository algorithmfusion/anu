package com.algorithmfusion.anu.generic.impl;

import java.util.Collection;
import java.util.Collections;

import com.algorithmfusion.anu.generic.api.ImmutableMultiValueMap;

/**
 * 
 * @author Hallo Khaznadar
 */
public class ImmutableMultiValueMapImpl<KEY, VALUE> extends MultiValueMapImpl<KEY, VALUE> implements ImmutableMultiValueMap<KEY, VALUE> {

	@Override
	public Collection<VALUE> put(KEY key, VALUE value) {
		return Collections.unmodifiableCollection(super.put(key, value));
	}

	@Override
	public Collection<VALUE> putAll(KEY key, VALUE[] values) {
		return Collections.unmodifiableCollection(super.putAll(key, values));
	}
	
	@Override
	public Collection<VALUE> putAll(KEY key, Collection<VALUE> values) {
		return Collections.unmodifiableCollection(super.putAll(key, values));
	}

	@Override
	public Collection<VALUE> get(KEY key) {
		return Collections.unmodifiableCollection(super.get(key));
	}
}