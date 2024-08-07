package com.algorithmfusion.anu.flow.handlers;

import com.algorithmfusion.anu.generic.api.ParameterizedHandler;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;
import com.algorithmfusion.anu.sm.triggers.TimerTrigger2;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TimerTriggerDisposeHandler implements ParameterizedHandler<TransitionObserver> {

	@Override
	public TransitionObserver handle(Object... parameters) {
		String timerId = (String) parameters[0];
		ObjectStorage objectStorage = (ObjectStorage) parameters[1];
		return objectStorage.retrieve(timerId, TimerTrigger2.class).getDispose();
	}

}
