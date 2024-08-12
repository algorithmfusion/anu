package com.algorithmfusion.anu.storage.observers;

import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class StoreKeyValueOnTransitionObserver<KEY, VALUE> implements TransitionObserver {

	private final ObjectStorage objectStorage;
	private final KEY key;
	private final VALUE value;
	
	public StoreKeyValueOnTransitionObserver(
			ObjectStorage objectStorage,
			KEY key,
			VALUE value) {
		this.objectStorage = objectStorage;
		this.key = key;
		this.value = value;
	}

	@Override
	public void notify(Transition transition) {
		objectStorage.store(key, value);
	}
}