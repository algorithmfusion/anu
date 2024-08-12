package com.algorithmfusion.anu.generic.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.ParameterizedHandler;

class StringToParameterizedHandlerTest {

	private StringToParameterizedHandler stringToParameterizedHandler;

	@Test
	void givenRegisteredhandlerIdWithCustomParameterizedHandlerWhenGetHandlerhandleExpectedHandlerResultIsRetrieved() {
		String handlerId = "handlerId";
		Object handlerResult = new Object();
		ParameterizedHandler<?> parameterizedHandler = new ParameterizedHandler<>() {
			@Override
			public Object handle(Object... parameters) {
				return handlerResult;
			}
		};
		stringToParameterizedHandler = new StringToParameterizedHandler();
		stringToParameterizedHandler.registerIdToHandler(handlerId, parameterizedHandler);
		
		assertThat(stringToParameterizedHandler.getHandler(handlerId).handle(new Object())).isEqualTo(handlerResult);
	}
	
	@Test
	void givenNonRegisteredHandlerIdWithWhenGetHandlerhandleExpectedHandlerResultIsNull() {
		String handlerId = "handlerId";
		stringToParameterizedHandler = new StringToParameterizedHandler();
		
		assertThat(stringToParameterizedHandler.getHandler(handlerId).handle(new Object())).isNull();
	}
}