package com.chapter.work2;

public class Global {
    public static Semaphore RW_mutex = new Semaphore(1);  //写者与其他写者或读者互斥的访问共享数据
    public static Semaphore R_mutex = new Semaphore(1);  //读者互斥的访问读者计数器
    public static int RC = 0;  //对读者进行计数

    //随机等待
    public static void naps() {
        try {
            Thread.sleep((int) (1000 * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
