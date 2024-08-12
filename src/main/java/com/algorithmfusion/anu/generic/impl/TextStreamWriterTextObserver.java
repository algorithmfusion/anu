package com.algorithmfusion.anu.generic.impl;

import com.algorithmfusion.anu.generic.api.TextObserver;
import com.algorithmfusion.anu.generic.api.TextStreamWriter;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextStreamWriterTextObserver implements TextObserver {

	private final TextStreamWriter writer;
	
	
	public TextStreamWriterTextObserver(TextStreamWriter writer) {
		this.writer = writer;
	}

	@Override
	public void notify(String text) {
		writer.write(text);
	}
}