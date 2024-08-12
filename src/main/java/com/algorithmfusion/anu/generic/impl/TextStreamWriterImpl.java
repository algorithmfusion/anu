package com.algorithmfusion.anu.generic.impl;

import java.io.PrintStream;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextStreamWriterImpl implements TextStreamWriter {

	private final PrintStream printStream;
	
	public TextStreamWriterImpl(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void write(String text) {
		printStream.print(text);
	}

	@Override
	public void writeLine(String text) {
		printStream.println(text);
	}
}