package com.algorithmfusion.anu.flow;

import com.algorithmfusion.anu.sm.base.ObservableStateMachine;
import com.algorithmfusion.anu.sm.observers.api.StateMachineObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class Flow extends ObservableStateMachine {

	private String id;
	private String name;
	
	private Flow(Builder<?> builder) {
		super(builder.stateMachineObserver);
		this.id = builder.id;
		this.name = builder.name;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static Builder<?> builder() {
		return new Builder<>();
	}
	
	public static class Builder<T extends Builder<?>> {
		
		private StateMachineObserver stateMachineObserver;
		private String id;
		private String name;
		
		@SuppressWarnings("unchecked")
		protected T self() {
            return (T) this;
        }
		
		public T stateMachineObserver(StateMachineObserver stateMachineObserver) {
			this.stateMachineObserver = stateMachineObserver;
			return self();
		}
		
		public T id(String id) {
			this.id = id;
			return self();
		}
		
		public T name(String name) {
			this.name = name;
			return self();
		}
		
		public Flow build() {
			return new Flow(this);
		}
	}
}