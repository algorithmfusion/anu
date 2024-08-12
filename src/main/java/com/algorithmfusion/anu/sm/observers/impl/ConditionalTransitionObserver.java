package com.algorithmfusion.anu.sm.observers.impl;

import java.util.function.Predicate;

import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

/**
 * 
 * @author Hallo Khaznadar
 */
public class ConditionalTransitionObserver implements TransitionObserver {

	private final TransitionObserver transitionObserver;
	private final Predicate<Transition> predicate;
	
	public ConditionalTransitionObserver(TransitionObserver transitionObserver, Predicate<Transition> predicate) {
		this.transitionObserver = transitionObserver;
		this.predicate = predicate;
	}
	
	@Override
	public void notify(Transition transition) {
		if(predicate.test(transition)) {
			transitionObserver.notify(transition);
		}
	}
}