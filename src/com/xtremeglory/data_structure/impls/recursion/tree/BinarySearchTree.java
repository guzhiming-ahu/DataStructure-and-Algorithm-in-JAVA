package com.xtremeglory.data_structure.impls.recursion.tree;

import com.xtremeglory.data_structure.Stack;
import com.xtremeglory.data_structure.Tree;

import java.util.Iterator;


interface BstNode<E extends Comparable<E>> extends TreeNode<E> {
    BstNode<E> insert(E elem);

    BstNode<E> delete(E elem);

    BstNode<E> left();

    BstNode<E> right();

    E value();
}

class LeafNode<E extends Comparable<E>> implements BstNode<E> {

    @Override
    public BstNode<E> insert(E elem) {
        return new ValueNode<>(elem);
    }

    @Override
    public BstNode<E> delete(E elem) {
        return this;
    }

    @Override
    public BstNode<E> left() {
        return null;
    }

    @Override
    public BstNode<E> right() {
        return null;
    }

    @Override
    public E value() {
        return null;
    }

    @Override
    public E find(E elem) {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public E max() {
        return null;
    }

    @Override
    public E min() {
        return null;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int count() {
        return 0;
    }
}

class ValueNode<E extends Comparable<E>> implements BstNode<E> {
    E value;
    BstNode<E> left;
    BstNode<E> right;

    ValueNode(E value) {
        this.value = value;
        this.left = new LeafNode<>();
        this.right = new LeafNode<>();
    }

    protected ValueNode(E value, BstNode<E> left, BstNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return false;
    }

    @Override
    public E max() {
        return this.right.isLeaf() ? this.value : this.right.max();
    }

    @Override
    public E min() {
        return this.left.isLeaf() ? this.value : this.left.max();
    }

    @Override
    public int height() {
        return Math.max(this.left.height(), this.right.height()) + 1;
    }

    @Override
    public int count() {
        return this.left.count() + this.right.count() + 1;
    }

    @Override
    public BstNode<E> insert(E elem) {
        int k = elem.compareTo(this.value);
        if (k > 0) {
            this.right = this.right.insert(elem);
        } else if (k < 0) {
            this.left = this.left.insert(elem);
        }
        return this;
    }

    @Override
    public BstNode<E> delete(E elem) {
        // 前两个判断条件表示与删除元素不等的情况
        // 后三个判断条件表示删除元素相等时的删除策略
        // -策略1: 存在左子树，用左子树最大元素替代，然后删除
        // -策略2：只存在右子树，用右子树最小元素替代，然后删除
        // -策略3：删除节点为叶子节点，直接删除
        //  除最后一个策略会改变树的结构，其他策略都不会改变，所以返回this
        int k = elem.compareTo(this.value);
        if (k > 0) {
            this.right = this.right.delete(elem);
        } else if (k < 0) {
            this.left = this.left.delete(elem);
        } else if (!this.left.isLeaf()) {
            this.value = this.left.max();
            this.left = this.left.delete(this.value);
        } else if (!this.right.isLeaf()) {
            this.value = this.right.min();
            this.right = this.right.delete(this.value);
        } else {
            return new LeafNode<>();
        }
        return this;
    }

    @Override
    public BstNode<E> left() {
        return this.left;
    }

    @Override
    public BstNode<E> right() {
        return this.right;
    }

    @Override
    public E value() {
        return this.value;
    }

    @Override
    public E find(E elem) {
        int k = elem.compareTo(this.value);
        if (k > 0) {
            return this.right.find(elem);
        } else if (k < 0) {
            return this.left.find(elem);
        } else {
            return this.value;
        }
    }
}

class BstIterator<E extends Comparable<E>> implements Iterator<E> {
    Stack<BstNode<E>> stack;

    public BstIterator(BstNode<E> root) {
        this.stack = new Stack<>();
        while (!root.isLeaf()) {
            stack.push(root);
            root = root.left();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() {
        if (stack.isEmpty()) {
            return null;
        }
        BstNode<E> node = stack.pop();
        BstNode<E> root = node.right();
        while (!root.isLeaf()) {
            stack.push(root);
            root = root.left();
        }
        return node.value();
    }
}

public class BinarySearchTree<E extends Comparable<E>> implements Tree<E> {
    protected BstNode<E> root;

    public BinarySearchTree(BstNode<E> root) {
        this.root = root;
    }

    public BinarySearchTree() {
        this(new LeafNode<>());
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(15);
        bst.insert(25);
        bst.insert(35);
        bst.insert(10);
        bst.insert(5);
        bst.insert(13);
        bst.insert(33);
        for (int i : bst) {
            System.out.println(i);
        }
    }

    @Override
    public void insert(E elem) {
        this.root = this.root.insert(elem);
    }

    @Override
    public void delete(E elem) {
        this.root = this.root.delete(elem);
    }

    @Override
    public int height() {
        return this.root.height();
    }

    @Override
    public int count() {
        return this.root.count();
    }

    @Override
    public boolean isEmpty() {
        return this.root.count() == 0;
    }

    @Override
    public E find(E elem) {
        return this.root.find(elem);
    }

    @Override
    public E max() {
        return this.root.max();
    }

    @Override
    public E min() {
        return this.root.min();
    }

    @Override
    public Iterator<E> iterator() {
        return new BstIterator<>(this.root);
    }
}
