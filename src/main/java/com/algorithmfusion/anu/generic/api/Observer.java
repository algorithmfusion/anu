package com.algorithmfusion.anu.generic.api;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface Observer<T> {
	void notify(T observable);
}