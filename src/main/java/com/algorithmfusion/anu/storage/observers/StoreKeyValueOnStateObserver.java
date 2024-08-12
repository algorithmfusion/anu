package com.algorithmfusion.anu.storage.observers;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.observers.api.StateObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class StoreKeyValueOnStateObserver<KEY, VALUE> implements StateObserver {
	
	private final ObjectStorage objectStorage;
	private final KEY key;
	private final VALUE value;

	public StoreKeyValueOnStateObserver(
			ObjectStorage objectStorage,
			KEY key,
			VALUE value) {
		this.objectStorage = objectStorage;
		this.key = key;
		this.value = value;
	}

	@Override
	public void notify(State state) {
		objectStorage.store(key, value);
	}
}