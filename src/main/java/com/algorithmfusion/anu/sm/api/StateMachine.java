package com.algorithmfusion.anu.sm.api;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface StateMachine {
	
	void startingState(State state);

	State getCurrent();
	
	void transition(Transition transition);
	
	void leavingState(State state);
	
	void performingTransition(Transition transition);
	
	void enterState(State state);
}