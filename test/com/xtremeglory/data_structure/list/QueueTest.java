package com.xtremeglory.data_structure.list;

import com.xtremeglory.data_structure.Queue;
import com.xtremeglory.data_structure.impls.iteration.list.ArrayList;

public class QueueTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Queue<Integer> queue = new Queue<Integer>(ArrayList.class);
        queue.enqueue(5);
        System.out.println(queue.front());
    }
}
