package com.algorithmfusion.anu.flow.observers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.flow.FlowTransition;
import com.algorithmfusion.anu.generic.api.TextObserver;
import com.algorithmfusion.anu.generic.api.Trigable;

class TimerPrepareTransitionObserverTest {

	private TimerPrepareTransitionObserver timerPrepareTransitionObserver;
	
	private FlowTransition flowTransition;
	
	private TextObserver textObserver;
	private Trigable trigable;
	private long interval;
	private int ticks;
	private String message;
	private String timerIntervalMessage;
	
	@BeforeEach
	void setUp() throws Exception {
		this.flowTransition = mock(FlowTransition.class);
		
		this.textObserver = mock(TextObserver.class);
		this.trigable = mock(Trigable.class);
		this.interval = 10000;
		this.ticks = 10;
		this.message = "message";
		this.timerIntervalMessage = "timerIntervalMessage";
		
		this.timerPrepareTransitionObserver = 
			new TimerPrepareTransitionObserver(
					textObserver,
					trigable,
					interval,
					ticks,
					message,
					timerIntervalMessage);
	}

	@Test
	void test() {
		assertThat(timerPrepareTransitionObserver.get()).isNull();
		
		timerPrepareTransitionObserver.notify(flowTransition);
		
		assertThat(timerPrepareTransitionObserver.get()).isNotNull();
		verify(textObserver, times(2)).notify(any(String.class));
	}
}