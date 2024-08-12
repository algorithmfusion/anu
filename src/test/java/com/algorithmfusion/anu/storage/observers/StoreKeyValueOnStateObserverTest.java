package com.algorithmfusion.anu.storage.observers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.base.BaseState;
import com.algorithmfusion.anu.storage.api.ObjectStorage;

class StoreKeyValueOnStateObserverTest {

	private StoreKeyValueOnStateObserver<?, ?> storeKeyValueOnStateObserver;
	
	private ObjectStorage objectStorage;
	private Object key;
	private Object value;
	
	@BeforeEach
	void setUp() throws Exception {
		this.objectStorage = mock(ObjectStorage.class);
		this.key = new Object();
		this.value = new Object();
		
		this.storeKeyValueOnStateObserver = 
				new StoreKeyValueOnStateObserver<>(
					objectStorage,
					key,
					value);
	}

	@Test
	void givenObjectStoreAndKeyAndValueWhenNotifyThenKeyValueStored() {
		storeKeyValueOnStateObserver.notify(new BaseState());
		
		verify(objectStorage, times(1)).store(key, value);
	}
}