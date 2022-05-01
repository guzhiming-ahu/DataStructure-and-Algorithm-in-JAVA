package data_structure.map;

import data_structure.set.Set;

public interface Map<K,V> {
    void put(K key,V value);

    V get(K key);

    Set<K> ketSets();

    boolean containsKey(K key);
}
