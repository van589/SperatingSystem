package com.chapter.work5;

public class Sources {
    private int a;
    private int b;
    private int c;

    public Sources() {
    }



    public Sources(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Sources{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
