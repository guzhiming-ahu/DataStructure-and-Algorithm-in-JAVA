package com.xtremeglory.data_structure.impls.recursion.list;

import com.xtremeglory.data_structure.List;

import java.util.Iterator;

final class Node<E> {
    E elem;
    Node<E> next;

    public Node(E elem, Node<E> next) {
        this.elem = elem;
        this.next = next;
    }

    public Node(E elem) {
        this(elem, null);
    }

    public Node() {
        this(null, null);
    }

    public boolean insert(int index, E e) {
        if (index == 0) {
            this.next = new Node<>(e, this.next);
            return true;
        } else if (this.next != null) {
            return this.next.insert(index - 1, e);
        } else {
            return false;
        }
    }

    /**
     * 被删除结点为this.next
     *
     * @param index 被删除结点的索引
     * @return 被删除的元素
     */
    public E remove(int index) {
        if (this.next == null) {
            return null;
        } else if (index == 0) {
            E elem = this.next.elem;
            this.next = this.next.next;
            return elem;
        } else {
            return this.next.remove(index - 1);
        }
    }

    public void set(int index, E e) {
        if (index == -1) {
            this.elem = e;
        } else if (this.next != null) {
            this.next.set(index - 1, e);
        }
    }

    public E get(int index) {
        if (index == -1) {
            return this.elem;
        } else if (this.next != null) {
            return this.next.get(index - 1);
        } else {
            return null;
        }
    }

    public int indexOf(int index, E e) {
        if (e.equals(this.elem)) {
            return index;
        } else if (this.next != null) {
            return this.next.indexOf(index + 1, e);
        } else {
            return -1;
        }
    }

    public static <E> Node<E> reverse(Node<E> node) {
        if (node == null || node.next == null) {
            return node;
        } else {
            Node<E> head = reverse(node.next);
            node.next.next = node;
            node.next = null;
            return head;
        }
    }
}

public class LinkedList<E> implements List<E> {
    private int size;
    //非空头结点
    private final Node<E> head;

    private static final class LinkedListIterator<E> implements Iterator<E> {
        private Node<E> currentPosition;

        public LinkedListIterator(Node<E> currentPosition) {
            this.currentPosition = currentPosition;
        }

        @Override
        public boolean hasNext() {
            return currentPosition != null;
        }

        @Override
        public E next() {
            E elem = currentPosition.elem;
            currentPosition = currentPosition.next;
            return elem;
        }
    }

    public LinkedList() {
        this.size = 0;
        this.head = new Node<>();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 链表不支持紧凑操作
     *
     * @return 链表不支持紧凑，返回false
     */
    @Override
    public boolean compact() {
        return false;
    }

    @Override
    public void insert(int index, E e) {
        if (this.head.insert(index, e)) {
            this.size++;
        }
    }

    @Override
    public void prepend(E e) {
        this.insert(0, e);
    }

    @Override
    public void append(E e) {
        this.insert(this.size, e);
    }

    @Override
    public E remove(int index) {
        E elem = this.head.remove(index);
        if (elem != null) {
            this.size--;
        }
        return elem;
    }

    @Override
    public void removeAll() {
        this.head.next = null;
        this.size = 0;
    }

    @Override
    public E deque() {
        return this.remove(0);
    }

    @Override
    public E pop() {
        return this.remove(this.size - 1);
    }

    @Override
    public void set(int index, E e) {
        this.head.set(index, e);
    }

    @Override
    public E get(int index) {
        return this.head.get(index);
    }

    @Override
    public E front() {
        return this.get(0);
    }

    @Override
    public E tail() {
        return this.get(this.size - 1);
    }

    @Override
    public int indexOf(E e) {
        if (e != null) {
            return this.head.indexOf(0, e);
        }
        return -1;
    }

    @Override
    public void reverse() {
        if (head.next != null) {
            this.head.next = Node.reverse(head.next);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(this.head.next);
    }
}
