package com.xtremeglory.data_structure;

import java.util.Objects;

public class TreeSet<K extends Comparable<K>, V> {
    Tree<KVNode<K, V>> tree;

    private static final class KVNode<K extends Comparable<K>, V> implements Comparable<KVNode<K, V>> {
        K key;
        V value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KVNode<?, ?> kvNode = (KVNode<?, ?>) o;
            return key.equals(kvNode.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public int compareTo(KVNode<K, V> o) {
            return this.key.compareTo(o.key);
        }
    }


}
