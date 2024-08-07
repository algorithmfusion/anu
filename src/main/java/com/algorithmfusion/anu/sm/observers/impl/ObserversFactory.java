package com.algorithmfusion.anu.sm.observers;

import static com.algorithmfusion.anu.generic.impl.TextStreamWriterFactory.getTextStreamWriter;

/**
 * 
 * @author Hallo Khaznadar
 */
public class ObserversFactory {

	private ObserversFactory() {}
	
	public static TextStateObserver createTextStateObserver(String text) {
		return new TextStateObserver(getTextStreamWriter(), text);
	}
	
	public static TextTransitionObserver createTextTransitionObserver(String text) {
		return new TextTransitionObserver(getTextStreamWriter(), text);
	}
}