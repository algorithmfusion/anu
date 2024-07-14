package com.algorithmfusion.anu.storage.predicates;

import java.util.function.Predicate;

import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class ObjectStoredPredicate implements Predicate<Transition> {

	private ObjectStorage objectStorage;
	
	public ObjectStoredPredicate(ObjectStorage objectStorage) {
		this.objectStorage = objectStorage;
	}
	
	@Override
	public boolean test(Transition transition) {
		return objectStorage.retrieve(transition, Object.class) != null;
	}
	
}
