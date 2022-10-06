package com.xtremeglory.data_structure.impls.recursion.set;

public interface Set<E> extends Iterable<E>{
    void add(E elem);

    E get(E elem);

    void remove(E elem);

    boolean contains(E elem);
}
