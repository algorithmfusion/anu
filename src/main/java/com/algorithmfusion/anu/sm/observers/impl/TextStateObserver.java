package com.algorithmfusion.anu.sm.observers.impl;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.observers.api.StateObserver;

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
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public void notify(State state) {
		writer.writeLine(getMessage());
	}
}