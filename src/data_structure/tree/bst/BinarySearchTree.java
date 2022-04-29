package data_structure.tree.bst;

import data_structure.tree.Tree;

import java.util.Iterator;

class BSTNode<E extends Comparable<E>> {
    E value;
    BSTNode<E> left;
    BSTNode<E> right;

    BSTNode(E value) {
        this.value = value;
    }

    boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    static <E extends Comparable<E>> E max(BSTNode<E> root) {
        if (root.right == null) {
            return root.value;
        }
        return max(root.right);
    }

    static <E extends Comparable<E>> E min(BSTNode<E> root) {
        if (root.left == null) {
            return root.value;
        }
        return min(root.left);
    }

    static <E extends Comparable<E>> BSTNode<E> insert(BSTNode<E> root, E elem) {
        if (root == null) {
            return new BSTNode<>(elem);
        }
        int k=elem.compareTo(root.value);
        if (k > 0) {
            root.right = insert(root.right, elem);
        } else if (k < 0) {
            root.left = insert(root.left, elem);
        }
        return root;
    }

    static <E extends Comparable<E>> BSTNode<E> delete(BSTNode<E> root, E elem) {
        int k=elem.compareTo(root.value);
        if (k > 0) {
            root.right = delete(root.right, root.value);
        } else if (k < 0) {
            root.left = delete(root.left, root.value);
        }else {
            if (root.isLeaf()){
                return null;
            } else if (root.right!=null){
                root.value = min(root.right);
                root.right = delete(root.right, root.value);
            }else {
                root.value = max(root.left);
                root.left = delete(root.left, root.value);
            }
        }
        return root;
    }

    static <E extends Comparable<E>> int height(BSTNode<E> root){
        if (root==null){
            return 0;
        }
        return Math.max(height(root.left),height(root.right))+1;
    }

    static <E extends Comparable<E>> int count(BSTNode<E> root){
        if (root==null){
            return 0;
        }
        return count(root.left)+count(root.right)+1;
    }

    static <E extends Comparable<E>> E find(BSTNode<E> root, E elem) {
        if (root == null) {
            return null;
        }
        if (elem.compareTo(root.value) > 0) {
            return find(root.right, elem);
        } else if (elem.compareTo(root.value) < 0) {
            return find(root.left, elem);
        } else {
            return root.value;
        }
    }

}

public class BinarySearchTree<E extends Comparable<E>> implements Tree<E> {
    private BSTNode<E> root;

    @Override
    public void insert(E elem) {
        this.root = BSTNode.insert(this.root, elem);
    }

    @Override
    public void delete(E elem) {
        if (root != null) {
            this.root = BSTNode.delete(this.root, elem);
        }
    }

    @Override
    public int height() {
        return BSTNode.height(this.root);
    }

    @Override
    public int count() {
        return BSTNode.count(this.root);
    }

    @Override
    public boolean isEmpty() {
        return this.root==null;
    }

    @Override
    public E find(E elem) {
        return BSTNode.find(this.root,elem);
    }

    @Override
    public E max() {
        return BSTNode.max(this.root);
    }

    @Override
    public E min() {
        return BSTNode.min(this.root);
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
