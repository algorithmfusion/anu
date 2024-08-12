package com.algorithmfusion.anu.flow.observers;

import java.util.TimerTask;

import com.algorithmfusion.anu.generic.api.TextObserver;
import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.exceptions.UnknownCurrentStateException;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextObserverTriggeringTimeoutTask extends TimerTask {

	private final TextObserver textObserver;
	private final Trigable trigable;
	private final int ticks;
	private final String message;
	private int ticksCount;
	
	public TextObserverTriggeringTimeoutTask(TextObserver textObserver, Trigable trigable, int ticks, String message) {
		this.textObserver = textObserver;
		this.trigable = trigable;
		this.ticks = ticks;
		this.message = message;
		this.ticksCount = 0;
	}

	@Override
	public void run() {
		if (ticksCount++ >= ticks) {
			try {
				textObserver.notify("\n" + message + " triggered\n");
				trigable.trigger();
			} catch (UnknownCurrentStateException e) {
				e.printStackTrace();
			}
		} else {
			textObserver.notify(".");
		}
	}
}