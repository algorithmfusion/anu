package com.algorithmfusion.anu.generic.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import com.algorithmfusion.anu.generic.api.MultiValueMap;
import com.algorithmfusion.anu.generic.api.MutableMultiValueMap;

class MutableMultiValueMapImplTest extends MultiValueMapImplTest {

	@Override
	protected MultiValueMap<String, String> createMultiValueMap() {
		return new MutableMultiValueMapImpl<>();
	}
	
	@Test
	void whenRemovingMappedValuesToKeyUsingPuttingApisThenWeGetCollectionWithRemainingMapedValues() {
		MutableMultiValueMap<String, String> mmvm = new MutableMultiValueMapImpl<>();
		Collection<String> actual = null;
		boolean isRemoved = false;
		
		String k1 = "k1";
		String k1v1 = "k1v1";
		actual = mmvm.put(k1, k1v1);
		assertThat(actual).containsExactly(k1v1);
		
		actual.remove(k1v1);
		assertThat(mmvm.get(k1)).isEmpty();
		
		String k1v2 = "k1v2";
		String k1v3 = "k1v3";
		String k1v4 = "k1v4";
		actual = mmvm.putAll(k1, new String[] {k1v2, k1v3, k1v4});
		assertThat(actual).containsExactly(k1v2, k1v3, k1v4);
		
		actual.remove(k1v3);
		assertThat(mmvm.get(k1)).containsExactly(k1v2, k1v4);
		
		String k1v5 = "k1v5";
		String k1v6 = "k1v6";
		String k1v7 = "k1v7";
		actual = mmvm.putAll(k1, Arrays.asList(new String[] {k1v5, k1v6, k1v7}));
		assertThat(actual).containsExactly(k1v2, k1v4, k1v5, k1v6, k1v7);
		
		isRemoved = actual.remove(k1v7);
		assertThat(mmvm.get(k1)).containsExactly(k1v2, k1v4, k1v5, k1v6);
		assertThat(isRemoved).isTrue();
		
		isRemoved = actual.remove(k1v7);
		assertThat(mmvm.get(k1)).containsExactly(k1v2, k1v4, k1v5, k1v6);
		assertThat(isRemoved).isFalse();
		
		String k2 = "k2";
		String k2v1 = "k2v1";
		actual = mmvm.put(k2, k2v1);
		assertThat(actual).containsExactly(k2v1);
		
		actual.remove(k2v1);
		assertThat(mmvm.get(k2)).isEmpty();
		
		String k2v2 = "k2v2";
		String k2v3 = "k2v3";
		actual = mmvm.putAll(k2, new String[] {k2v2, k2v3});
		assertThat(actual).containsExactly(k2v2, k2v3);
		
		actual.remove(k2v3);
		assertThat(mmvm.get(k2)).containsExactly(k2v2);
		
		String k2v4 = "k2v4";
		String k2v5 = "k2v5";
		actual = mmvm.putAll(k2, Arrays.asList(new String[] {k2v4, k2v5}));
		assertThat(actual).containsExactly(k2v2, k2v4, k2v5);
		
		isRemoved = actual.remove(k2v4);
		assertThat(mmvm.get(k2)).containsExactly(k2v2, k2v5);
		assertThat(isRemoved).isTrue();
		
		isRemoved = actual.remove(k2v4);
		assertThat(mmvm.get(k2)).containsExactly(k2v2, k2v5);
		assertThat(isRemoved).isFalse();
	}
	
	@Test
	void whenRemovingKeyMappedToValuesThenWeGetRemovedCollectionForThatKey() {
		MutableMultiValueMap<String, String> mmvm = new MutableMultiValueMapImpl<>();
		Collection<String> actual = null;
		
		String k1 = "k1";
		String k1v1 = "k1v1";
		actual = mmvm.put(k1, k1v1);
		assertThat(actual).containsExactly(k1v1);
		
		actual = mmvm.remove(k1);
		assertThat(actual).containsExactly(k1v1);
	}
	
	@Test
	void whenRemovingKeyMappedToValuesThenUsingGetForThatKeyWillGiveNullCollection() {
		MutableMultiValueMap<String, String> mmvm = new MutableMultiValueMapImpl<>();
		Collection<String> actual = null;
		
		String k1 = "k1";
		String k1v1 = "k1v1";
		actual = mmvm.put(k1, k1v1);
		assertThat(actual).containsExactly(k1v1);
		
		actual = mmvm.remove(k1);
		assertThat(actual).containsExactly(k1v1);
		assertThat(mmvm.get(k1)).isNull();
	}
}