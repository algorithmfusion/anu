package com.algorithmfusion.anu.generic.impl;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextStreamWriterFactory {

	private static final TextStreamWriter writer = new TextStreamWriterImpl(System.out);
	
	private TextStreamWriterFactory() {}
	
	public static TextStreamWriter getTextStreamWriter() {
		return writer;
	}
}
