package data_structure.line.stack;

import data_structure.line.list.ArrayList;
import data_structure.line.list.List;

import java.util.EmptyStackException;

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
            throw new EmptyStackException();
        }
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            return stack.removeLast();
        }else {
            throw new EmptyStackException();
        }
    }
}
