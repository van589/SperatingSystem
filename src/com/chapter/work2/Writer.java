package com.chapter.work2;

public class Writer implements Runnable {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        Global.naps();
        System.out.println(Thread.currentThread().getName() + "  等待...");
        Global.RW_mutex.P();
        System.out.println(Thread.currentThread().getName() + "  开始写...");
        Global.naps();
        System.out.println(Thread.currentThread().getName() + "  离开...");
        Global.RW_mutex.V();
    }
}