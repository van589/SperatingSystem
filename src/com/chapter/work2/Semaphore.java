package com.chapter.work2;

public class Semaphore {
    public int value;

    public Semaphore(int value) {
        super();
        this.value = value;
    }

    /**
     * p操作
     */
    public synchronized final void P() {
        value--;
        if (value < 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * v操作
     */
    public synchronized final void V() {
        value++;
        if (value <= 0) {
            this.notify();
        }
    }
}
