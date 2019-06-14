package com.chapter.work3;

import java.util.Scanner;

import static com.chapter.work3.tool.Out;

public class Main {

    public Main() {
        init();
    }

    private void init() {
        Scanner in = new Scanner(System.in);

        System.out.println("请输入进程个数：");
        int n = in.nextInt();

        Process[] p = new Process[n];

        System.out.println("请输入每个进程的到达时间和服务时间和进程ID:");

        //初始化进程数据
        for (int i = 0; i < n; i++) {
            int arrTime = in.nextInt();
            int serTime = in.nextInt();
            String pid = in.nextLine();
            p[i] = new Process(arrTime, serTime, pid);
        }

        while (true) {
            System.out.println("请选择进程调度算法，1：FCFS 2:SJF 3:RR");
            String select = in.nextLine();
            switch (select) {
                case "1":
                    System.out.println("----------------您选择了FCFS-------------------");
                    FCFS.FCFS(p);
                    Out(p);
                    break;
                case "2":
                    System.out.println("----------------您选择了SJF-------------------");
                    Process[] processes = SJF.SJF(p);
                    Out(processes);
                    break;
                case "3":
                    System.out.println("----------------您选择了RR-------------------");
                    RR.RR(p);
                    break;
                default:
                    return;
            }
        }
    }

}
