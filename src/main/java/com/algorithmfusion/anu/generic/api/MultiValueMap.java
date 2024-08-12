package com.algorithmfusion.anu.generic.api;

import java.util.Collection;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface MultiValueMap<KEY, VALUE> {

	Collection<VALUE> put(KEY key, VALUE value);
	
	Collection<VALUE> putAll(KEY key, VALUE[] values);
	
	Collection<VALUE> putAll(KEY key, Collection<VALUE> values);
	
	Collection<VALUE> get(KEY key);
}