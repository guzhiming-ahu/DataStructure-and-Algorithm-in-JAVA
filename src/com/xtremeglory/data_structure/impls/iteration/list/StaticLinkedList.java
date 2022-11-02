package com.xtremeglory.data_structure.impls.iteration.list;

import com.xtremeglory.data_structure.List;

import java.util.Iterator;

public class StaticLinkedList<E> implements List<E> {
    private static final class Node<E> {
        private E elem;
        private int next;

        public Node(E elem, int next) {
            this.elem = elem;
            this.next = next;
        }

        public Node(E elem) {
            this(elem, -1);
        }

        public Node() {
            this(null, -1);
        }
    }

    @Override
    public void prepend(E e) {
        this.insert(0, e);
    }


    private final Node<E>[] array;
    private final int capacity;
    private int size;

    private static final int DEFAULT_CAPACITY = 100;

    public StaticLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public StaticLinkedList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.array = new Node[this.capacity + 1];
        this.array[0] = new Node<E>();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean compact() {
        return false;
    }

    @Override
    public void insert(int index, E e) {
        //静态链表底层实现是一个静态数组，不允许扩充容量，所以不仅需要保持下标在允许范围内，还要保证不超过容量限制
        if (index < 0 || index > this.size || index >= this.capacity) {
            return;
        }

        int cur = 0;
        while (index > 0) {
            cur = this.array[cur].next;
            index--;
        }
        int pos = 0;
        while (pos < this.capacity && this.array[pos] != null) {
            ++pos;
        }
        assert this.array[cur] != null;
        Node<E> node = new Node<>(e, this.array[cur].next);
        this.array[pos] = node;
        this.array[cur].next = pos;
        this.size++;
    }

    @Override
    public void append(E e) {
        this.insert(this.size, e);
    }

    @Override
    public E deque() {
        return this.remove(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int cur = 0;
        while (index > 0) {
            cur = this.array[cur].next;
            index--;
        }
        int next = this.array[cur].next;
        E elem = this.array[next].elem;
        this.array[cur].next = this.array[this.array[cur].next].next;
        this.array[next] = null;
        this.size--;
        return elem;
    }

    @Override
    public void removeAll() {
        for (int i = 1; i < this.capacity + 1; ++i) {
            this.array[i] = null;
        }
        this.array[0].next = -1;
        this.size = 0;
    }

    @Override
    public E pop() {
        return this.remove(this.size - 1);
    }

    @Override
    public E front() {
        return this.get(0);
    }

    @Override
    public void set(int index, E e) {
        if (index < 0 || index >= this.size) {
            return;
        }

        int cur = 0;
        while (index > -1) {
            cur = this.array[cur].next;
            index--;
        }
        this.array[cur].elem = e;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }

        int cur = 0;
        while (index > -1) {
            cur = this.array[cur].next;
            index--;
        }

        return this.array[cur].elem;
    }

    @Override
    public E tail() {
        return this.get(this.size - 1);
    }

    private static final class StaticLinkedListIterator<E> implements Iterator<E> {
        private final Node[] array;
        private int pos;

        public StaticLinkedListIterator(Node[] array) {
            this.array = array;
            this.pos = array[0].next;
        }

        @Override
        public boolean hasNext() {
            return pos != -1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            E elem = (E) array[pos].elem;
            pos = array[pos].next;
            return elem;
        }
    }

    @Override
    public int indexOf(E e) {
        int index = 0;
        int cur = 0;
        while (cur != -1 && e.equals(this.array[cur].elem)) {
            cur = this.array[cur].next;
            index++;
        }

        return cur == -1 ? -1 : index;
    }

    @Override
    public void reverse() {
        int current = this.array[0].next;
        int next = this.array[current].next;
        this.array[current].next = -1;
        while (next != -1) {
            int step_next = this.array[next].next;
            this.array[next].next = current;
            current = next;
            next = step_next;
        }
        this.array[0].next = current;
    }

    @Override
    public Iterator<E> iterator() {
        return new StaticLinkedListIterator<>(this.array);
    }
}