package com.algorithmfusion.anu.flow.observers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Timer;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.TextObserver;
import com.algorithmfusion.anu.sm.api.Transition;

class TimerDisposeTransitionObserverTest {

	private TimerDisposeTransitionObserver timerDisposeTransitionObserver;
	
	private Transition observable;
	
	private TextObserver textObserver;
	private Supplier<Timer> timerSupplier;
	private Timer timer;
	private String timerIntervalMessage;
	
	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() throws Exception {
		this.observable = mock(Transition.class);
		this.textObserver = mock(TextObserver.class);
		this.timerSupplier = mock(Supplier.class);
		this.timer = mock(Timer.class);
		this.timerIntervalMessage = "message";
		
		when(timerSupplier.get()).thenReturn(timer);
		
		this.timerDisposeTransitionObserver = 
			new TimerDisposeTransitionObserver(
				textObserver,
				timerSupplier,
				timerIntervalMessage);
	}

	@Test
	void test() {
		timerDisposeTransitionObserver.notify(observable);
		
		verify(timerSupplier, times(1)).get();
		verify(timer, times(1)).cancel();
		verify(textObserver, times(1)).notify(timerIntervalMessage+ " stoped" + "\n\n");
	}
}