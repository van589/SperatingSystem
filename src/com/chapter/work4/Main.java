package com.chapter.work4;

import java.util.*;

public class Main {

    public Main() {
        init();
    }

    private void init() {
        Scanner in = new Scanner(System.in);

        System.out.println("请输入内存大小(kb)：");
        int memory = 16;
        System.out.println(memory);
//        int memory = in.nextInt();
        System.out.println("请输入页面大小(kb)：");
        int page = 4;
        System.out.println(page);
//        int page = in.nextInt();
        int pageNumber = memory / page;
        System.out.println("共有 " + pageNumber + " 页");
        System.out.println("产生随机指令160条");
        LinkedList<Integer> list = new LinkedList();
        for (int i = 0; i < 160; i++) {
            Random random = new Random();
            int rdNumber = random.nextInt(32) + 1;
            list.add(rdNumber);
        }

        while (true) {
            System.out.println("请选择页面置换算法，1：FIFO 2:LRU 3:LFU 其他键:quit");
            String select = in.nextLine();
            switch (select) {
                case "1":
                    System.out.println("----------------您选择了FIFO-------------------");
                    show(list);
                    new FIFO(list,page);
                    break;
                case "2":
                    System.out.println("----------------您选择了LRU-------------------");
                    show(list);
                    new LRU(list);
                    break;
                case "3":
                    System.out.println("----------------您选择了LFU-------------------");
                    show(list);
                    new LFU(list);
                    break;
                default:
                    return;
            }
        }

    }


    private static void show(LinkedList<Integer> list) {
        int flag = 0;
        //打印指令访问顺序
        for (Integer i : list) {
            System.out.print(i + " ");
            flag++;
            if (flag == 31) {
                System.out.println();
                flag = 0;
            }
        }
        System.out.println();
    }


}
