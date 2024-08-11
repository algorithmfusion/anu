package com.algorithmfusion.anu.sm.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.exceptions.ConfigurationException;
import com.algorithmfusion.anu.sm.api.Transition;

class BaseStateTest {

	private BaseState stateA;
	private BaseState stateB;
	
	private BaseState stateC;
	private BaseState stateD;
	
	private Transition transition;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stateA = new BaseState();
		this.stateB = new BaseState();
		this.stateC = new BaseState();
		this.stateD = new BaseState();
	}

	@Test
	void givenBaseStateWhenAddOutgoingTransitionThatDoesNotHoldFromStateMatchingTheBaseStateThenConfigurationExceptionIsThrowen() {
		Transition transitionCtoD = BaseTransition.builder()
										.from(stateC)
										.to(stateD)
									.build();
		
		assertThrows(ConfigurationException.class, () -> stateA.addOutgoingTransition(transitionCtoD));
	}

	@Test
	void givenBaseStateWhenAddOutgoingTransitionThatHoldNoFromStateThenConfigurationExceptionIsThrowen() {
		this.transition = mock(Transition.class);
		
		assertThrows(ConfigurationException.class, () -> stateA.addOutgoingTransition(transition));
	}

	@Test
	void givenBaseStateWhenAddOutgoingTransitionThatHoldFromStateMatchingTheBaseStateThenGetOutgoingTransitionsContainesAddedTransition() {
		Transition transitionAtoB = BaseTransition.builder()
										.from(stateA)
										.to(stateB)
									.build();
		
		List<Transition> actualOutgoingTransitions = stateA.getOutgoingTransitions();
		
		assertThat(stateA.hasOutgoingTransition(transitionAtoB)).isTrue();
		assertThat(actualOutgoingTransitions).contains(transitionAtoB);
	}

	@Test
	void givenBaseStateWhenAddIncomingTransitionThatDoesNotHoldToStateMatchingTheBaseStateThenConfigurationExceptionIsThrowen() {
		Transition transitionCtoD = BaseTransition.builder()
										.from(stateC)
										.to(stateD)
									.build();
		
		assertThrows(ConfigurationException.class, () -> stateA.addIncomingTransition(transitionCtoD));
	}

	@Test
	void givenBaseStateWhenAddIncomingTransitionThatHoldNoToStateThenConfigurationExceptionIsThrowen() {
		this.transition = mock(Transition.class);
		
		assertThrows(ConfigurationException.class, () -> stateA.addIncomingTransition(transition));
	}

	@Test
	void givenBaseStateWhenAddIncomingTransitionThatHoldToStateMatchingTheBaseStateThenGetOutgoingTransitionsContainesAddedTransition() {
		Transition transitionAtoB = BaseTransition.builder()
										.from(stateA)
										.to(stateB)
									.build();
		
		List<Transition> actualIncomingTransitions = stateB.getIncomingTransitions();
		
		assertThat(stateB.hasIncomingTransition(transitionAtoB)).isTrue();
		assertThat(actualIncomingTransitions).contains(transitionAtoB);
	}
}