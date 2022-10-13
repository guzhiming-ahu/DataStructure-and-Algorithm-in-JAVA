package com.xtremeglory.data_structure.impls.independent.list;

import com.xtremeglory.data_structure.List;
import com.xtremeglory.data_structure.impls.iteration.list.ArrayList;


public class Stack<E> {
    private final List<E> stack;

    public Stack(Class<? extends List> clazz) throws InstantiationException, IllegalAccessException {
        this.stack = clazz.newInstance();
    }

    public Stack() {
        this.stack = new ArrayList<>();
    }

    /**
     * 判断是否为空栈
     *
     * @return 是否为空栈
     */
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * 获取栈元素数量
     *
     * @return 元素数量
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * 入栈
     *
     * @param elem 入栈元素
     */
    public void push(E elem) {
        this.stack.insertLast(elem);
    }

    /**
     * 入栈
     *
     * @param elem 入栈元素
     */
    public void pushIfNotNull(E elem) {
        if (elem != null) {
            this.push(elem);
        }
    }

    /**
     * 获取栈顶元素,而不弹出
     *
     * @return 栈顶元素
     */
    public E top() {
        if (!isEmpty()) {
            return this.stack.getLast();
        } else {
            return null;
        }
    }

    /**
     * 弹出栈顶元素
     *
     * @return 栈顶元素
     */
    public E pop() {
        if (!isEmpty()) {
            return this.stack.removeLast();
        } else {
            return null;
        }
    }
}
