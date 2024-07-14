package com.algorithmfusion.anu.flow.handlers;

import static com.algorithmfusion.anu.sm.triggers.TriggersFactory.createTimerTrigger2;

import com.algorithmfusion.anu.flow.BpmnFlow;
import com.algorithmfusion.anu.generic.api.ParameterizedHandler;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.TransitionObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TimerTriggerPrepareHandler implements ParameterizedHandler<TransitionObserver> {

	@Override
	public TransitionObserver handle(Object... parameters) {
		String timerId = (String) parameters[0];
		int interval = Integer.parseInt((String) parameters[1]);
		int ticks = Integer.parseInt((String) parameters[2]);
		ObjectStorage objectStorage = (ObjectStorage) parameters[3];		
		BpmnFlow flow = (BpmnFlow) parameters[4];
		Transition transition = (Transition) parameters[5];
		return objectStorage.store(timerId, createTimerTrigger2(flow, transition, interval, ticks, timerId)).getPrepare();
	}
}