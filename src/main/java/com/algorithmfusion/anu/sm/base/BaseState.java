package com.algorithmfusion.anu.sm.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algorithmfusion.anu.generic.exceptions.ConfigurationException;
import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;

/**
 * 
 * @author Hallo Khaznadar
 */
public class BaseState implements State {
	
	private final List<Transition> outgoingTransitions;
	private final List<Transition> incomingTransitions;

	public BaseState() {
		this.outgoingTransitions = new ArrayList<>();
		this.incomingTransitions = new ArrayList<>();
	}
	
	@Override
	public void addOutgoingTransition(Transition outgoing) {
		State from = outgoing.getFrom();
		if( (from != null) && (from.equals(this)) ) {
			outgoingTransitions.add(outgoing);
		} else {
			throw new ConfigurationException(
					"given outgoing from(" + from + ") state does not match this(" + this + ") state");
		}
	}
	
	@Override
	public void addIncomingTransition(Transition incoming) {
		State to = incoming.getTo();
		if( (to != null) && (to.equals(this)) ) {
			incomingTransitions.add(incoming);
		} else {
			throw new ConfigurationException(
					"given incoming to(" + to + ") state does not match this(" + this + ") state");
		}
	}
	
	@Override
	public List<Transition> getOutgoingTransitions() {
		return Collections.unmodifiableList(outgoingTransitions);
	}
	
	@Override
	public List<Transition> getIncomingTransitions() {
		return Collections.unmodifiableList(incomingTransitions);
	}
	
	@Override
	public boolean hasOutgoingTransition(Transition outgoing) {
		return outgoingTransitions.contains(outgoing);
	}
	
	@Override
	public boolean hasIncomingTransition(Transition incoming) {
		return incomingTransitions.contains(incoming);
	}
}
