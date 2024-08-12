package com.algorithmfusion.anu.sm.observers.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.base.BaseState;
import com.algorithmfusion.anu.sm.base.BaseTransition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

class ConditionalTransitionObserverTest {

	private ConditionalTransitionObserver conditionalTransitionObserver;
	
	private TransitionObserver transitionObserver;
	private Predicate<Transition> predicate;
	private Transition transition;
	
	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() throws Exception {
		this.transitionObserver = mock(TransitionObserver.class);
		this.predicate = mock(Predicate.class);
		
		this.conditionalTransitionObserver = new ConditionalTransitionObserver(transitionObserver, predicate);

		BaseState from = new BaseState();
		BaseState to = new BaseState();
		this.transition = BaseTransition.builder()
							.from(from)
							.to(to)
						.build();
	}

	@Test
	void givenConditionalTransitionObserverWithTransitionObserverAndPredicateWhenNotifiedThenGivenTransitionObservergetNotifiedIfPredicateIsTrue() {
		when(predicate.test(transition)).thenReturn(true);
		
		conditionalTransitionObserver.notify(transition);
		
		verify(transitionObserver, times(1)).notify(transition);
	}

	@Test
	void givenConditionalTransitionObserverWithTransitionObserverAndPredicateWhenNotifiedThenGivenTransitionObservergetIsNotNotifiedIfPredicateIsFalse() {
		when(predicate.test(transition)).thenReturn(false);
		
		conditionalTransitionObserver.notify(transition);
		
		verify(transitionObserver, never()).notify(transition);
	}
}