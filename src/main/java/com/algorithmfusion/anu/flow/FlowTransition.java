package com.algorithmfusion.anu.flow;

import com.algorithmfusion.anu.sm.base.BaseTransition;

/**
 * 
 * @author Hallo Khaznadar
 */
public class FlowTransition extends BaseTransition {

	private String id;
	private String name;
	
	public FlowTransition(Builder<?> builder) {
		super(builder);
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
	
	public static class Builder<T extends Builder<T>> extends BaseTransition.Builder<T> {
		protected String id;
		protected String name;
		
		public T id(String id) {
            this.id = id;
            return self();
        }

        public T name(String name) {
            this.name = name;
            return self();
        }

        @Override
        public FlowTransition build() {
            return new FlowTransition(this);
        }
	}
}