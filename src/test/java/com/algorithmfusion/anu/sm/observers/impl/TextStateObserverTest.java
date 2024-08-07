package com.algorithmfusion.anu.sm.observers.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.base.BaseState;

class TextStateObserverTest {

	private TextStateObserver textStateObserver;
	
	private TextStreamWriter writer;
	private String message;
	
	@BeforeEach
	void setUp() throws Exception {
		this.writer = mock(TextStreamWriter.class);
		this.message = "message";
		
		this.textStateObserver = new TextStateObserver(writer, message);
	}

	@Test
	void givenTextStateObserverWithMessageWhenNotifiedThenTextStreamWriterWriteLineUsingMessage() {
		textStateObserver.notify(new BaseState());
		
		verify(writer, times(1)).writeLine(message);
	}
}