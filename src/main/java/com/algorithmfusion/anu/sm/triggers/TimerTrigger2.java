package com.algorithmfusion.anu.sm.triggers;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.generic.impl.TextStreamWriterTextObserver;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.TransitionObserver;
import com.algorithmfusion.anu.sm.observers.timer.TimerDisposeTransitionObserver;
import com.algorithmfusion.anu.sm.observers.timer.TimerPrepareTransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TimerTrigger2 extends TrigableStateMachineTransition {

	private final String timerIntervalMessage;

	private final TransitionObserver prepare;
	private final TransitionObserver dispose;
	
	public TimerTrigger2(
			StateMachine stateMachine,
			Transition transition,
			TextStreamWriter writer,
			long interval,
			int ticks,
			String message) {
		super(stateMachine, transition);
		this.timerIntervalMessage = "Timer(" + interval + ")ms";
		TextStreamWriterTextObserver textObserver = new TextStreamWriterTextObserver(writer);
		TimerPrepareTransitionObserver timerPrepareTransitionObserver = new TimerPrepareTransitionObserver(textObserver, this, interval, ticks, message, timerIntervalMessage);
		this.prepare = timerPrepareTransitionObserver;
		this.dispose = new TimerDisposeTransitionObserver(textObserver, timerPrepareTransitionObserver, timerIntervalMessage);
	}

	public TransitionObserver getPrepare() {
		return prepare;
	}

	public TransitionObserver getDispose() {
		return dispose;
	}
}