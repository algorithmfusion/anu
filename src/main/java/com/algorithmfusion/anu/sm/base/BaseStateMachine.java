package com.algorithmfusion.anu.sm.base;

import com.algorithmfusion.anu.generic.exceptions.ConfigurationException;
import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.exceptions.UnknownCurrentStateException;

/**
 * 
 * @author Hallo Khaznadar
 */
public abstract class BaseStateMachine implements StateMachine {

	private State current;
	
	@Override
	public synchronized void startingState(State state) {
		enterState(state);
	}

	@Override
	public State getCurrent() {
		return current;
	}

	@Override
	public synchronized void transition(Transition transition) {
		validateTransitionOnState(transition, current);
		State from = transition.getFrom();
		State to = transition.getTo();
		
		leavingState(from);
		performingTransition(transition);
		enterState(to);
	}
	
	@Override
	public void enterState(State state) {
		this.current = state;
	}
	
	private void validateTransitionOnState(Transition transition, State state) throws UnknownCurrentStateException {
		if (state != null) {
			if (!state.hasOutgoingTransition(transition)) {
				throw new ConfigurationException(
						"Current(" + state + ") state does not have such outgoing transition(" + transition + ")");
			} else if(!current.equals(transition.getFrom())) {
				throw new ConfigurationException(
						"Current(" + state + ") state does not match transition(" + transition + ") from(" + transition.getFrom() + ") state");
			}
		} else {
			throw new UnknownCurrentStateException("Current state can not be null");
		}
	}
}