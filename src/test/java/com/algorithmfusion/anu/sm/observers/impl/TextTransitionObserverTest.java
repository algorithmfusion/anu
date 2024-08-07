package com.algorithmfusion.anu.sm.observers.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.base.BaseState;
import com.algorithmfusion.anu.sm.base.BaseTransition;

class TextTransitionObserverTest {

	private TextTransitionObserver textTransitionObserver;
	
	private TextStreamWriter writer;
	private String message;
	private Transition transition;
	
	@BeforeEach
	void setUp() throws Exception {
		this.writer = mock(TextStreamWriter.class);
		this.message = "message";
		this.textTransitionObserver = new TextTransitionObserver(writer, message);
		
		BaseState from = new BaseState();
		BaseState to = new BaseState();
		this.transition = BaseTransition.builder()
							.from(from)
							.to(to)
						.build();
	}

	@Test
	void givenTextTransitionObserverWithMessageWhenNotifiedThenTextStreamWriterWriteLineUsingMessage() {
		textTransitionObserver.notify(transition);
		
		verify(writer, times(1)).writeLine(message);
	}
}