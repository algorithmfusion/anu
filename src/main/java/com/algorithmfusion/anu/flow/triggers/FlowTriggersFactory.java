package com.algorithmfusion.anu.flow.triggers;

import static com.algorithmfusion.anu.generic.impl.TextStreamWriterFactory.getTextStreamWriter;

import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;

public class FlowTriggersFactory {

	private FlowTriggersFactory() {}
	
	public static FlowTimerTrigger createFlowTimerTrigger(StateMachine stateMachine, Transition transition, long interval, int ticks, String timerId) {
		return new FlowTimerTrigger(stateMachine, transition, getTextStreamWriter(), interval, ticks, timerId);
	}
}
