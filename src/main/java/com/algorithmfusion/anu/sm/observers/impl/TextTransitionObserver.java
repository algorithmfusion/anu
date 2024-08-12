package com.algorithmfusion.anu.sm.observers.impl;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class TextTransitionObserver implements TransitionObserver {

	private final TextStreamWriter writer;
	private final String message;
	
	public TextTransitionObserver(TextStreamWriter writer, String text) {
		this.writer = writer;
		this.message = text;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public void notify(Transition transition) {
		writer.writeLine(getMessage());	
	}
}