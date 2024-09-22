package com.algorithmfusion.anu.flow.observers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.algorithmfusion.anu.generic.api.TextObserver;
import com.algorithmfusion.anu.generic.api.Trigable;
import com.algorithmfusion.anu.sm.exceptions.UnknownCurrentStateException;

class TextObserverTriggeringTimeoutTaskTest {

	private TextObserverTriggeringTimeoutTask textObserverTriggeringTimeoutTask;
	
	private TextObserver textObserver;
	private Trigable trigable;
	private int ticks;
	private String message;
	
	@BeforeEach
	void setUp() throws Exception {
		this.textObserver = mock(TextObserver.class);
		this.trigable = mock(Trigable.class);
		this.ticks = 1;
		this.message = "message";
		
		this.textObserverTriggeringTimeoutTask = new TextObserverTriggeringTimeoutTask(textObserver, trigable, ticks, message);
	}

	@Test
	void test() {
		textObserverTriggeringTimeoutTask.run();
		textObserverTriggeringTimeoutTask.run();
		
		verify(textObserver, times(1)).notify(".");
		verify(textObserver, times(1)).notify("\n" + message + " triggered\n");
		verify(trigable, times(1)).trigger();
	}
	
	@Test
	void test2() {
		String errorMessage = "error";
		Mockito.doThrow(new UnknownCurrentStateException(errorMessage)).when(trigable).trigger();
		
		textObserverTriggeringTimeoutTask.run();
		textObserverTriggeringTimeoutTask.run();
		
		verify(textObserver, times(1)).notify(".");
		verify(textObserver, times(1)).notify("\n" + message + " triggered\n");
		verify(trigable, times(1)).trigger();
		verify(textObserver, times(1)).notify(errorMessage);
		
	}

}
