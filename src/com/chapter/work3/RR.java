package com.chapter.work3;

import java.util.Scanner;

import static com.chapter.work3.tool.InsertSort;

public class RR {


    public static void RR(Process[] p) {
        //按到达时间对进程进行排序
        InsertSort(p);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入时间片大小");
        int timeSlice = sc.nextInt();   //设置时间片
        int timeNow = 0;    //当前时间
        int processNumber = p.length;   //线程数量
        int processRemain = p.length;  //剩余线程数量
        Process[] process = new Process[p.length];
        for (int i = 0; i < processNumber; i++) {
            process[i] = p[i];
            process[i].remainserviceTime = process[i].serviceTime;
        }
        while (processRemain != 0) {
            //对每个线程计算
            for (int i = 0; i < processNumber; i++) {
                process[i] = p[i];

                /**
                 *  如果剩余服务时间大于0且现在时间大于或等于到达时间,
                 *  则继续对线程进行服务
                 */
                if (process[i].remainserviceTime > 0
                        && timeNow >= process[i].arrivalTime) {

                    /**
                     * 如果剩余服务时间等于总服务时间,
                     * 则判断该线程为第一次执行
                     */
                    if (process[i].serviceTime == process[i].remainserviceTime) {
                        int waitTime = timeNow - process[i].arrivalTime;
                        process[i].startTime = timeNow;
                        process[i].waitTime = waitTime;
                    }

                    //计算剩余的服务时间
                    int remainServiceTime = process[i].remainserviceTime - timeSlice;

                    if (remainServiceTime <= 0) {
                        //计算完成时间
                        process[i].finishTime = timeNow + process[i].remainserviceTime;
                        //计算周转时间
                        process[i].WholeTime = process[i].finishTime - process[i].arrivalTime;
                        //计算带权周转时间
                        process[i].weightWholeTime = 1.0 * process[i].WholeTime / process[i].serviceTime;
                        //剩余线程数减一
                        processRemain--;
                        timeNow += process[i].serviceTime;
                        process[i].remainserviceTime = 0;
                    } else {
                        timeNow += timeSlice;
                        process[i].remainserviceTime = remainServiceTime;
                    }
                    System.out.println("    #STEP# Process" + (i + 1)
                            + " remain service time:"
                            + process[i].remainserviceTime
                            + " , timeBefore:" + (timeNow - 1) + ", timeNow:"
                            + timeNow
                            + ((remainServiceTime <= 0) ? " Finish" : ""));
                }
            }

        }
        tool.Out(process);
    }
}
