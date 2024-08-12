package com.algorithmfusion.anu.storage.triggers;

import java.util.List;

import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.observers.api.StateObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class OnKeysAvailabilityStateObserverTrigger implements StateObserver {

	private final Trigable trigable;
	private final ObjectStorage objectStorage;
	private final List<?> keys;
	
	public OnKeysAvailabilityStateObserverTrigger(
			Trigable trigable,
			ObjectStorage objectStorage,
			List<?> keys) {
		this.trigable = trigable;
		this.objectStorage = objectStorage;
		this.keys = keys;
	}


	@Override
	public void notify(State state) {
		for (Object id : keys) {
			if (objectStorage.retrieve(id, Object.class) == null) {
				return;
			}
		}
		new Thread(new TriggerPerformer(state)).start();
	}

	private class TriggerPerformer implements Runnable {
		State lock;
		
		private TriggerPerformer(State state) {
			this.lock = state;
		}
		
		@Override
		public void run() {
			synchronized(lock) {
				trigable.trigger();
			}
		}
	}
}