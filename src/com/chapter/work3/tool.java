package com.chapter.work3;

import java.text.DecimalFormat;
import java.util.List;

public class tool {
    //插入排序
    public static void InsertSort(Process[] array) {
        int i, j;
        int n = array.length;
        Process target;
        for (i = 1; i < n; i++) {
            j = i;
            target = array[i];
            while (j > 0 && target.arrivalTime < array[j - 1].arrivalTime) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = target;
        }
    }

    //找出下一个处理的进程
    public static Process FindNextPro(List<Process> list, int now) {
        Process pro = list.get(0);
        int index = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).serviceTime < pro.serviceTime && list.get(i).arrivalTime < now) {
                pro = list.get(i);
                index = i;
            }
        }
        list.remove(index);
        return pro;
    }


    //输出
    public static void Out(Process[] p) {
        DecimalFormat df = new DecimalFormat("#.00");
        float sumWT = 0;
        float sumWWT = 0;
        float AverageWT;
        float AverageWWT;
        for (int i = 0; i < p.length; i++) {
            System.out.println("时刻" + p[i].startTime + ": 进程" + p[i].pid + "开始运行，完成时间为:" + p[i].finishTime + ",周转时间为：" + p[i].WholeTime + ",带权周转时间为：" + df.format(p[i].weightWholeTime));
            sumWT += p[i].WholeTime;
            sumWWT += p[i].weightWholeTime;
        }
        AverageWT = sumWT / p.length;
        AverageWWT = sumWWT / p.length;

        System.out.println("平均周转时间为：" + df.format(AverageWT));
        System.out.println("平均带权周转时间为：" + df.format(AverageWWT));
        System.out.println("---------------------------------------------------------");
    }

}
