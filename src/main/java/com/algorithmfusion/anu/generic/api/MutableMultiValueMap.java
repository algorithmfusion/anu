package com.algorithmfusion.anu.generic.api;

import java.util.Collection;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface MutableMultiValueMap<KEY, VALUE> extends MultiValueMap<KEY, VALUE> {
	
	Collection<VALUE> remove(KEY key);
}