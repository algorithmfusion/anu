package com.algorithmfusion.anu.generic.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;

class TextStreamWriterImplTest {

	private TextStreamWriter textStreamWriter;
	
	private PrintStream printStream;
	
	@BeforeEach
	void setUp() throws Exception {
		this.printStream = mock(PrintStream.class);
		this.textStreamWriter = new TextStreamWriterImpl(printStream);
	}
	
	@Test
	void givenTextStreamWriterImplWhenWriteTheUnderlyingPrintStreamPrintIsused() {
		String text = "text";
		
		textStreamWriter.write(text);
		
		verify(printStream, times(1)).print(text);
	}
	
	@Test
	void givenTextStreamWriterImplWhenWriteTheUnderlyingPrintStreamPrintlnIsused() {
		String text = "text";
		
		textStreamWriter.writeLine(text);
		
		verify(printStream, times(1)).println(text);
	}
}
