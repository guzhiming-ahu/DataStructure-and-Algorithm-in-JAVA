package com.xtremeglory.problem.list;

import com.xtremeglory.data_structure.List;

public class Josephus {
    public static int rf(List<Integer> list, int gap) {
        int index = gap - 1;
        while (list.size() != 1) {
            list.remove(index);
            index = (index + gap - 1) % list.size();
        }
        return list.front();
    }
}
