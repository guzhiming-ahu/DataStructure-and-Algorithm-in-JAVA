package com.xtremeglory.data_structure.impls.iteration.list;

import com.xtremeglory.data_structure.List;
import com.xtremeglory.data_structure.Stack;

public class ArrayStack<T> implements Stack<T> {
    private final List<T> stack;

    public ArrayStack() {
        this.stack = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void push(T elem) {
        stack.insertLast(elem);
    }

    @Override
    public T top() {
        if (!isEmpty()) {
            return stack.getLast();
        } else {
            return null;
        }
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            return stack.removeLast();
        }else {
            return null;
        }
    }
}
