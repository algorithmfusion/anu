package com.algorithmfusion.anu.flow.triggers;

import com.algorithmfusion.anu.flow.observers.TimerDisposeTransitionObserver;
import com.algorithmfusion.anu.flow.observers.TimerPrepareTransitionObserver;
import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.generic.impl.TextStreamWriterTextObserver;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;
import com.algorithmfusion.anu.sm.triggers.TrigableStateMachineTransition;

/**
 * 
 * @author Hallo Khaznadar
 */
public class FlowTimerTrigger extends TrigableStateMachineTransition {

	private final String timerIntervalMessage;

	private final TransitionObserver prepare;
	private final TransitionObserver dispose;
	
	public FlowTimerTrigger(
			StateMachine stateMachine,
			Transition transition,
			TextStreamWriter writer,
			long interval,
			int ticks,
			String timerId) {
		super(stateMachine, transition);
		this.timerIntervalMessage = "Timer[" + timerId + "](" + interval + ")ms";
		TextStreamWriterTextObserver textObserver = new TextStreamWriterTextObserver(writer);
		TimerPrepareTransitionObserver timerPrepareTransitionObserver = new TimerPrepareTransitionObserver(textObserver, this, interval, ticks, timerId, timerIntervalMessage);
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