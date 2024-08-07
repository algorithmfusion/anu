package com.algorithmfusion.anu.storage.triggers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.base.BaseState;
import com.algorithmfusion.anu.storage.api.ObjectStorage;
import com.algorithmfusion.anu.storage.impl.InMemoryObjectStorage;

class OnKeysAvailabilityStateObserverTriggerTest {

	private OnKeysAvailabilityStateObserverTrigger onKeysAvailabilityStateObserverTrigger;
	
	private Trigable trigable;
	private ObjectStorage objectStorage;
	private List<Object> keys;
	private State state;
	
	@BeforeEach
	void setUp() throws Exception {
		this.trigable = mock(Trigable.class);
		this.objectStorage = new InMemoryObjectStorage();
		this.keys = Arrays.asList("k1", "k2");
		this.onKeysAvailabilityStateObserverTrigger = 
			new OnKeysAvailabilityStateObserverTrigger(
				trigable,
				objectStorage,
				keys);
		
		this.state = new BaseState();
	}

	@Test
	void givenKeysAvailableInStoreWhenStateObserverGetNotifiedThenTrigableTriggers() throws InterruptedException {
		keys.forEach(key -> objectStorage.store(key, ""));
		
		onKeysAvailabilityStateObserverTrigger.notify(state);
		Thread.sleep(100);
		
		verify(trigable, times(1)).trigger();
	}

	@Test
	void givenKeysNotAvailableInStoreWhenStateObserverGetNotifiedThenTrigableDoesntTriggers() throws InterruptedException {
		onKeysAvailabilityStateObserverTrigger.notify(state);
		
		verify(trigable, never()).trigger();
	}
}