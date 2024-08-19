package com.algorithmfusion.anu.flow.handlers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.observers.api.StateObserver;

class StateObserverContextHandlerTest {

	private StateObserverContextHandler contextHandler;
	
	@BeforeEach
	void setUp() throws Exception {
		this.contextHandler = new StateObserverContextHandler();
	}

	@Test
	void givenStateObserverContextHandlerWhenHandlewithExpectedParametersThenStateObserverGetCreated() {
		StateObserver stateObserver = mock(StateObserver.class);
		
		Object key = new Object();
		Map<Object, Object> context = new HashMap<>();
		
		context.put(key, stateObserver);
		
		StateObserver actual = contextHandler.handle(key, context);
		
		assertThat(actual).isInstanceOf(StateObserver.class);
	}
}