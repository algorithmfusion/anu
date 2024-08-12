package com.algorithmfusion.anu.flow;

import com.algorithmfusion.anu.flow.handlers.ContextHandler;
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
	
	private final StringToParameterizedHandler idToHandler;
	
	public FlowConfiguration() {
		this.idToHandler = new StringToParameterizedHandler();
		configure();
	}
	
	public StringToParameterizedHandler getIdToHandler() {
		return idToHandler;
	}

	private void configure() {
		idToHandler.registerIdToHandler("TextStateObserver", new TextStateObserverHandler());
		idToHandler.registerIdToHandler("Context", new ContextHandler());

		idToHandler.registerIdToHandler("TextTransitionObserver", new TextTransitionObserverHandler());
		idToHandler.registerIdToHandler("TimerTriggerPrepare", new TimerTriggerPrepareHandler());
		idToHandler.registerIdToHandler("TimerTriggerDispose", new TimerTriggerDisposeHandler());
	}
}