package com.algorithmfusion.anu.flow.handlers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.observers.impl.TextStateObserver;

class TextStateObserverHandlerTest {

	private TextStateObserverHandler textStateObserverHandler;
	
	@BeforeEach
	void setUp() throws Exception {
		this.textStateObserverHandler = new TextStateObserverHandler();
	}

	@Test
	void givenTextStateObserverHandlerWhenHandlewithExpectedParametersThenTextStateObserverGetCreated() {
		TextStateObserver actual = textStateObserverHandler.handle("text");
		
		assertThat(actual).isInstanceOf(TextStateObserver.class);
	}
}