package com.algorithmfusion.anu.generic.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.TextStreamWriter;

class TextStreamWriterFactoryTest {

	@Test
	void test() {
		assertThat(TextStreamWriterFactory.getTextStreamWriter())
			.isInstanceOf(TextStreamWriter.class);
	}
}