package com.xtremeglory.data_structure.impls.independent.list;

import com.xtremeglory.data_structure.List;
import com.xtremeglory.data_structure.impls.independent.UnknownStorageStructureException;
import com.xtremeglory.data_structure.impls.iteration.list.ArrayList;
import com.xtremeglory.data_structure.impls.recursion.list.LinkedList;

public class Queue<E> {
    private final List<E> queue;

    public Queue(ListStorageStructure structure) throws UnknownStorageStructureException {
        if (structure.equals(ListStorageStructure.ARRAY)) {
            this.queue = new ArrayList<>();
        } else if (structure.equals(ListStorageStructure.LINKED)) {
            this.queue = new LinkedList<>();
        } else {
            throw new UnknownStorageStructureException();
        }
    }

    public Queue(){
        this.queue=new ArrayList<>();
    }

    /**
     * 判断是否为空栈
     *
     * @return 是否为空栈
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * 获取栈元素数量
     *
     * @return 元素数量
     */
    public int size() {
        return this.queue.size();
    }


    /**
     * 入队
     *
     * @param elem 入队元素
     */
    public void enqueue(E elem) {
        this.queue.insertLast(elem);
    }

    /**
     * 入队
     *
     * @param elem 入队元素
     */
    public void enqueueIfNotNull(E elem) {
        if (elem != null) {
            this.enqueue(elem);
        }
    }

    /**
     * 获取队首元素,而不弹出
     *
     * @return 队首元素
     */
    public E front() {
        if (!isEmpty()) {
            return this.queue.getFirst();
        } else {
            return null;
        }
    }

    /**
     * 弹出队首元素
     *
     * @return 队首元素
     */
    public E pop() {
        if (!isEmpty()) {
            return this.queue.removeFirst();
        } else {
            return null;
        }
    }
}
