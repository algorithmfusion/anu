package com.algorithmfusion.anu.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.observers.api.StateObserver;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

class FlowTest {

	private Flow flow;
	
	String flow_id = "flow_id";
	String flow_name = "flow_name";
	
	private FlowObserver flowObserver;
	private FlowObserversRegistry flowObserversRegistry;
	
	private String stateA_id = "stateA_id";
	private String stateA_name = "stateA_name";
	private FlowState stateA;
	
	private String stateB_id = "stateB_id";
	private String stateB_name = "stateB_name";
	private FlowState stateB;
	
	private String transitionAtoB_id = "transitionAtoB_id";
	private String transitionAtoB_name = "transitionAtoB_name";
	private FlowTransition transitionAtoB;
	
	private TransitionObserver prepareTransitionObserver;
	private TransitionObserver performTransitionObserver;
	private TransitionObserver disposeTransitionObserver;
	private StateObserver enterStateObserver;
	private StateObserver leaveStateObserver;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stateA = FlowState.builder()
				.id(stateA_id)
				.name(stateA_name)
			.build();
		this.stateB = FlowState.builder()
				.id(stateB_id)
				.name(stateB_name)
			.build();
		this.transitionAtoB = FlowTransition.builder()
				.id(transitionAtoB_id)
				.name(transitionAtoB_name)
				.from(stateA)
				.to(stateB)
			.build();
		
		this.flowObserver = new FlowObserver();
		
		this.flow = Flow.builder()
				.id(flow_id)
				.name(flow_name)
				.stateMachineObserver(flowObserver)
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
		
		this.flowObserver.setFlowObserversRegistry(flowObserversRegistry);
	}

	@Test
	void givenConfiguredFlowWhenStartingStateFollowedByOutgoingTransitionOnItThenTheExpectedObserversGetNotified() {
		flow.startingState(stateA);
		assertThat(flow.getCurrent()).isEqualTo(stateA);
		assertThat(((FlowState)flow.getCurrent()).getId()).isEqualTo(stateA.getId());
		assertThat(((FlowState)flow.getCurrent()).getName()).isEqualTo(stateA.getName());
		assertThat(flow.getCurrent().getOutgoingTransitions()).containsExactly(transitionAtoB);
		assertThat(((FlowTransition)flow.getCurrent().getOutgoingTransitions().get(0)).getId()).isEqualTo(transitionAtoB.getId());
		assertThat(((FlowTransition)flow.getCurrent().getOutgoingTransitions().get(0)).getName()).isEqualTo(transitionAtoB.getName());
		
		flow.transition(transitionAtoB);
		
		assertThat(flow.getId()).isEqualTo(flow_id);
		assertThat(flow.getName()).isEqualTo(flow_name);
		assertThat(flow.getCurrent()).isEqualTo(stateB);
		
		verify(prepareTransitionObserver, times(1)).notify(transitionAtoB);
		verify(performTransitionObserver, times(1)).notify(transitionAtoB);
		verify(disposeTransitionObserver, times(1)).notify(transitionAtoB);

		verify(enterStateObserver, times(1)).notify(stateB);
		verify(leaveStateObserver, times(1)).notify(stateA);
	}
}