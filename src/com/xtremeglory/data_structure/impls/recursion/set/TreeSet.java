package com.xtremeglory.data_structure.impls.recursion.set;

import com.xtremeglory.data_structure.Tree;
import com.xtremeglory.data_structure.impls.recursion.tree.BinarySearchTree;
import com.xtremeglory.data_structure.impls.recursion.tree.ThirdOrderBTree;

import java.util.Iterator;


public class TreeSet<E extends Comparable<E>> implements Set<E> {
    public enum DataStrategy {
        BINARY_SEARCH_TREE,
        AVL,
        THIRD_ORDER_B_TREE,
    }

    private final Tree<E> root;

    public TreeSet() {
        this.root = new ThirdOrderBTree<>();
    }

    public TreeSet(DataStrategy strategy) {
        switch (strategy) {
            case BINARY_SEARCH_TREE:
                this.root = new BinarySearchTree<>();
                break;
            case AVL:
                this.root = new BinarySearchTree<>();
                break;
            default:
                this.root = new ThirdOrderBTree<>();
                break;
        }
    }

    @Override
    public void add(E elem) {
        this.root.insert(elem);
    }

    @Override
    public E get(E elem) {
        return this.root.find(elem);
    }

    @Override
    public void remove(E elem) {
        this.root.delete(elem);
    }

    @Override
    public boolean contains(E elem) {
        return this.root.find(elem) != null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
