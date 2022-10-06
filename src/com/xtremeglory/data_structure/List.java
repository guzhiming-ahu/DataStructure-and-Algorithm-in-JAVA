package com.xtremeglory.data_structure;

public interface List<E> {
    //底层接口
    int size();

    boolean isEmpty();

    /**
     * 使线性表更加紧凑，节省内存
     * 对于不支持紧凑的数据结构， 将返回false
     * @return 是否执行此操作
     */
    boolean compact();

    //存取接口
    void insert(int index, E e);

    void insertFirst(E e);

    void insertLast(E e);

    E remove(int index);

    E removeFirst();

    E removeLast();

    void set(int index, E e);

    E get(int index);

    E getFirst();

    E getLast();

    //查询接口
    int indexOf(E e);
}
