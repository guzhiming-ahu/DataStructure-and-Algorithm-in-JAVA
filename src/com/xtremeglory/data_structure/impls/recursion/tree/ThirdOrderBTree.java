package com.xtremeglory.data_structure.impls.recursion.tree;

import com.xtremeglory.data_structure.Tree;

import java.util.Iterator;

enum TreeNodePosition {
    LEFT, MIDDLE, RIGHT
}

abstract class BTreeNode<E extends Comparable<E>> {
    boolean isLeaf() {
        return false;
    }

    BTreeNode<E> insert(E elem) {
        return this;
    }

    BTreeNode<E> upgrade(BTreeNode<E> another, TreeNodePosition position) {
        return this;
    }


    BTreeNode<E> split() {
        return this;
    }


    BTreeNode<E> delete(E elem) {
        return this;
    }

    boolean needRotation(BTreeNode<E> brother) {
        return false;
    }

    boolean needMerge(BTreeNode<E> brother) {
        return false;
    }

    BTreeNode<E> leftRotation(BTreeNode<E> parent, TreeNodePosition position) {
        return this;
    }

    BTreeNode<E> rightRotation(BTreeNode<E> parent) {
        return this;
    }

    BTreeNode<E> downgrade(TreeNodePosition position) {
        return this;
    }

    BTreeNode<E> mergeRight(E excape, NilNode<E> another) {
        return this;
    }

    BTreeNode<E> mergeLeft(E excape, NilNode<E> another) {
        return this;
    }

    E find(E elem) {
        return null;
    }

    E min() {
        return null;
    }

    E max() {
        return null;
    }

    int height(){
        return 0;
    }

    int count(){
        return 0;
    }

}

class NilNode<E extends Comparable<E>> extends BTreeNode<E> {
    BTreeNode<E> branch;

    public NilNode() {
    }

    public NilNode(BTreeNode<E> branch) {
        this.branch = branch;
    }

    @Override
    boolean isLeaf() {
        return this.branch == null;
    }

    @Override
    boolean needRotation(BTreeNode<E> brother) {
        return this.isLeaf() && brother instanceof TripleNode;
    }

    @Override
    boolean needMerge(BTreeNode<E> brother) {
        return !this.isLeaf() || brother instanceof DoubleNode;
    }

    @Override
    public BTreeNode<E> insert(E elem) {
        return new DoubleNode<>(elem);
    }
}

class DoubleNode<E extends Comparable<E>> extends BTreeNode<E> {
    E value;
    BTreeNode<E> left;
    BTreeNode<E> right;

    DoubleNode(E value) {
        this.value = value;
    }

