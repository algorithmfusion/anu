package com.algorithmfusion.anu.storage.predicates;

import java.util.function.Predicate;

import com.algorithmfusion.anu.storage.api.ObjectStorage;

/**
 * 
 * @author Hallo Khaznadar
 */
public class IsKeyStored<KEY> implements Predicate<KEY> {

	private final ObjectStorage objectStorage;
	
	public IsKeyStored(ObjectStorage objectStorage) {
		this.objectStorage = objectStorage;
	}

	@Override
	public boolean test(KEY key) {
		return objectStorage.retrieve(key, Object.class) != null;
	}
}