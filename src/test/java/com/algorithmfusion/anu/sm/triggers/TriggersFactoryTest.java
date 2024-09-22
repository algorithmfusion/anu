package com.algorithmfusion.anu.sm.triggers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.sm.api.StateMachine;
import com.algorithmfusion.anu.sm.api.Transition;

class TriggersFactoryTest {

	private TriggersFactory triggersFactory;
	
	private StateMachine stateMachine;
	private Transition transition;
	private long interval;
	private int ticks;
	private String timerId;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stateMachine = mock(StateMachine.class);
		this.transition = mock(Transition.class);
		this.interval = 100;
		this.ticks = 1;
		this.timerId = "timerId";
	}

	@Test
	void test() {
		assertThat(TriggersFactory.createTimerTrigger(stateMachine, transition, interval, ticks, timerId)).isInstanceOf(TimerTrigger.class);
	}
}