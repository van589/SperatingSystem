package com.chapter.work3;

import java.util.LinkedList;
import java.util.List;

import static com.chapter.work3.tool.FindNextPro;
import static com.chapter.work3.tool.InsertSort;

public class SJF {

    public static Process[] SJF(Process[] p) {
        //当前时间
        int now = 0;
        //待处理list
        List<Process> list = new LinkedList<>();
        //结果list
        List<Process> res = new LinkedList<>();
        //按时间对进程进行排序
        InsertSort(p);

        //处理第一个进程
        p[0].finishTime = p[0].arrivalTime + p[0].serviceTime;
        p[0].WholeTime = p[0].finishTime - p[0].arrivalTime;
        p[0].weightWholeTime = p[0].WholeTime / p[0].serviceTime;
        res.add(p[0]);

        now = p[0].finishTime;

        //将剩余进程添加进待处理list
        for (int i = 1; i < p.length; i++) {
            list.add(p[i]);
        }

        while (list.size() != 0) {
            Process next = FindNextPro(list, now);
            if (next.arrivalTime > now) {
                next.finishTime = next.arrivalTime + next.serviceTime;
                next.startTime = next.arrivalTime;
            } else {
                next.finishTime = now + next.serviceTime;
                next.startTime = now;
            }
            now = next.finishTime;
            next.WholeTime = next.finishTime - next.arrivalTime;
            next.weightWholeTime = (double) next.WholeTime / (double) next.serviceTime;
            res.add(next);
        }

        return res.toArray(new Process[0]);

    }
}
