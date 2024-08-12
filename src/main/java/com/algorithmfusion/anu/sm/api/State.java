package com.algorithmfusion.anu.sm.api;

import java.util.List;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface State {

	void addOutgoingTransition(Transition outgoing);

	void addIncomingTransition(Transition incoming);

	List<Transition> getOutgoingTransitions();

	List<Transition> getIncomingTransitions();

	boolean hasOutgoingTransition(Transition outgoing);

	boolean hasIncomingTransition(Transition incoming);
}