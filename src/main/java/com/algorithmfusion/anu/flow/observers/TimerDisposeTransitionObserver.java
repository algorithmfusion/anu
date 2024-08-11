package com.algorithmfusion.anu.flow.observers;

import java.util.Timer;
import java.util.function.Supplier;

import com.algorithmfusion.anu.generic.api.TextObserver;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TimerDisposeTransitionObserver implements TransitionObserver {

	private final TextObserver textObserver;
	private final Supplier<Timer> timerSupplier;
	private final String timerIntervalMessage;
	
	
	public TimerDisposeTransitionObserver(
			TextObserver textObserver,
			Supplier<Timer> timerSupplier,
			String timerIntervalMessage) {
		this.textObserver = textObserver;
		this.timerSupplier = timerSupplier;
		this.timerIntervalMessage = timerIntervalMessage;
	}


	@Override
	public void notify(Transition observable) {
		timerSupplier.get().cancel();
		textObserver.notify(timerIntervalMessage + " stoped" + "\n\n");
	}
}