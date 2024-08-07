package com.algorithmfusion.anu.storage.observers;

import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.TransitionObserver;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class ObjectStoreTransitionObserver<ID, OBJ> implements TransitionObserver {

	private ObjectStorage objectStorage;
	private ID objectId;
	private OBJ objectToStore;
	
	public ObjectStoreTransitionObserver(ObjectStorage objectStorage, ID objectId, OBJ objectToStore) {
		this.objectStorage = objectStorage;
		this.objectId = objectId;
		this.objectToStore = objectToStore;
	}

	@Override
	public void notify(Transition transition) {
		objectStorage.store(objectId, objectToStore);
	}

}
