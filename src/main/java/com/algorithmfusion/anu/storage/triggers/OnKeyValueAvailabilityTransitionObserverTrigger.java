package com.algorithmfusion.anu.storage.triggers;

import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class OnKeyValueAvailabilityTransitionObserverTrigger<KEY, VALUE> implements TransitionObserver {
	
	private final Trigable trigable;
	private final ObjectStorage objectStorage;
	private final KEY key;
	private final VALUE value;
	
	public OnKeyValueAvailabilityTransitionObserverTrigger(
			Trigable trigable,
			ObjectStorage objectStorage,
			KEY key,
			VALUE value) {
		this.trigable = trigable;
		this.objectStorage = objectStorage;
		this.key = key;
		this.value = value;
	}

	@Override
	public void notify(Transition observable) {
		if (value.equals(objectStorage.retrieve(key, value.getClass()))) {
			trigable.trigger();
		}
	}
}