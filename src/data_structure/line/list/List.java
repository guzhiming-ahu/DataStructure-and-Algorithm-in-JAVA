package data_structure.line.list;

public interface List<E> {
    void insert(int index, E e);

    void insertLast(E e);

    E remove(int index);

    E removeLast();

    void update(int index, E e);

    E get(int index);

    E getLast();

    int size();

    int indexOf(E e);

    boolean isEmpty();
}
