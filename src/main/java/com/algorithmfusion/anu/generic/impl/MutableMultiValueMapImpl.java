package com.algorithmfusion.anu.generic.impl;

import java.util.Collection;

import com.algorithmfusion.anu.generic.api.MutableMultiValueMap;

/**
 * 
 * @author Hallo Khaznadar
 */
public class MutableMultiValueMapImpl<KEY, VALUE> extends MultiValueMapImpl<KEY, VALUE> implements MutableMultiValueMap<KEY, VALUE> {

	@Override
	public Collection<VALUE> remove(KEY key) {
		return keyToValuesMap.remove(key);
	}
}