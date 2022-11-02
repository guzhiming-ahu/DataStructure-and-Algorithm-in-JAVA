package com.xtremeglory.data_structure;


public abstract class Stack<E> {
    protected final List<E> stack;

    public Stack(List<E> stack) {
        this.stack = stack;
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
    public abstract void push(E elem);

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
    public abstract E top();

    /**
     * 弹出栈顶元素
     *
     * @return 栈顶元素
     */
    public abstract E pop();
}
