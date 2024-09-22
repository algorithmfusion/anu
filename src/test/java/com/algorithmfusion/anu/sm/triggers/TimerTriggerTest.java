package com.algorithmfusion.anu.sm.triggers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.exceptions.UnknownCurrentStateException;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

class TimerTriggerTest {

	private TimerTrigger timerTrigger;
	
	private StateMachine stateMachine;
	private Transition transition;
	private TextStreamWriter writer;
	private long interval;
	private int ticks;
	private String timerId;
	
	private TransitionObserver prepare;
	private TransitionObserver dispose;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stateMachine = mock(StateMachine.class);
		this.transition = mock(Transition.class);
		this.writer = mock(TextStreamWriter.class);

	}

	@Test
	void test() throws InterruptedException {
		this.interval = 200;
		this.ticks = 2;
		this.timerId = "timerId1";
		
		this.timerTrigger = new TimerTrigger(
				stateMachine,
				transition,
				writer,
				interval,
				ticks,
				timerId);
		
		this.prepare = timerTrigger.getPrepare();
		this.dispose = timerTrigger.getDispose();
		
		this.prepare.notify(transition);
		Thread.sleep(250);
		this.dispose.notify(transition);
		
		String timerIntervalMessage = "Timer[" + timerId + "](" + interval + ")ms";
		
		verify(writer, times(1)).writeLine("\n" + timerIntervalMessage + " started");
		verify(writer, times(2)).write(".");
		verify(writer, times(1)).writeLine("\n" + timerId + " invoked");
		verify(stateMachine, times(1)).transition(transition);
		verify(writer, times(1)).writeLine(timerIntervalMessage + " stoped" + "\n");
	}

	@Test
	void test2() throws InterruptedException {
		this.interval = 200;
		this.ticks = 2;
		this.timerId = "timerId2";
		
		this.timerTrigger = new TimerTrigger(
				stateMachine,
				transition,
				writer,
				interval,
				ticks,
				timerId);
		
		this.prepare = timerTrigger.getPrepare();
		this.dispose = timerTrigger.getDispose();
		
		String errorMessage = "error";
		Mockito.doThrow(new UnknownCurrentStateException(errorMessage)).when(stateMachine).transition(transition);
		
		this.prepare.notify(transition);
		Thread.sleep(250);
		this.dispose.notify(transition);
		
		String timerIntervalMessage = "Timer[" + timerId + "](" + interval + ")ms";
		
		verify(writer, times(1)).writeLine("\n" + timerIntervalMessage + " started");
		verify(writer, times(2)).write(".");
		verify(writer, times(1)).writeLine("\n" + timerId + " invoked");
		verify(writer, times(1)).writeLine(errorMessage);
		verify(writer, times(1)).writeLine(timerIntervalMessage + " stoped" + "\n");
	}
}