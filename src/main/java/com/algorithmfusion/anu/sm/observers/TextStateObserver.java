package com.algorithmfusion.anu.sm.observers;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.api.State;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextStateObserver implements StateObserver {

	private final TextStreamWriter writer;
	private final String message;
	
	public TextStateObserver(TextStreamWriter writer, String text) {
		this.writer = writer;
		this.message = text;
	}
	
	@Override
	public void notify(State state) {
		writer.writeLine(message);
	}
}