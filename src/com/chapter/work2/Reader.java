package com.chapter.work2;

public class Reader implements Runnable {
    @Override
    public void run() {
        Global.naps();
        System.out.println(Thread.currentThread().getName() + "  等待...");
        Global.R_mutex.P();
        if (Global.RC == 0) {
            Global.RW_mutex.P();
        }
        Global.RC++;
        Global.R_mutex.V();
        System.out.println(Thread.currentThread().getName() + "  开始读...");
        Global.naps();
        System.out.println(Thread.currentThread().getName() + "  离开...");
        Global.R_mutex.P();
        Global.RC--;
        if (Global.RC == 0) {
            Global.RW_mutex.V();
        }
        Global.R_mutex.V();
    }
}