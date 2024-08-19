package com.algorithmfusion.anu.flow.handlers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.observers.impl.TextTransitionObserver;

class TextTransitionObserverHandlerTest {

	private TextTransitionObserverHandler textTransitionObserverHandler;
	
	@BeforeEach
	void setUp() throws Exception {
		this.textTransitionObserverHandler = new TextTransitionObserverHandler();
	}

	@Test
	void givenTextTransitionObserverHandlerWhenHandlewithExpectedParametersThenTextTransitionObserverGetCreated() {
		TextTransitionObserver actual = textTransitionObserverHandler.handle("text");
		
		assertThat(actual).isInstanceOf(TextTransitionObserver.class);
	}
}