    public DoubleNode(E value, BTreeNode<E> left, BTreeNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public BTreeNode<E> insert(E elem) {
        if (elem.compareTo(value) > 0) {
            if (this.isLeaf()) {
                return new TripleNode<>(this.value, elem);
            } else {
                this.right = this.right.insert(elem);
                return this.upgrade(this.right, TreeNodePosition.RIGHT);
            }
        } else if (elem.compareTo(value) < 0) {
            if (this.isLeaf()) {
                return new TripleNode<>(elem, this.value);
            } else {
                this.left = this.left.insert(elem);
                return this.upgrade(this.left, TreeNodePosition.LEFT);
            }
        }
        return this;
    }

    @Override
    public BTreeNode<E> delete(E elem) {
        if (elem.compareTo(this.value) > 0) {
            this.right = this.right.delete(elem);
            if (this.right.needRotation(this.left)) {
                return this.left.rightRotation(this);
            } else if (this.right.needMerge(this.left)) {
                return this.downgrade(TreeNodePosition.RIGHT);
            }
        } else if (elem.compareTo(this.value) < 0) {
            this.left = this.left.delete(elem);
            if (this.left.needRotation(this.right)) {
                return this.right.leftRotation(this,TreeNodePosition.LEFT);
            }else if (this.left.needMerge(this.right)){
                return this.downgrade(TreeNodePosition.LEFT);
            }
        } else {
            if (this.isLeaf()) {
                return new NilNode<>();
            } else {
                this.value = this.left.min();
                this.left = this.left.delete(this.value);
                if (this.left.needRotation(this.right)){
                    return this.right.leftRotation(this,TreeNodePosition.LEFT);
                }else if (this.left.needMerge(this.right)){
                    return this.downgrade(TreeNodePosition.LEFT);
                }
            }
        }
        return this;
    }

    @Override
    BTreeNode<E> downgrade(TreeNodePosition position) {
        switch (position) {
            case LEFT:
                return new NilNode<>(this.right.mergeLeft(this.value, (NilNode<E>) this.left));
            case RIGHT:
                return new NilNode<>(this.left.mergeRight(this.value, (NilNode<E>) this.right));
        }
        return this;
    }

    @Override
    BTreeNode<E> mergeLeft(E escape, NilNode<E> another) {
        return new TripleNode<>(escape, this.value, another.branch, this.left, this.right);
    }

    @Override
    BTreeNode<E> mergeRight(E escape, NilNode<E> another) {
        return new TripleNode<>(this.value, escape, this.left, this.right, another.branch);
    }

    @Override
    public E find(E elem) {
        if (elem.compareTo(value) > 0) {
            return this.right.find(elem);
        } else if (elem.compareTo(value) < 0) {
            return this.left.find(elem);
        } else {
            return this.value;
        }

    }

    @Override
    public BTreeNode<E> upgrade(BTreeNode<E> another, TreeNodePosition position) {
        if (another instanceof Quadruple) {
            DoubleNode<E> dnode = (DoubleNode<E>) another.split();
            switch (position) {
                case LEFT:
                    return new TripleNode<>(dnode.value, this.value, dnode.left, dnode.right, this.right);
                case RIGHT:
                    return new TripleNode<>(this.value, dnode.value, this.left, dnode.left, dnode.right);
            }
        }
        return this;
    }

    @Override
    public E min() {
        if (this.isLeaf()) {
            return this.value;
        }
        return this.left.min();
    }

    @Override
    public E max() {
        if (this.isLeaf()) {
            return this.value;
        }
        return this.right.max();
    }

    @Override
    int height() {
        if (this.isLeaf()){
            return 1;
        }
        return this.left.height()+1;
    }

    @Override
    int count() {
        if (this.isLeaf()){
            return 1;
        }
        return this.left.count()+this.right.count()+1;
    }
}

class TripleNode<E extends Comparable<E>> extends BTreeNode<E> {
    E first;
    E second;
    BTreeNode<E> left;
    BTreeNode<E> middle;
    BTreeNode<E> right;

    public TripleNode(E first, E second) {
        this.first = first;
        this.second = second;
    }

