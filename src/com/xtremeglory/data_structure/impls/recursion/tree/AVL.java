package com.xtremeglory.data_structure.impls.recursion.tree;

class AvlLeafNode<E extends Comparable<E>> extends LeafNode<E> {
    @Override
    public BstNode<E> insert(E elem) {
        return new AvlValueNode<>(elem);
    }
}

class AvlValueNode<E extends Comparable<E>> extends ValueNode<E> {
    int height;

    AvlValueNode(E value) {
        super(value, new AvlLeafNode<>(), new AvlLeafNode<>());
        this.height = 1;
    }

    @Override
    public int height() {
        return this.height;
    }

    protected AvlValueNode<E> rotate(E elem) {
        if (Math.abs(this.left.height() - this.right.height()) <= 1) {
            return this;
        }
        if (elem.compareTo(this.value) > 0) {
            if (elem.compareTo(this.right.value()) > 0) {
                return rotateRR();
            } else {
                return rotateRL();
            }
        } else if (elem.compareTo(this.left.value()) < 0) {
            return rotateLL();
        } else {
            return rotateLR();
        }
    }

    AvlValueNode<E> rotateLL() {
        AvlValueNode<E> root = (AvlValueNode<E>) this.left;
        this.left = root.right;
        root.right = this;
        this.height = Math.max(this.left.height(), this.right.height()) + 1;
        root.height = Math.max(root.left.height(), root.right.height()) + 1;
        return root;
    }

    AvlValueNode<E> rotateRR() {
        AvlValueNode<E> root = (AvlValueNode<E>) this.right;
        this.right = root.left;
        root.left = this;
        this.height = Math.max(this.left.height(), this.right.height()) + 1;
        root.height = Math.max(root.left.height(), root.right.height()) + 1;
        return root;
    }

    AvlValueNode<E> rotateLR() {
        this.left = ((AvlValueNode<E>) this.left).rotateRR();
        return this.rotateLL();
    }

    AvlValueNode<E> rotateRL() {
        this.right = ((AvlValueNode<E>) this.right).rotateLL();
        return this.rotateRR();
    }

    @Override
    public BstNode<E> insert(E elem) {
        super.insert(elem);
        this.height = Math.max(this.left.height(), this.right.height()) + 1;
        return this.rotate(elem);
    }

    @Override
    public BstNode<E> delete(E elem) {
        super.delete(elem);
        this.height = Math.max(this.left.height(), this.right.height()) + 1;
        return this.rotate(elem);
    }
}

public class AVL<E extends Comparable<E>> extends BinarySearchTree<E> {
    public AVL() {
        super(new AvlLeafNode<>());
    }

    public static void main(String[] args) {
        AVL<Integer> bst = new AVL<>();
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
}
