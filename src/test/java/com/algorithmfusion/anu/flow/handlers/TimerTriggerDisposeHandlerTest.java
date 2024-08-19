package com.algorithmfusion.anu.flow.handlers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.flow.triggers.FlowTimerTrigger;
import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;
import com.algorithmfusion.anu.storage.impl.InMemoryObjectStorage;

class TimerTriggerDisposeHandlerTest {

	private TimerTriggerDisposeHandler timerTriggerDisposeHandler;
	
	@BeforeEach
	void setUp() throws Exception {
		this.timerTriggerDisposeHandler = new TimerTriggerDisposeHandler();
	}

	@Test
	void givenTimerTriggerDisposeHandlerWhenHandlewithExpectedParameterThenTransitionObserverGetCreated() {
		StateMachine stateMachine = mock(StateMachine.class);
		Transition transition = mock(Transition.class);
		TextStreamWriter writer = mock(TextStreamWriter.class);
		long interval = 10000;
		int ticks = 10;
		String timerId = "timerId";
		FlowTimerTrigger flowTimerTrigger = new FlowTimerTrigger(
				stateMachine,
				transition,
				writer,
				interval,
				ticks,
				timerId);
		
		ObjectStorage objectStorage = new InMemoryObjectStorage();
		objectStorage.store(timerId, flowTimerTrigger);
		
		TransitionObserver actual = timerTriggerDisposeHandler.handle(
				timerId,
				objectStorage);
		
		assertThat(actual).isInstanceOf(TransitionObserver.class);
	}
}