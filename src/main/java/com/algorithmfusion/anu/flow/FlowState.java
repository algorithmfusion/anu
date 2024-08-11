package com.algorithmfusion.anu.flow;

import com.algorithmfusion.anu.sm.base.BaseState;

/**
 * 
 * @author Hallo Khaznadar
 */
public class FlowState extends BaseState {

	private String id;
	private String name;
	
	protected FlowState(Builder<?> builder) {
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
		protected String id;
		protected String name;
		
		@SuppressWarnings("unchecked")
		protected T self() {
            return (T) this;
        }
		
		public T id(String id) {
			this.id = id;
			return self();
		}
		
		public T name(String name) {
			this.name = name;
			return self();
		}
		
		public FlowState build() {
			return new FlowState(this);
		}
    }
}