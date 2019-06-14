package com.chapter.work3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static com.chapter.work3.tool.InsertSort;

public class FCFS {

    //先来先服务算法
    public static void FCFS(Process[] p) {

        //按到达时间对进程进行排序
        InsertSort(p);

        for (int i = 0; i < p.length; i++) {
            //计算完成时间
            if (i == 0) {
                p[i].finishTime = p[i].arrivalTime + p[i].serviceTime;
            } else {
                if (p[i].arrivalTime > p[i - 1].finishTime) {
                    p[i].finishTime = p[i].arrivalTime + p[i].serviceTime;
                    p[i].startTime = p[i].arrivalTime;
                } else {
                    p[i].finishTime = p[i].serviceTime + p[i - 1].finishTime;
                    p[i].startTime = p[i - 1].finishTime;
                }
            }

            //计算周转时间和带权周转时间
            p[i].WholeTime = p[i].finishTime - p[i].arrivalTime;
            p[i].weightWholeTime = (double) p[i].WholeTime / (double) p[i].serviceTime;

        }
    }

}