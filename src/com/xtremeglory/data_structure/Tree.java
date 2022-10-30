package com.xtremeglory.data_structure;

public interface Tree<E extends Comparable<E>> extends Iterable<E> {
    void insert(E elem);

    void delete(E elem);

    int height();

    int count();

    boolean isEmpty();

    E find(E elem);

    E max();

    E min();
}
