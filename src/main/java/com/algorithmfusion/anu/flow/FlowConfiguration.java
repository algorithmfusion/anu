package com.algorithmfusion.anu.flow;

import com.algorithmfusion.anu.flow.handlers.StateObserverContextHandler;
import com.algorithmfusion.anu.flow.handlers.TextStateObserverHandler;
import com.algorithmfusion.anu.flow.handlers.TextTransitionObserverHandler;
import com.algorithmfusion.anu.flow.handlers.TimerTriggerDisposeHandler;
import com.algorithmfusion.anu.flow.handlers.TimerTriggerPrepareHandler;
import com.algorithmfusion.anu.generic.impl.StringToParameterizedHandler;

/**
 * 
 * @author Hallo Khaznadar
 */
public class FlowConfiguration {
	
	static final String TEXT_STATE_OBSERVER = "TextStateObserver";
	static final String CONTEXT = "Context";
	static final String TEXT_TRANSITION_OBSERVER = "TextTransitionObserver";
	static final String TIMER_TRIGGER_PREPARE = "TimerTriggerPrepare";
	static final String TIMER_TRIGGER_DISPOSE = "TimerTriggerDispose";
	
	private final StringToParameterizedHandler idToHandler;
	
	public FlowConfiguration() {
		this.idToHandler = new StringToParameterizedHandler();
		configure();
	}
	
	public StringToParameterizedHandler getIdToHandler() {
		return idToHandler;
	}
	
	public static String asString(FlowObservableLifeCycle flowObservableLifeCycle) {
		return flowObservableLifeCycle.name();
	}

	private void configure() {
		idToHandler.registerIdToHandler(TEXT_STATE_OBSERVER, new TextStateObserverHandler());
		idToHandler.registerIdToHandler(CONTEXT, new StateObserverContextHandler());

		idToHandler.registerIdToHandler(TEXT_TRANSITION_OBSERVER, new TextTransitionObserverHandler());
		idToHandler.registerIdToHandler(TIMER_TRIGGER_PREPARE, new TimerTriggerPrepareHandler());
		idToHandler.registerIdToHandler(TIMER_TRIGGER_DISPOSE, new TimerTriggerDisposeHandler());
	}
}