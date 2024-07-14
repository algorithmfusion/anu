package com.algorithmfusion.anu.sm.triggers;

import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TrigableStateMachineTransition implements Trigable {
	
	private StateMachine stateMachine;
	private Transition transition;
	
	public TrigableStateMachineTransition(StateMachine stateMachine, Transition transition) {
		this.stateMachine = stateMachine;
		this.transition = transition;
	}

	@Override
	public void trigger() {
		stateMachine.transition(transition);
	}
}