package com.algorithmfusion.anu.sm.observers.timer;

import java.util.Timer;
import java.util.function.Supplier;

import com.algorithmfusion.anu.generic.api.TextObserver;
import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.TransitionObserver;
import com.algorithmfusion.anu.flow.BpmnTransition;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TimerPrepareTransitionObserver implements TransitionObserver, Supplier<Timer> {

	private final TextObserver textObserver;
	private final Trigable trigable;
	private final long interval;
	private final int ticks;
	private final String message;
	private final String timerIntervalMessage;
	
	private Timer timer;
	
	public TimerPrepareTransitionObserver(
			TextObserver textObserver,
			Trigable trigable,
			long interval,
			int ticks,
			String message,
			String timerIntervalMessage) {
		this.textObserver = textObserver;
		this.trigable = trigable;
		this.interval = interval;
		this.ticks = ticks;
		this.message = message;
		this.timerIntervalMessage = timerIntervalMessage;
	}

	@Override
	public void notify(Transition observable) {
		timer = new Timer();
		textObserver.notify("\n" + timerIntervalMessage +
				" for triggering transition(Id, Name)[" +
				((BpmnTransition) observable).getId() +
				"," +
				((BpmnTransition) observable).getName() +
				"] started\n");
		timer.schedule(new TextObserverTriggeringTimeoutTask(textObserver, trigable, ticks, message), 0, interval/ticks);
	}

	@Override
	public Timer get() {
		return timer;
	}
}
