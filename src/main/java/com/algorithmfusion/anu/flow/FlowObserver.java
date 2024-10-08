package com.algorithmfusion.anu.flow;

import java.util.Collection;
import java.util.List;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.StateMachineObserver;
import com.algorithmfusion.anu.sm.observers.api.StateObserver;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class FlowObserver implements StateMachineObserver {

	private FlowObserversRegistry flowObserversRegistry;

	public void setFlowObserversRegistry(FlowObserversRegistry flowObserversRegistry) {
		this.flowObserversRegistry = flowObserversRegistry;
	}
	
	@Override
	public void leavingStateNotification(State state) {
		notifyDisposeTransitionObservers(state.getOutgoingTransitions());
		notifyLeaveStateObservers(state);
	}

	@Override
	public void performingTransitionNotification(Transition transition) {
		notifyPerformTransitionObservers(transition);
	}
	
	@Override
	public void enteredStateNotification(State state) {
		notifyEnterStateObservers(state);
		notifyPrepareTransitionObservers(state.getOutgoingTransitions());
	}
	
	private void notifyDisposeTransitionObservers(List<Transition> transitions) {
		for (Transition transition : transitions) {
			notifyTransitionObservers(flowObserversRegistry.getDisposeTransitionsObservers(transition), transition);
		}
	}
	
	private void notifyPerformTransitionObservers(Transition transition) {
		notifyTransitionObservers(flowObserversRegistry.getPerformTransitionObservers(transition), transition);
	}
	
	private void notifyPrepareTransitionObservers(List<Transition> transitions) {
		for (Transition transition : transitions) {
			notifyTransitionObservers(flowObserversRegistry.getPrepareTransitionsObservers(transition), transition);
		}
	}
	
	private void notifyTransitionObservers(Collection<TransitionObserver> transitionObservers, Transition transition) {
		if (transitionObservers != null) {
			for (TransitionObserver transitionObserver : transitionObservers) {
				transitionObserver.notify(transition);
			}
		}
	}

	private void notifyLeaveStateObservers(State state) {
		Collection<StateObserver> leaveStateObservers = flowObserversRegistry.getLeaveStateObservers(state);
		notifyStateObservers(leaveStateObservers, state);
	}

	private void notifyEnterStateObservers(State state) {
		Collection<StateObserver> enterStateObservers = flowObserversRegistry.getEnterStateObservers(state);
		notifyStateObservers(enterStateObservers, state);
	}
	
	private void notifyStateObservers(Collection<StateObserver> stateObservers, State state) {
		if (stateObservers != null) {
			for (StateObserver leaveStateObserver : stateObservers) {
				leaveStateObserver.notify(state);
			}
		}
	}
}
