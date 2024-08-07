package com.algorithmfusion.anu.flow.handlers;

import static com.algorithmfusion.anu.sm.observers.impl.ObserversFactory.createTextTransitionObserver;

import com.algorithmfusion.anu.generic.api.ParameterizedHandler;
import com.algorithmfusion.anu.sm.observers.impl.TextTransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextTransitionObserverHandler implements ParameterizedHandler<TextTransitionObserver> {

	@Override
	public TextTransitionObserver handle(Object... parameters) {
		return createTextTransitionObserver((String) parameters[0]);
	}

}
