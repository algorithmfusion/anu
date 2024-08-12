package com.algorithmfusion.anu.sm.observers.api;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface StateMachineObserver {

	void leavingStateNotification(State state);
	
	void performingTransitionNotification(Transition transition);
	
	void enteredStateNotification(State state);
}
