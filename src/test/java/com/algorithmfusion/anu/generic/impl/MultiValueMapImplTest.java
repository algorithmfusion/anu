package com.algorithmfusion.anu.generic.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.MultiValueMap;

abstract class MultiValueMapImplTest {

	protected abstract MultiValueMap<String, String> createMultiValueMap();

	@Test
	void whenMappingValuesToKeyUsingPuttingApisThenWeGetCollectionWithAllMapedValues() {
		MultiValueMap<String, String> mvm = createMultiValueMap();
		Collection<String> actual = null;
		
		String k1 = "k1";
		String k1v1 = "k1v1";
		actual = mvm.put(k1, k1v1);
		assertThat(actual).containsExactly(k1v1);
		
		String k1v2 = "k1v2";
		String k1v3 = "k1v3";
		String k1v4 = "k1v4";
		actual = mvm.putAll(k1, new String[] {k1v2, k1v3, k1v4});
		assertThat(actual).containsExactly(k1v1, k1v2, k1v3, k1v4);
		
		String k1v5 = "k1v5";
		String k1v6 = "k1v6";
		String k1v7 = "k1v7";
		actual = mvm.putAll(k1, Arrays.asList(new String[] {k1v5, k1v6, k1v7}));
		assertThat(actual).containsExactly(k1v1, k1v2, k1v3, k1v4, k1v5, k1v6, k1v7);

		actual = mvm.get(k1);
		assertThat(actual).containsExactly(k1v1, k1v2, k1v3, k1v4, k1v5, k1v6, k1v7);
		
		String k2 = "k2";
		String k2v1 = "k2v1";
		actual = mvm.put(k2, k2v1);
		assertThat(actual).containsExactly(k2v1);
		
		String k2v2 = "k2v2";
		String k2v3 = "k2v3";
		actual = mvm.putAll(k2, new String[] {k2v2, k2v3});
		assertThat(actual).containsExactly(k2v1, k2v2, k2v3);
		
		String k2v4 = "k2v4";
		String k2v5 = "k2v5";
		actual = mvm.putAll(k2, Arrays.asList(new String[] {k2v4, k2v5}));
		assertThat(actual).containsExactly(k2v1, k2v2, k2v3, k2v4, k2v5);

		actual = mvm.get(k2);
		assertThat(actual).containsExactly(k2v1, k2v2, k2v3, k2v4, k2v5);
	}
}
