package com.algorithmfusion.anu.flow.handlers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.flow.Flow;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.base.BaseState;
import com.algorithmfusion.anu.sm.base.BaseTransition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;
import com.algorithmfusion.anu.storage.impl.InMemoryObjectStorage;

class TimerTriggerPrepareHandlerTest {

	private TimerTriggerPrepareHandler timerTriggerPrepareHandler;
	
	@BeforeEach
	void setUp() throws Exception {
		this.timerTriggerPrepareHandler = new TimerTriggerPrepareHandler();
	}

	@Test
	void givenTimerTriggerPrepareHandlerWhenHandlewithExpectedParametersThenTransitionObserverGetCreated() {
		String timerId = "timerId";
		String interval = "10000";
		String ticks = "10";
		ObjectStorage objectStorage = new InMemoryObjectStorage();
		Flow flow = Flow.builder().build();
		Transition transition = BaseTransition.builder()
					.from(new BaseState())
					.to(new BaseState())
				.build();
		
		TransitionObserver actual = timerTriggerPrepareHandler.handle(
				timerId,
				interval,
				ticks,
				objectStorage,
				flow,
				transition);
		
		assertThat(actual).isInstanceOf(TransitionObserver.class);
	}
}