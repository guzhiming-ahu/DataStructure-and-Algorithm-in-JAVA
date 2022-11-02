package com.xtremeglory.data_structure.impls.recursion.list;

import com.xtremeglory.data_structure.Stack;
import com.xtremeglory.data_structure.impls.iteration.list.ArrayList;

public class LinkedStack<E> extends Stack<E> {
    public LinkedStack() {
        super(new ArrayList<>());
    }

    /**
     * 入栈
     *
     * @param elem 入栈元素
     */
    public void push(E elem) {
        this.stack.prepend(elem);
    }

    /**
     * 获取栈顶元素,而不弹出
     *
     * @return 栈顶元素
     */
    public E top() {
        return !isEmpty() ? this.stack.front() : null;
    }

    /**
     * 弹出栈顶元素
     *
     * @return 栈顶元素
     */
    public E pop() {
        return !isEmpty() ? this.stack.deque() : null;
    }
}
