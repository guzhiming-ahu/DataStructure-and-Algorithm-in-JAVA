package com.xtremeglory.data_structure.impls.iteration.list;

import com.xtremeglory.data_structure.List;

import java.util.Iterator;
import java.util.RandomAccess;

public class ArrayList<E> implements List<E>, RandomAccess {
    private Object[] array;
    private int capacity;
    private int size;

    private static final int DEFAULT_CAPACITY = 100;
    private static final int DEFAULT_INC_SIZE = 20;

    private static final class ArrayListIterator<E> implements Iterator<E> {
        private int index;
        private final ArrayList<E> list;

        public ArrayListIterator(ArrayList<E> list) {
            this.index = 0;
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return 0 <= this.index && this.index < this.list.size;
        }

        @Override
        public E next() {
            return this.list.get(index++);
        }
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.array = new Object[this.capacity];
    }

    @Override
    public void insert(int index, E e) {
        if (index < 0 && index > this.size) {
            return;
        }
        if (this.size == this.capacity) {
            this.resize();
        }
        System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        this.array[index] = e;
        this.size++;
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
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        E elem = (E) this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
        this.size--;
        return elem;
    }

    @Override
    public void removeAll() {
        this.size = 0;
    }

    @Override
    public E deque() {
        return remove(0);
    }

    @Override
    public E pop() {
        return remove(this.size - 1);
    }

    @Override
    public void set(int index, E e) {
        if (index >= 0 && index < this.size) {
            this.array[index] = e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= 0 && index < this.size) {
            return (E) this.array[index];
        }
        return null;
    }

    @Override
    public E front() {
        return get(0);
    }

    @Override
    public E tail() {
        return get(this.size - 1);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E e) {
        if (e != null) {
            for (int i = 0; i < this.size; ++i) {
                if (e.equals(this.array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean compact() {
        Object[] new_array = new Object[this.size];
        System.arraycopy(this.array, 0, new_array, 0, this.size);
        this.array = new_array;
        this.capacity = this.size;
        return true;
    }

    public void resize() {
        Object[] new_array = new Object[this.capacity + DEFAULT_INC_SIZE];
        System.arraycopy(this.array, 0, new_array, 0, this.size);
        this.array = new_array;
        this.capacity += DEFAULT_INC_SIZE;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<>(this);
    }
}
