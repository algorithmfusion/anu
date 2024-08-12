package com.algorithmfusion.anu.generic.api;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface ParameterizedHandler<T> {
	T handle(Object... parameters);
}