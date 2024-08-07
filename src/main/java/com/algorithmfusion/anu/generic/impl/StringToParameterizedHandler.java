package com.algorithmfusion.anu.generic.impl;

import java.util.HashMap;
import java.util.Map;

import com.algorithmfusion.anu.generic.api.ParameterizedHandler;

/**
 * 
 * @author Hallo Khaznadar
 */
public class StringToParameterizedHandler {
	
	private final Map<String, ParameterizedHandler<?>> idsToHandlers;
	private final ParameterizedHandler<?> defaultHandler;
	
	public StringToParameterizedHandler() {
		this(new ParameterizedHandler<>() {
			@Override
			public Object handle(Object... parameters) {
				return null;
			}
		});
	}
	
	public StringToParameterizedHandler(ParameterizedHandler<?> defaultHandler) {
		this.idsToHandlers = new HashMap<>();
		this.defaultHandler = defaultHandler;
	}
	
	public void registerIdToHandler(String handlerId, ParameterizedHandler<?> parameterizedHandler) {
		idsToHandlers.put(handlerId, parameterizedHandler);
	}
	
	@SuppressWarnings("unchecked")
	public <T> ParameterizedHandler<T> getHandler(String handlerId) {
		return (ParameterizedHandler<T>) idsToHandlers.getOrDefault(handlerId, defaultHandler);
	}
}