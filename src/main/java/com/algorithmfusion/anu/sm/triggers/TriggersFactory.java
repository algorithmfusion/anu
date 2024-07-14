package com.algorithmfusion.anu.sm.triggers;

import static com.algorithmfusion.anu.generic.impl.TextStreamWriterFactory.getTextStreamWriter;

import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TriggersFactory {

	private TriggersFactory() {}
	
	public static TimerTrigger createTimerTrigger(StateMachine stateMachine, Transition transition, long interval, int ticks, String message) {
		return new TimerTrigger(stateMachine, transition, getTextStreamWriter(), interval, ticks, message);
	}
	
	public static TimerTrigger2 createTimerTrigger2(StateMachine stateMachine, Transition transition, long interval, int ticks, String message) {
		return new TimerTrigger2(stateMachine, transition, getTextStreamWriter(), interval, ticks, message);
	}
}