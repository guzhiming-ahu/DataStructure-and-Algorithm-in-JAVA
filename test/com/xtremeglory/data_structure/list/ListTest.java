package com.xtremeglory.data_structure.list;

import com.xtremeglory.data_structure.List;
import com.xtremeglory.data_structure.impls.iteration.list.ArrayList;
import com.xtremeglory.data_structure.impls.recursion.list.LinkedList;

public class ListTest {
    public static List<Integer> list=new ArrayList<>();


    public static void main(String[] args) {
        insertTest();
        removeTest();

        insertTest1();
        removeTest();

        insertTest1();
        removeTest1();

        insertTest1();
        removeTest2();
    }

    public static void insertTest(){
        for (int i=0;i<220;i++){
            list.insertLast(i);
        }
        printTest("insertTest");
    }

    public static void insertTest1(){
        for (int i=0;i<30;i++){
            list.insertLast(i);
        }
        printTest("insertTest1");
    }

    public static void printTest(String func){
        System.out.println("######"+func+"#######");
        for (int i:list){
            System.out.println(i);
        }
    }

    //从末尾开始全部删除
    public static void removeTest(){
        int len=list.size();
        for (int i=0;i<len;++i){
            list.removeLast();
        }
        printTest("removeTest");
    }

    //从开头开始全部删除
    public static void removeTest1(){
        int len=list.size();
        for (int i=0;i<len;++i){
            list.removeFirst();
        }
        printTest("removeTest1");
    }

    //删除所有余3位置的元素
    public static void removeTest2(){
        int len=list.size();
        int removed=0;
        for (int i=0;i<len;++i){
            if (i%3==0) {
                list.remove(i-removed);
                removed++;
            }
        }
        printTest("removeTest2");
    }
}
