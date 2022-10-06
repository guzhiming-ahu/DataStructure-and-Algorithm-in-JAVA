package com.xtremeglory.data_structure.impls.recursion.map;

import com.xtremeglory.data_structure.impls.recursion.set.Set;

public interface Map<K,V> {
    void put(K key,V value);

    V get(K key);

    Set<K> ketSets();

    boolean containsKey(K key);
}
