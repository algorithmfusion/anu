package com.algorithmfusion.anu.flow.handlers;

import static com.algorithmfusion.anu.sm.observers.ObserversFactory.createTextStateObserver;

import com.algorithmfusion.anu.generic.api.ParameterizedHandler;
import com.algorithmfusion.anu.sm.observers.TextStateObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextStateObserverHandler implements ParameterizedHandler<TextStateObserver> {

	@Override
	public TextStateObserver handle(Object... parameters) {
		return createTextStateObserver((String) parameters[0]);
	}
}