    public TripleNode(E first, E second, BTreeNode<E> left, BTreeNode<E> middle, BTreeNode<E> right) {
        this.first = first;
        this.second = second;
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    @Override
    public boolean isLeaf() {
        return this.left == null && this.middle == null && this.right == null;
    }

    @Override
    public BTreeNode<E> insert(E elem) {
        if (elem.compareTo(this.first) < 0) {
            if (this.isLeaf()) {
                return new Quadruple<>(elem, this.first, this.second);
            } else {
                this.left = this.left.insert(elem);
                return this.upgrade(this.left, TreeNodePosition.LEFT);
            }
        } else if (elem.compareTo(this.first) > 0 && elem.compareTo(this.second) < 0) {
            if (this.isLeaf()) {
                return new Quadruple<>(this.first, elem, this.second);
            } else {
                this.middle = this.middle.insert(elem);
                return this.upgrade(this.middle, TreeNodePosition.MIDDLE);
            }
        } else if (elem.compareTo(this.second) > 0) {
            if (this.isLeaf()) {
                return new Quadruple<>(this.first, this.second, elem);
            } else {
                this.right = this.right.insert(elem);
                return this.upgrade(this.right, TreeNodePosition.RIGHT);
            }
        }
        return this;
    }

    @Override
    public BTreeNode<E> delete(E elem) {
        if (elem.compareTo(this.first) < 0) {
            this.left = this.left.delete(elem);
            if (this.left.needRotation(this.middle)) {
                return this.middle.leftRotation(this, TreeNodePosition.LEFT);
            } else if (this.left.needMerge(this.middle)) {
                return this.downgrade(TreeNodePosition.LEFT);
            }
        } else if (elem.compareTo(this.first) == 0) {
            if (this.isLeaf()) {
                return new DoubleNode<>(this.second);
            } else {
                this.first = this.left.max();
                this.left = this.left.delete(this.first);
                if (this.left.needRotation(this.middle)){
                    return this.middle.leftRotation(this,TreeNodePosition.LEFT);
                }else if (this.left.needMerge(this.middle)){
                    return this.downgrade(TreeNodePosition.LEFT);
                }
            }
        } else if (elem.compareTo(this.second) < 0) {
            this.middle = this.middle.delete(elem);
            if (this.middle.needRotation(this.right)){
                return this.right.leftRotation(this,TreeNodePosition.MIDDLE);
            }else if (this.middle.needMerge(this.right)){
                return this.downgrade(TreeNodePosition.MIDDLE);
            }
        } else if (elem.compareTo(this.second) == 0) {
            if (this.isLeaf()) {
                return new DoubleNode<>(this.first);
            } else {
                this.second = this.right.min();
                this.right = this.right.delete(this.second);
                if (this.right.needRotation(this.middle)){
                    return this.middle.rightRotation(this);
                }else if (this.right.needMerge(this.middle)){
                    return this.downgrade(TreeNodePosition.RIGHT);
                }
            }
        } else {
            this.right = this.right.delete(elem);
            if (this.right.needRotation(this.middle)){
                return this.middle.rightRotation(this);
            }else if (this.right.needMerge(this.middle)){
                return this.downgrade(TreeNodePosition.RIGHT);
            }
        }
        return this;
    }

    @Override
    BTreeNode<E> leftRotation(BTreeNode<E> parent, TreeNodePosition position) {
        if (parent instanceof DoubleNode) {
            return new DoubleNode<>(this.first, new DoubleNode<>(((DoubleNode<E>) parent).value), new DoubleNode<>(this.second));
        } else {
            TripleNode<E> node = (TripleNode<E>) parent;
            switch (position) {
                case LEFT:
                    node.left = new DoubleNode<>(node.first);
                    node.first = this.first;
                    node.middle = new DoubleNode<>(this.second);
                    break;
                case MIDDLE:
                    node.middle = new DoubleNode<>(node.second);
                    node.second = this.first;
                    node.right = new DoubleNode<>(this.second);
                    break;
            }
        }
        return parent;
    }

    @Override
    BTreeNode<E> rightRotation(BTreeNode<E> parent) {
        if (parent instanceof DoubleNode) {
            return new DoubleNode<>(this.second, new DoubleNode<>(this.first), new DoubleNode<>(((DoubleNode<E>) parent).value));
        } else {
            TripleNode<E> node = (TripleNode<E>) parent;
            node.right = new DoubleNode<>(node.second);
            node.second = this.second;
            node.middle = new DoubleNode<>(this.first);
            return parent;
        }
    }

    @Override
    BTreeNode<E> downgrade(TreeNodePosition position) {
        DoubleNode<E> root;
        switch (position) {
            case LEFT:
                root = new DoubleNode<>(this.second, this.middle.mergeLeft(this.first, (NilNode<E>) this.left), this.right);
                return root.upgrade(root.left, TreeNodePosition.LEFT);
            case MIDDLE:
                root = new DoubleNode<>(this.first, this.left, this.right.mergeLeft(this.second, (NilNode<E>) this.middle));
                return root.upgrade(root.right, TreeNodePosition.RIGHT);
            case RIGHT:
                root = new DoubleNode<>(this.first, this.left, this.middle.mergeRight(this.second, (NilNode<E>) this.right));
                return root.upgrade(this.right,TreeNodePosition.RIGHT);
        }
        return this;
    }

    @Override
    BTreeNode<E> mergeLeft(E escape, NilNode<E> another) {
        return new Quadruple<>(escape, this.first, this.second, another.branch, this.left, this.middle, this.right);
    }

    @Override
    BTreeNode<E> mergeRight(E escape, NilNode<E> another) {
        return new Quadruple<>(this.first, this.second, escape, this.left, this.middle, this.right, another.branch);
    }

    @Override
    public E find(E elem) {
        if (elem.compareTo(this.first) < 0) {
            return this.left.find(elem);
        } else if (elem.compareTo(this.first) == 0) {
            return this.first;
        } else if (elem.compareTo(this.second) < 0) {
            return this.middle.find(elem);
        } else if (elem.compareTo(this.second) == 0) {
            return this.second;
        } else {
            return this.right.find(elem);
        }
    }

    @Override
    public BTreeNode<E> upgrade(BTreeNode<E> another, TreeNodePosition position) {
        if (another instanceof Quadruple) {
            DoubleNode<E> dnode = (DoubleNode<E>) another.split();
            switch (position) {
                case LEFT:
                    return new Quadruple<>(dnode.value, this.first, this.second, dnode.left, dnode.right, this.middle, this.right);
                case MIDDLE:
                    return new Quadruple<>(this.first, dnode.value, this.second, this.left, dnode.left, dnode.right, this.right);
                case RIGHT:
                    return new Quadruple<>(this.first, this.second, dnode.value, this.left, this.middle, dnode.left, dnode.right);
            }
        }
        return this;
    }

    @Override
    public E min() {
        if (this.isLeaf()) {
            return this.first;
        }
        return this.left.min();
    }

    @Override
    public E max() {
        if (this.isLeaf()) {
            return this.second;
        }
        return this.right.max();
    }

    @Override
    int height() {
        if (this.isLeaf()){
            return 1;
        }
        return this.left.height()+1;
    }

    @Override
    int count() {
        if (this.isLeaf()){
            return 2;
        }
        return this.left.count()+this.middle.count()+this.right.count()+2;
    }
}

class Quadruple<E extends Comparable<E>> extends BTreeNode<E> {
    E first;
    E second;
    E third;

