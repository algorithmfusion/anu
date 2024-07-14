package com.algorithmfusion.anu.flow;

import java.util.Collection;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.StateObserver;
import com.algorithmfusion.anu.sm.observers.TransitionObserver;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * 
 * @author Hallo Khaznadar
 */
public class FlowObserversRegistry {

	private Multimap<Transition, TransitionObserver> prepareTransitionsObservers;
	private Multimap<Transition, TransitionObserver> performTransitionsObservers;
	private Multimap<Transition, TransitionObserver> disposeTransitionsObservers;
	
	private Multimap<State, StateObserver> enterStateObservers;
	private Multimap<State, StateObserver> leaveStateObservers;

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
		
		private Multimap<Transition, TransitionObserver> prepareTransitionsObservers;
		private Multimap<Transition, TransitionObserver> performTransitionsObservers;
		private Multimap<Transition, TransitionObserver> disposeTransitionsObservers;
		
		private Multimap<State, StateObserver> enterStateObservers;
		private Multimap<State, StateObserver> leaveStateObservers;
		
		public Builder() {
			this.prepareTransitionsObservers = ArrayListMultimap.create();
			this.performTransitionsObservers = ArrayListMultimap.create();
			this.disposeTransitionsObservers = ArrayListMultimap.create();
			this.enterStateObservers = ArrayListMultimap.create();
			this.leaveStateObservers = ArrayListMultimap.create();
		}
		
		public Builder addPrepareTransitionObserver(Transition transition, TransitionObserver... transitionObservers) {
			this.prepareTransitionsObservers.putAll(transition, Lists.newArrayList(transitionObservers));
			return this;
		}
		
		public Builder addPerformTransitionObserver(Transition transition, TransitionObserver... transitionObservers) {
			this.performTransitionsObservers.putAll(transition, Lists.newArrayList(transitionObservers));
			return this;
		}
		
		public Builder addDisposeTransitionObserver(Transition transition, TransitionObserver... transitionObservers) {
			this.disposeTransitionsObservers.putAll(transition, Lists.newArrayList(transitionObservers));
			return this;
		}
		
		public Builder addEnterStateObservers(State state, StateObserver... stateObservers) {
			this.enterStateObservers.putAll(state, Lists.newArrayList(stateObservers));
			return this;
		}
		
		public Builder addLeaveStateObservers(State state, StateObserver... stateObservers) {
			this.leaveStateObservers.putAll(state, Lists.newArrayList(stateObservers));
			return this;
		}
		
		public FlowObserversRegistry build() {
			return new FlowObserversRegistry(this);
		}
	}
}