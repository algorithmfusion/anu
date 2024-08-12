package com.algorithmfusion.anu.storage.triggers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.base.BaseState;
import com.algorithmfusion.anu.sm.base.BaseTransition;
import com.algorithmfusion.anu.storage.api.ObjectStorage;
import com.algorithmfusion.anu.storage.impl.InMemoryObjectStorage;

class OnKeyValueAvailabilityTransitionObserverTriggerTest {

	private OnKeyValueAvailabilityTransitionObserverTrigger<?, ?> onKeyValueAvailabilityTransitionObserverTrigger;
	
	private Trigable trigable;
	private ObjectStorage objectStorage;
	private Object key;
	private Object value;
	private Transition transition;
	
	@BeforeEach
	void setUp() throws Exception {
		this.trigable = mock(Trigable.class);
		this.objectStorage = new InMemoryObjectStorage();
		this.key = new Object();
		this.value = new Object();
		
		this.onKeyValueAvailabilityTransitionObserverTrigger = 
			new OnKeyValueAvailabilityTransitionObserverTrigger<>(
				trigable,
				objectStorage,
				key,
				value);
		
		BaseState from = new BaseState();
		BaseState to = new BaseState();
		this.transition = BaseTransition.builder()
							.from(from)
							.to(to)
						.build();
	}

	@Test
	void givenKeyValueAvailableInStoreWhenTransitionObserverGetNotifiedThenTrigableTriggers() {
		this.objectStorage.store(key, value);
		
		onKeyValueAvailabilityTransitionObserverTrigger.notify(transition);
		
		verify(trigable, times(1)).trigger();
	}
	
	@Test
	void givenKeyValueNotAvailableInStoreWhenTransitionObserverGetNotifiedThenTrigableDoesntTriggers() {
		onKeyValueAvailabilityTransitionObserverTrigger.notify(transition);
		
		verify(trigable, never()).trigger();
	}
}