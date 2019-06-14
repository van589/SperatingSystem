package com.chapter.work2;

import java.util.Scanner;

public class ReaderWriterProblem {


    public ReaderWriterProblem() {
        init();
    }

    private void init() {
        int wr, re;
        Scanner input = new Scanner(System.in);
        System.out.print("请输入写者数目：");
        wr = input.nextInt();
        System.out.print("请输入读者数目：");
        re = input.nextInt();
        // 实例化读者与写者对象
        for (int i = 0; i < wr; i++) {
            new Thread(new Writer(), "写者线程r" + Integer.toString(i))
                    .start();
        }
        for (int i = 0; i < re; i++) {
            new Thread(new Reader(), "读者线程r" + Integer.toString(i))
                    .start();
        }
    }
}