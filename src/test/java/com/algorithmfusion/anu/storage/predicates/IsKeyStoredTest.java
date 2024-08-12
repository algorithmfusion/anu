package com.algorithmfusion.anu.storage.predicates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.storage.api.ObjectStorage;
import com.algorithmfusion.anu.storage.impl.InMemoryObjectStorage;

class IsKeyStoredTest {

	private IsKeyStored<Object> isKeyStored;
	
	private ObjectStorage objectStorage;
	
	@BeforeEach
	void setUp() throws Exception {
		this.objectStorage = new InMemoryObjectStorage();
		this.isKeyStored = new IsKeyStored<Object>(objectStorage);
	}

	@Test
	void givenKeyStoredWhenTestPassingKeyThenTrueIsReturned() {
		Object key = new Object();
		objectStorage.store(key, "");
		
		boolean actual = isKeyStored.test(key);
		
		assertThat(actual).isTrue();
	}

	@Test
	void givenKeyNotStoredWhenTestPassingKeyThenFalseIsReturned() {
		Object key = new Object();
		
		boolean actual = isKeyStored.test(key);
		
		assertThat(actual).isFalse();
	}
}