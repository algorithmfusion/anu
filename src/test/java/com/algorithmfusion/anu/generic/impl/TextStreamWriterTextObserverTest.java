package com.algorithmfusion.anu.generic.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;

class TextStreamWriterTextObserverTest {

	private TextStreamWriterTextObserver textStreamWriterTextObserver;
	
	private TextStreamWriter writer;
	
	@BeforeEach
	void setUp() throws Exception {
		this.writer = mock(TextStreamWriter.class);
		this.textStreamWriterTextObserver = new TextStreamWriterTextObserver(writer);
	}

	@Test
	void test() {
		String text = "text";
		textStreamWriterTextObserver.notify(text);
		
		verify(writer, times(1)).write(text);
	}

}
