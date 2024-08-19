package com.algorithmfusion.anu.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.StateObserver;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

class FlowObserversRegistryTest {

	private FlowObserversRegistry flowObserversRegistry;
	
	private State stateA;
	private State stateB;
	private Transition transitionAtoB;
	
	private TransitionObserver prepareTransitionObserver;
	private TransitionObserver performTransitionObserver;
	private TransitionObserver disposeTransitionObserver;
	private StateObserver enterStateObserver;
	private StateObserver leaveStateObserver;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stateA = FlowState.builder()
						.id("stateA_id")
						.name("stateA_name")
					.build();
		this.stateB = FlowState.builder()
						.id("stateB_id")
						.name("stateB_name")
					.build();
		this.transitionAtoB = FlowTransition.builder()
						.id("transitionAtoB_id")
						.name("transitionAtoB_name")
						.from(stateA)
						.to(stateB)
					.build();
		
		this.prepareTransitionObserver = mock(TransitionObserver.class);
		this.performTransitionObserver = mock(TransitionObserver.class);
		this.disposeTransitionObserver = mock(TransitionObserver.class);
		this.enterStateObserver = mock(StateObserver.class);
		this.leaveStateObserver = mock(StateObserver.class);
		
		this.flowObserversRegistry = FlowObserversRegistry.builder()
			.addPrepareTransitionObserver(transitionAtoB, prepareTransitionObserver)
			.addPerformTransitionObserver(transitionAtoB, performTransitionObserver)
			.addDisposeTransitionObserver(transitionAtoB, disposeTransitionObserver)
			.addEnterStateObservers(stateB, enterStateObserver)
			.addLeaveStateObservers(stateA, leaveStateObserver)
		.build();
	}

	@Test
	void givenFlowObserversRegistryWhenAddingtheDiferentTypesOfObserversThenAllAddedObserversCanBeRetreived() {
		Collection<TransitionObserver> actualPrepareTransitionObservers = flowObserversRegistry.getPrepareTransitionsObservers(transitionAtoB);
		Collection<TransitionObserver> actualPerformTransitionObservers = flowObserversRegistry.getPerformTransitionObservers(transitionAtoB);
		Collection<TransitionObserver> actualDisposeTransitionsObservers = flowObserversRegistry.getDisposeTransitionsObservers(transitionAtoB);
		Collection<StateObserver> actualEnterStateObservers = flowObserversRegistry.getEnterStateObservers(stateB);
		Collection<StateObserver> actualLeaveStateObservers = flowObserversRegistry.getLeaveStateObservers(stateA);
		
		assertThat(actualPrepareTransitionObservers).containsExactly(prepareTransitionObserver);
		assertThat(actualPerformTransitionObservers).containsExactly(performTransitionObserver);
		assertThat(actualDisposeTransitionsObservers).containsExactly(disposeTransitionObserver);
		assertThat(actualEnterStateObservers).containsExactly(enterStateObserver);
		assertThat(actualLeaveStateObservers).containsExactly(leaveStateObserver);
	}
}