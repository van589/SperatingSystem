package com.chapter.work3;

public class Process {
    public int arrivalTime; //到达时间
    public int serviceTime; //服务时间
    public int finishTime;  //完成时间
    public int startTime;   //开始时间
    public int WholeTime;   //周转时间
    public double weightWholeTime;  //带权周转时间
    public int remainserviceTime;   //剩余服务时间
    public int waitTime;      //等待
    public String pid;  //线程名称

    public Process(int arrivalTime, int serviceTime, String pid) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.pid = pid;
    }
}
