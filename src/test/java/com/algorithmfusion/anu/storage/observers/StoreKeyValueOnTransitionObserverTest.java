package com.algorithmfusion.anu.storage.observers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.base.BaseState;
import com.algorithmfusion.anu.sm.base.BaseTransition;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

class StoreKeyValueOnTransitionObserverTest {

	private StoreKeyValueOnTransitionObserver<?, ?> storeKeyValueOnTransitionObserver;
	
	private ObjectStorage objectStorage;
	private Object key;
	private Object value;
	private Transition transition;
	
	@BeforeEach
	void setUp() throws Exception {
		this.objectStorage = mock(ObjectStorage.class);
		this.key = new Object();
		this.value = new Object();
		
		this.storeKeyValueOnTransitionObserver = 
				new StoreKeyValueOnTransitionObserver<>(
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
	void givenObjectStoreAndKeyAndValueWhenNotifyThenKeyValueStored() {
		storeKeyValueOnTransitionObserver.notify(transition);
		
		verify(objectStorage, times(1)).store(key, value);
	}
}