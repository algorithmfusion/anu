package com.algorithmfusion.anu.storage.api;

/**
 * 
 * @author Hallo Khaznadar
 */
public interface ObjectStorage {

	<K, V> V store(K key, V value);

	<V> V retrieve(Object key, Class<V> clazz);
}