package com.algorithmfusion.anu.sm.observers.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ObserversFactoryTest {

	@Test
	void givenObserversFactoryWhenCreateTextStateObserverWithMessageThenTextStateObserverGetMessageMatchesGivenMessage() {
		String message = "message";
		TextStateObserver actual = ObserversFactory.createTextStateObserver(message);
		
		assertThat(actual.getMessage()).isEqualTo(message);
	}

	@Test
	void givenObserversFactoryWhenCreateTextTransitionObserverWithMessageThenTextTransitionObserverGetMessageMatchesGivenMessage() {
		String message = "message";
		TextTransitionObserver actual = ObserversFactory.createTextTransitionObserver(message);
		
		assertThat(actual.getMessage()).isEqualTo(message);
	}
}