package com.algorithmfusion.anu.storage.triggers;

import java.util.List;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.StateObserver;
import com.algorithmfusion.anu.sm.triggers.TrigableStateMachineTransition;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class ObjectsAvailabilityOnStateTrigger extends TrigableStateMachineTransition implements StateObserver {

	ObjectStorage objectStorage;
	List<?> objectIds;
		
	public ObjectsAvailabilityOnStateTrigger(
			StateMachine stateMachine,
			Transition transition,
			ObjectStorage objectStorage,
			List<?> objectIds) {
		super(stateMachine, transition);
		this.objectStorage = objectStorage;
		this.objectIds = objectIds;
	}


	@Override
	public void notify(State state) {
		boolean allAvailable = true;
		for (Object id : objectIds) {
			if (objectStorage.retrieve(id, Object.class) == null) {
				allAvailable = false;
				break;
			}
		}
		if (allAvailable) {
			new Thread(new TriggerPerformer(state)).start();
		}
	}

	private class TriggerPerformer implements Runnable {
		State lock;
		
		private TriggerPerformer(State state) {
			this.lock = state;
		}
		
		@Override
		public void run() {
			synchronized(lock) {
				trigger();
			}
		}
	}
}
