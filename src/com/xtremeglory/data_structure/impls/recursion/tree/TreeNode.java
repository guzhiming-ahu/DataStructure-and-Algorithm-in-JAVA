package com.xtremeglory.data_structure.impls.recursion.tree;

public interface TreeNode<E extends Comparable<E>> {
    TreeNode<E> insert(E elem);

    TreeNode<E> delete(E elem);

    E find(E elem);

    boolean isLeaf();

    E max();

    E min();

    int height();

    int count();
}


