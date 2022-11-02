package com.xtremeglory.data_structure.impls.iteration.list;

import com.xtremeglory.data_structure.List;
import com.xtremeglory.data_structure.impls.recursion.list.LinkedList;
import com.xtremeglory.problem.list.Josephus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {

    private static final int INITSIZE = 200;
    private static final int INSERT_INDEX = 120;

    private static List[] lists;


    @BeforeEach
    public void before() {
        lists = new List[]{new ArrayList<Integer>(), new LinkedList<Integer>(), new StaticLinkedList<Integer>(INITSIZE)};
    }

    @AfterEach
    public void after() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertFirst() {
    }

    @Test
    void insertLast() {
    }

    @Test
    void remove() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }

    @Test
    void set() {
    }

    @Test
    void get() {
    }

    @Test
    void getFirst() {
    }

    @Test
    void getLast() {
    }

    @Test
    void size() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void compact() {
    }

    @Test
    void resize() {
    }

    @Test
    void reverse() {
        int size = INITSIZE;
        for (List<Integer> list : lists) {
            for (int i = 0; i < size; ++i) {
                list.append(i);
            }
            for (int i = 0; i < size - 1; ++i) {
                assert list.get(i) < list.get(i + 1);
            }
            list.reverse();
            for (int i = 0; i < size - 1; ++i) {
                assert list.get(i) > list.get(i + 1);
            }
            list.reverse();
            for (int i = 0; i < size - 1; ++i) {
                assert list.get(i) < list.get(i + 1);
            }
        }
    }

    @Test
    void josephus() {
        int[][] array = new int[][]{
                {30, 5, 2},
                {11, 3, 6},
                {19, 1, 18}
        };
        for (List<Integer> list : lists) {
            System.out.println(list);
            for (int i = 0; i < array.length; ++i) {
                System.out.printf("%d,%d,%d\n", array[i][0], array[i][1], array[i][2]);
                for (int k = 0; k < array[i][0]; ++k) {
                    list.append(k);
                }
                assert Josephus.rf(list, array[i][1]) == array[i][2];
                list.removeAll();
            }
        }
    }

    @Test
    void iterator() {
    }
}