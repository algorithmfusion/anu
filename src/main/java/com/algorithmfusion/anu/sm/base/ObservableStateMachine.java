package com.algorithmfusion.anu.sm.base;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.StateMachineObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class ObservableStateMachine extends BaseStateMachine {
	
	private final StateMachineObserver stateMachineObserver;
	
	public ObservableStateMachine(StateMachineObserver stateMachineObserver) {
		this.stateMachineObserver = stateMachineObserver;
	}
	
	@Override
	public void leavingState(State state) {
		stateMachineObserver.leavingStateNotification(state);
	}

	@Override
	public void performingTransition(Transition transition) {
		stateMachineObserver.performingTransitionNotification(transition);
	}

	@Override
	public void enterState(State state) {
		super.enterState(state);
		stateMachineObserver.enteredStateNotification(state);
	}
}