package com.algorithmfusion.anu.sm.triggers;

import java.util.Timer;
import java.util.TimerTask;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.exceptions.UnknownCurrentStateException;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TimerTrigger extends TrigableStateMachineTransition {

	private Timer timer;
	
	private final TextStreamWriter writer;
	private final long interval;
	private final int ticks;
	private final String timerId;
	private final String timerIntervalMessage;

	private final TransitionObserver prepare;
	private final TransitionObserver dispose;
	private int ticksCount;
	
	public TimerTrigger(StateMachine stateMachine, Transition transition, TextStreamWriter writer, long interval, int ticks, String timerId) {
		super(stateMachine, transition);
		this.writer = writer;
		this.interval = interval;
		this.ticks = ticks;
		this.timerId = timerId;
		this.timerIntervalMessage = "Timer[" + timerId + "](" + interval + ")ms";
		this.prepare = new Prepare();
		this.dispose = new Dispose();
	}

	public TransitionObserver getPrepare() {
		return prepare;
	}

	public TransitionObserver getDispose() {
		return dispose;
	}


	private class PerformTimeOutTask extends TimerTask {
		
		@Override
		public void run() {
			if (ticksCount++ >= ticks) {
				try {
					writer.writeLine("\n" + timerId + " invoked");
					trigger();
				} catch (UnknownCurrentStateException e) {
					e.printStackTrace();
				}
			} else {
				writer.write(".");
			}
		}
	}
	
	private class Prepare implements TransitionObserver {

		@Override
		public void notify(Transition transition) {
			timer = new Timer();
			ticksCount = 0;
			writer.writeLine("\n" + timerIntervalMessage + " started");
			timer.schedule(new PerformTimeOutTask(), 0, interval/ticks);
		}
		
	}
	
	private class Dispose implements TransitionObserver {

		@Override
		public void notify(Transition transition) {
			timer.cancel();
			writer.writeLine(timerIntervalMessage + " stoped" + "\n");
		}
		
	}
}