    BTreeNode<E> left;
    BTreeNode<E> middle_left;
    BTreeNode<E> middle_right;
    BTreeNode<E> right;

    public Quadruple(E first, E second, E third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Quadruple(E first, E second, E third,
                     BTreeNode<E> left, BTreeNode<E> middle_left, BTreeNode<E> middle_right, BTreeNode<E> right) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.left = left;
        this.middle_left = middle_left;
        this.middle_right = middle_right;
        this.right = right;
    }

    @Override
    public BTreeNode<E> split() {
        return new DoubleNode<>(this.second,
                new DoubleNode<>(this.first, this.left, this.middle_left),
                new DoubleNode<>(this.third, this.middle_right, this.right));
    }
}

public class ThirdOrderBTree<E extends Comparable<E>> implements Tree<E> {
    private BTreeNode<E> root;

    public ThirdOrderBTree() {
        this.root = new NilNode<>();
    }

    @Override
    public void insert(E elem) {
        this.root = this.root.insert(elem).split();
    }

    @Override
    public void delete(E elem) {
        this.root = this.root.delete(elem).split();
        if (this.root instanceof NilNode){
            if (!this.root.isLeaf()){
                this.root=((NilNode<E>) this.root).branch.split();
            }
        }
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
        return this.count()==0;
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
        return null;
    }
}
