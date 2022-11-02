package com.xtremeglory.data_structure;

public interface List<E> extends Iterable<E> {
    //底层接口
    int size();

    boolean isEmpty();

    /**
     * 使线性表更加紧凑，节省内存
     * 对于不支持紧凑的数据结构， 将返回false
     *
     * @return 是否执行此操作
     */
    boolean compact();

    //存取接口
    void insert(int index, E e);

    void prepend(E e);

    void append(E e);

    E remove(int index);

    void removeAll();

    E deque();

    E pop();

    void set(int index, E e);

    E get(int index);

    E front();

    E tail();

    //查询接口
    int indexOf(E e);

    /**
     * 翻转线性表，
     */
    void reverse();
}
