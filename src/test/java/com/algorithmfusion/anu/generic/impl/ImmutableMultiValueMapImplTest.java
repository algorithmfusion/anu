package com.algorithmfusion.anu.generic.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.ImmutableMultiValueMap;
import com.algorithmfusion.anu.generic.api.MultiValueMap;

class ImmutableMultiValueMapImplTest extends MultiValueMapImplTest {

	ImmutableMultiValueMap<String, String> imvm;

	@Override
	protected MultiValueMap<String, String> createMultiValueMap() {
		return new ImmutableMultiValueMapImpl<>();
	}
	
	@Test
	void whenRemovingMappedValuesToKeyUsingPuttingApisThenUnsupportedOperationExceptionIsThrowen() {
		ImmutableMultiValueMap<String, String> imvm = new ImmutableMultiValueMapImpl<>();
		
		String k1 = "k1";
		String k1v1 = "k1v1";
		final Collection<String> actual = imvm.put(k1, k1v1);
		assertThrows(UnsupportedOperationException.class, () -> actual.remove(k1v1));
	}
}