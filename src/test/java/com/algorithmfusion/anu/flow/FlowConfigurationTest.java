package com.algorithmfusion.anu.flow;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.flow.handlers.ContextHandler;
import com.algorithmfusion.anu.flow.handlers.TextStateObserverHandler;
import com.algorithmfusion.anu.flow.handlers.TextTransitionObserverHandler;
import com.algorithmfusion.anu.flow.handlers.TimerTriggerDisposeHandler;
import com.algorithmfusion.anu.flow.handlers.TimerTriggerPrepareHandler;
import com.algorithmfusion.anu.generic.impl.StringToParameterizedHandler;

class FlowConfigurationTest {

	private FlowConfiguration flowConfiguration;
	
	@BeforeEach
	void setUp() throws Exception {
		this.flowConfiguration = new FlowConfiguration();
	}

	@Test
	void givenFlowConfigurationInstanceWhenGetIdToHandlerThenItHasAllKnownIdToHandlersRegistration() {
		StringToParameterizedHandler idToHandler = flowConfiguration.getIdToHandler();
		
		assertThat(idToHandler.getHandler(FlowConfiguration.TEXT_STATE_OBSERVER)).isInstanceOf(TextStateObserverHandler.class);
		assertThat(idToHandler.getHandler(FlowConfiguration.CONTEXT)).isInstanceOf(ContextHandler.class);
		assertThat(idToHandler.getHandler(FlowConfiguration.TEXT_TRANSITION_OBSERVER)).isInstanceOf(TextTransitionObserverHandler.class);
		assertThat(idToHandler.getHandler(FlowConfiguration.TIMER_TRIGGER_PREPARE)).isInstanceOf(TimerTriggerPrepareHandler.class);
		assertThat(idToHandler.getHandler(FlowConfiguration.TIMER_TRIGGER_DISPOSE)).isInstanceOf(TimerTriggerDisposeHandler.class);
	}
}