package com.algorithmfusion.anu.sm.base;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;

/**
 * 
 * @author Hallo Khaznadar
 */
public class BaseTransition implements Transition {
	
	private final State from;
	private final State to;

	protected BaseTransition(Builder<?> builder) {
		this.from = builder.from;
		this.to = builder.to;
		addTransitions();
	}
	
	private void addTransitions() {
		this.from.addOutgoingTransition(this);
		this.to.addIncomingTransition(this);
	}
	
	@Override
	public State getFrom() {
		return from;
	}
	
	@Override
	public State getTo() {
		return to;
	}
	
	public static Builder<?> builder() {
		return new Builder<>();
	}
	
    public static class Builder<T extends Builder<?>> {

		protected State from;
		protected State to;

        @SuppressWarnings("unchecked")
		protected T self() {
            return (T) this;
        }

        public T from(State from) {
            this.from = from;
            return self();
        }

        public T to(State to) {
            this.to = to;
            return self();
        }

        public BaseTransition build() {
            return new BaseTransition(this);
        }
    }
}