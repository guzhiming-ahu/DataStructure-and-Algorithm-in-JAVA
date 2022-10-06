package com.xtremeglory.data_structure.impls.recursion.map;

import com.xtremeglory.data_structure.impls.recursion.set.Set;
import com.xtremeglory.data_structure.impls.recursion.set.TreeSet;

class KVNode<K extends Comparable<K>,V> implements Comparable<KVNode<K,V>>{
    K key;
    V value;

    public KVNode(K key) {
        this.key = key;
    }

    public KVNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(KVNode<K, V> o) {
        return this.key.compareTo(o.key);
    }
}

public class TreeMap<K extends Comparable<K>,V> implements Map<K,V>{
    enum DataStrategy {
        BINARY_SEARCH_TREE,
        AVL,
        THIRD_ORDER_B_TREE,
    }

    Set<KVNode<K,V>> set;

    public TreeMap() {
        this.set = new TreeSet<>();
    }

    public TreeMap(DataStrategy strategy) {
        switch (strategy) {
            case BINARY_SEARCH_TREE:
                this.set = new TreeSet<>(TreeSet.DataStrategy.BINARY_SEARCH_TREE);
                break;
            case AVL:
                this.set = new TreeSet<>(TreeSet.DataStrategy.AVL);
                break;
            case THIRD_ORDER_B_TREE:
            default:
                this.set = new TreeSet<>(TreeSet.DataStrategy.THIRD_ORDER_B_TREE);
                break;
        }
    }

    @Override
    public void put(K key, V value) {
        this.set.add(new KVNode<>(key, value));
    }

    @Override
    public V get(K key) {
        return this.set.get(new KVNode<>(key)).value;
    }

    @Override
    public Set<K> ketSets() {
        Set<K> keyset=new TreeSet<>();
        for (KVNode<K,V> node:this.set){
            keyset.add(node.key);
        }
        return keyset;
    }

    @Override
    public boolean containsKey(K key) {
        return this.set.contains(new KVNode<>(key));
    }
}
