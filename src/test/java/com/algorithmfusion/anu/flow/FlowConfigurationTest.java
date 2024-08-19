package com.algorithmfusion.anu.flow;

import static com.algorithmfusion.anu.flow.FlowObservableLifeCycle.DISPOSE_TRANSITION;
import static com.algorithmfusion.anu.flow.FlowObservableLifeCycle.ENTER_STATE;
import static com.algorithmfusion.anu.flow.FlowObservableLifeCycle.LEAVE_STATE;
import static com.algorithmfusion.anu.flow.FlowObservableLifeCycle.PERFORM_TRANSITION;
import static com.algorithmfusion.anu.flow.FlowObservableLifeCycle.PREPARE_TRANSITION;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.flow.handlers.StateObserverContextHandler;
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
		assertThat(idToHandler.getHandler(FlowConfiguration.CONTEXT)).isInstanceOf(StateObserverContextHandler.class);
		assertThat(idToHandler.getHandler(FlowConfiguration.TEXT_TRANSITION_OBSERVER)).isInstanceOf(TextTransitionObserverHandler.class);
		assertThat(idToHandler.getHandler(FlowConfiguration.TIMER_TRIGGER_PREPARE)).isInstanceOf(TimerTriggerPrepareHandler.class);
		assertThat(idToHandler.getHandler(FlowConfiguration.TIMER_TRIGGER_DISPOSE)).isInstanceOf(TimerTriggerDisposeHandler.class);
	}
	
	@Test
	void givenFlowConfigurationInstanceWhenAsStringForFlowObservableLifeCycleThenStringRepresentationIsreturned() {
		assertThat(FlowConfiguration.asString(ENTER_STATE)).isEqualTo("ENTER_STATE");
		assertThat(FlowConfiguration.asString(LEAVE_STATE)).isEqualTo("LEAVE_STATE");
		assertThat(FlowConfiguration.asString(PREPARE_TRANSITION)).isEqualTo("PREPARE_TRANSITION");
		assertThat(FlowConfiguration.asString(PERFORM_TRANSITION)).isEqualTo("PERFORM_TRANSITION");
		assertThat(FlowConfiguration.asString(DISPOSE_TRANSITION)).isEqualTo("DISPOSE_TRANSITION");
	}
}