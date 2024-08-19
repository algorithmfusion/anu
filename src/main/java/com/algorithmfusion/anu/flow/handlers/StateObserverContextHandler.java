package com.algorithmfusion.anu.flow.handlers;

import java.util.Map;

import com.algorithmfusion.anu.generic.api.ParameterizedHandler;
import com.algorithmfusion.anu.sm.observers.api.StateObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class StateObserverContextHandler implements ParameterizedHandler<StateObserver> {

	@Override
	public StateObserver handle(Object... parameters) {
		Object key = parameters[0];
		@SuppressWarnings("unchecked")
		Map<Object, Object> context = (Map<Object, Object>) parameters[1];
		return (StateObserver) context.get(key);
	}
}