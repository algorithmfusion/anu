package com.algorithmfusion.anu.flow;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.api.State;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.observers.api.StateObserver;
import com.algorithmfusion.anu.sm.observers.api.TransitionObserver;

class FlowObserverTest {

	private FlowObserver flowObserver;
	
	private FlowObserversRegistry flowObserversRegistry;
	private State stateA;
	private State stateB;
	private Transition transitionAtoB;
	private Transition transitionBtoC;
	
	private TransitionObserver disposeTransitionObserver;
	private StateObserver leaveStateObserver;
	private TransitionObserver performTransitionObserver;
	private StateObserver enterStateObserver;
	private TransitionObserver prepareTransitionsObserver;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stateA = mock(State.class);
		this.stateB = mock(State.class);
		this.transitionAtoB = mock(Transition.class);
		this.transitionBtoC = mock(Transition.class);
		this.flowObserversRegistry = mock(FlowObserversRegistry.class);
		this.disposeTransitionObserver = mock(TransitionObserver.class);
		this.leaveStateObserver = mock(StateObserver.class);
		this.performTransitionObserver = mock(TransitionObserver.class);
		this.enterStateObserver = mock(StateObserver.class);
		this.prepareTransitionsObserver = mock(TransitionObserver.class);
		this.flowObserver = new FlowObserver();
		
		this.flowObserver.setFlowObserversRegistry(flowObserversRegistry);
	}

	@Test
	void givenFlowObserverWhenRegisteredDisposeTransitionObserverAndLeaveStateObserverInvokingLeavingStateNotificationThenBothDisposeTransitionObserversAndLeaveStateObserversAreNotified() {
		when(stateA.getOutgoingTransitions()).thenReturn(List.of(transitionAtoB));
		when(flowObserversRegistry.getDisposeTransitionsObservers(transitionAtoB)).thenReturn(List.of(disposeTransitionObserver));
		when(flowObserversRegistry.getLeaveStateObservers(stateA)).thenReturn(List.of(leaveStateObserver));
		
		this.flowObserver.leavingStateNotification(stateA);
		
		verify(stateA, times(1)).getOutgoingTransitions();
		verify(flowObserversRegistry, times(1)).getDisposeTransitionsObservers(transitionAtoB);
		verify(disposeTransitionObserver, times(1)).notify(transitionAtoB);
		verify(leaveStateObserver, times(1)).notify(stateA);
	}

	@Test
	void givenFlowObserverWhenNoObserversAreRegisteredInvokingLeavingStateNotificationThenNothingNotified() {
		when(stateA.getOutgoingTransitions()).thenReturn(List.of(transitionAtoB));
		when(flowObserversRegistry.getDisposeTransitionsObservers(transitionAtoB)).thenReturn(null);
		when(flowObserversRegistry.getLeaveStateObservers(stateA)).thenReturn(null);
		this.flowObserver.leavingStateNotification(stateA);
		
		verify(stateA, times(1)).getOutgoingTransitions();
		verify(flowObserversRegistry, times(1)).getDisposeTransitionsObservers(transitionAtoB);
	}

	@Test
	void givenFlowObserverWhenRegisteredPerformTransitionObserverInvokingPerformingTransitionNotificationThenPerformTransitionObserversAreNotified() {
		when(flowObserversRegistry.getPerformTransitionObservers(transitionAtoB)).thenReturn(List.of(performTransitionObserver));
		
		this.flowObserver.performingTransitionNotification(transitionAtoB);
		
		verify(flowObserversRegistry, times(1)).getPerformTransitionObservers(transitionAtoB);
		verify(performTransitionObserver, times(1)).notify(transitionAtoB);
	}

	@Test
	void givenFlowObserverWhenRegisteredEnterStateObserverAndPrepareTransitionsObserverInvokingenteredStateNotificationThenBothEnterStateObserverAndPrepareTransitionsObserverAreNotified() {
		when(stateB.getOutgoingTransitions()).thenReturn(List.of(transitionBtoC));
		when(flowObserversRegistry.getEnterStateObservers(stateB)).thenReturn(List.of(enterStateObserver));
		when(flowObserversRegistry.getPrepareTransitionsObservers(transitionBtoC)).thenReturn(List.of(prepareTransitionsObserver));
		
		this.flowObserver.enteredStateNotification(stateB);
		
		verify(flowObserversRegistry, times(1)).getEnterStateObservers(stateB);
		verify(flowObserversRegistry, times(1)).getPrepareTransitionsObservers(transitionBtoC);
		
		verify(enterStateObserver, times(1)).notify(stateB);
	}
}