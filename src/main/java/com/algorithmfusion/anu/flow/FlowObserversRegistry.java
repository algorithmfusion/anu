package com.algorithmfusion.anu.flow;

import java.util.Collection;

import com.algorithmfusion.anu.generic.api.MutableMultiValueMap;
import com.algorithmfusion.anu.generic.impl.MutableMultiValueMapImpl;
import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.StateObserver;
import com.algorithmfusion.anu.sm.observers.TransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class FlowObserversRegistry {

	private MutableMultiValueMap<Transition, TransitionObserver> prepareTransitionsObservers;
	private MutableMultiValueMap<Transition, TransitionObserver> performTransitionsObservers;
	private MutableMultiValueMap<Transition, TransitionObserver> disposeTransitionsObservers;
	
	private MutableMultiValueMap<State, StateObserver> enterStateObservers;
	private MutableMultiValueMap<State, StateObserver> leaveStateObservers;

	private FlowObserversRegistry (Builder builder) {
		this.prepareTransitionsObservers = builder.prepareTransitionsObservers;
		this.performTransitionsObservers = builder.performTransitionsObservers;
		this.disposeTransitionsObservers = builder.disposeTransitionsObservers;
		this.enterStateObservers = builder.enterStateObservers;
		this.leaveStateObservers = builder.leaveStateObservers;
	}
	
	public Collection<TransitionObserver> getPrepareTransitionsObservers(Transition transition) {
		return prepareTransitionsObservers.get(transition);
	}
	
	public Collection<TransitionObserver> getPerformTransitionObservers(Transition transition) {
		return performTransitionsObservers.get(transition);
	}
	
	public Collection<TransitionObserver> getDisposeTransitionsObservers(Transition transition) {
		return disposeTransitionsObservers.get(transition);
	}
	
	public Collection<StateObserver> getEnterStateObservers(State state) {
		return enterStateObservers.get(state);
	}
	
	public Collection<StateObserver> getLeaveStateObservers(State state) {
		return leaveStateObservers.get(state);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private MutableMultiValueMap<Transition, TransitionObserver> prepareTransitionsObservers;
		private MutableMultiValueMap<Transition, TransitionObserver> performTransitionsObservers;
		private MutableMultiValueMap<Transition, TransitionObserver> disposeTransitionsObservers;
		
		private MutableMultiValueMap<State, StateObserver> enterStateObservers;
		private MutableMultiValueMap<State, StateObserver> leaveStateObservers;
		
		public Builder() {
			this.prepareTransitionsObservers = new MutableMultiValueMapImpl<>();
			this.performTransitionsObservers = new MutableMultiValueMapImpl<>();
			this.disposeTransitionsObservers = new MutableMultiValueMapImpl<>();
			this.enterStateObservers = new MutableMultiValueMapImpl<>();
			this.leaveStateObservers = new MutableMultiValueMapImpl<>();
		}
		
		public Builder addPrepareTransitionObserver(Transition transition, TransitionObserver... transitionObservers) {
			this.prepareTransitionsObservers.putAll(transition, transitionObservers);
			return this;
		}
		
		public Builder addPerformTransitionObserver(Transition transition, TransitionObserver... transitionObservers) {
			this.performTransitionsObservers.putAll(transition, transitionObservers);
			return this;
		}
		
		public Builder addDisposeTransitionObserver(Transition transition, TransitionObserver... transitionObservers) {
			this.disposeTransitionsObservers.putAll(transition, transitionObservers);
			return this;
		}
		
		public Builder addEnterStateObservers(State state, StateObserver... stateObservers) {
			this.enterStateObservers.putAll(state, stateObservers);
			return this;
		}
		
		public Builder addLeaveStateObservers(State state, StateObserver... stateObservers) {
			this.leaveStateObservers.putAll(state, stateObservers);
			return this;
		}
		
		public FlowObserversRegistry build() {
			return new FlowObserversRegistry(this);
		}
	}
}