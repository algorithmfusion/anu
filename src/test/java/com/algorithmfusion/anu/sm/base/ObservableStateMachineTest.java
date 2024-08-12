package com.algorithmfusion.anu.sm.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.exceptions.ConfigurationException;
import com.algorithmfusion.anu.sm.api.Transition;
import com.algorithmfusion.anu.sm.exceptions.UnknownCurrentStateException;
import com.algorithmfusion.anu.sm.observers.api.StateMachineObserver;

class ObservableStateMachineTest {

	private ObservableStateMachine observableStateMachine;
	
	private StateMachineObserver stateMachineObserver;
	
	private BaseState stateA;
	private BaseState stateB;	
	private BaseState stateC;
	private BaseState stateD;
	
	Transition transitionAtoB;
	Transition transitionBtoC;
	Transition transitionCtoD;
	Transition transitionDtoA;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stateMachineObserver = mock(StateMachineObserver.class);
		this.observableStateMachine = new ObservableStateMachine(stateMachineObserver);
		
		this.stateA = new BaseState();
		this.stateB = new BaseState();
		this.stateC = new BaseState();
		this.stateD = new BaseState();
		
		this.transitionAtoB = BaseTransition.builder()
								.from(stateA)
								.to(stateB)
							.build();
		this.transitionBtoC = BaseTransition.builder()
								.from(stateB)
								.to(stateC)
							.build();
		this.transitionCtoD = BaseTransition.builder()
								.from(stateC)
								.to(stateD)
							.build();
		this.transitionDtoA = BaseTransition.builder()
										.from(stateD)
										.to(stateA)
									.build();
	}

	@Test
	void test() {
		observableStateMachine.startingState(stateA);
		
		assertThat(observableStateMachine.getCurrent()).isEqualTo(stateA);
	}

	@Test
	void test2() {
		observableStateMachine.startingState(stateA);
		
		observableStateMachine.transition(transitionAtoB);
		
		assertThat(observableStateMachine.getCurrent()).isEqualTo(stateB);
		
		verify(stateMachineObserver, times(1)).leavingStateNotification(stateA);
		verify(stateMachineObserver, times(1)).performingTransitionNotification(transitionAtoB);
		verify(stateMachineObserver, times(1)).enteredStateNotification(stateB);
	}

	@Test
	void test3() {
		assertThrows(UnknownCurrentStateException.class, () -> observableStateMachine.transition(transitionAtoB));
	}
	
	@Test
	void test4() {
		observableStateMachine.startingState(stateA);
		assertThrows(ConfigurationException.class, () -> observableStateMachine.transition(transitionBtoC));
	}
	
	@Test
	void test5() {
		BaseState state = mock(BaseState.class);
		observableStateMachine.startingState(state);
		Transition transition = mock(Transition.class);
		
		when(state.hasOutgoingTransition(transition)).thenReturn(true);
		when(transition.getFrom()).thenReturn(null);
		
		assertThrows(ConfigurationException.class, () -> observableStateMachine.transition(transition));
	}
}