package com.algorithmfusion.anu.sm.exceptions;

import com.algorithmfusion.anu.generic.exceptions.ConfigurationException;

/**
 * 
 * @author Hallo Khaznadar
 */
@SuppressWarnings("serial")
public class UnknownCurrentStateException extends ConfigurationException {
	
	public UnknownCurrentStateException(String string) {
		super(string);
	}
}