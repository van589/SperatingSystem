package com.chapter.work5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {

    private Sources[] allocation;
    private Sources[] need;
    private Sources Available = new Sources(3, 3, 2); //初始资源

    public Bank() {
        init();
    }

    private void init() {
        //资源数
        int processNum = 5;
        // 初始化进程数据
        allocation = new Sources[processNum];
        need = new Sources[processNum];
        //静态数据初始化
        int[][] allocationData = {{0, 1, 0}, {2, 0, 0}, {3, 0, 2}, {2, 1, 1},
                {0, 0, 2}};
        int[][] needData = {{7, 4, 3}, {1, 2, 2}, {6, 0, 0},
                {0, 1, 1}, {4, 3, 1}};

        for (int i = 0; i < processNum; i++) {
            allocation[i] = new Sources(allocationData[i][0], allocationData[i][1], allocationData[i][2]);
            need[i] = new Sources(needData[i][0], needData[i][1], needData[i][2]);
        }
        Process[] processors = new Process[processNum];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new Process();
            processors[i].setName("P" + i);
            processors[i].setAllocation(allocation[i]);
            processors[i].setNeed(need[i]);
        }
        //打印进程信息
        showdata(processors);
        Scanner s = new Scanner(System.in);
        //请求资源的序号
        int requestNum = 0;
        Sources requestSources = new Sources();
        while (true)// 循环进行分配
        {
            System.out.println("请输入要请求的进程号（0--4）：");
            requestNum = s.nextInt();
            System.out.println("请输入请求的资源数目:");
            requestSources.setA(s.nextInt());
            requestSources.setB(s.nextInt());
            requestSources.setC(s.nextInt());
            //对请求进行检查
            assign(processors, requestNum, requestSources);
            showdata(processors);
        }
    }

    /**
     * 对请求的资源进行检查，如果能进行资源分配则进行安全序列检查
     *
     * @param processors 进程集合
     * @param requestNum 请求的资源序列
     * @param req        请求的资源信息
     * @return
     */
    private boolean assign(Process[] processors, int requestNum, Sources req) {
        Process process = processors[requestNum];
        //如果Request[i,j]<=Need[i,j]则继续，否则认为出错
        if (!(req.getA() <= process.getNeed().getA()
                && req.getB() <= process.getNeed().getB()
                && req.getC() <= process.getNeed().getC())) {
            System.out.println("请求的资源数超过了所需要的最大值");
            return false;
        }
        //如果Request[i,j]<=Available[i,j]则继续，否则没有足够的资源
        if (!(req.getA() <= Available.getA()
                && req.getB() <= Available.getB()
                && req.getC() <= Available.getC())) {
            System.out.println("尚无足够资源分配，必须等待");
            return false;
        }
        /**
         * 分配资源并修改数据
         *      Available[i,j] = Available[i,j] - Request[i,j]
         *      Allocation[i,j] = Allocation[i,j] + Request[i,j]
         *      Need[i,j] = Need[i,j] - Request[i,j]
         */
        Available.setA(Available.getA() - req.getA());
        Available.setB(Available.getB() - req.getB());
        Available.setC(Available.getC() - req.getC());
        process.getAllocation().setA(process.getAllocation().getA() + req.getA());
        process.getAllocation().setB(process.getAllocation().getB() + req.getB());
        process.getAllocation().setC(process.getAllocation().getC() + req.getC());
        process.getNeed().setA(process.getNeed().getA() - req.getA());
        process.getNeed().setB(process.getNeed().getB() - req.getB());
        process.getNeed().setC(process.getNeed().getC() - req.getC());
        // 进行安全性检查并返回是否安全
        boolean flag = checkSafeQueue(processors, Available);
        if (flag == true) {
            System.out.println("能够安全分配");
            return true;
        } else {
            System.out.println("不能够安全分配");
            return false;
        }
    }


    /**
     * 进行安全序列检查,并返回是否安全
     *
     * @param processors 进程集合
     * @param available  可分配资源
     * @return
     */
    private boolean checkSafeQueue(Process[] processors, Sources available) {
        Sources temp = new Sources(available.getA(), available.getB(), available.getC());
        boolean assigned[] = new boolean[5];
        int i = 0;
        /**
         * 对进程集合里的进程进行查找是否有满足Need[i,j]<=Request[i,j]
         *如果有则记录并将记号归零，否则记号继续记录直到遍历完集合
         */
        while (i < 5) {
            if (!assigned[i] && temp.getA() >= processors[i].getNeed().getA()
                    && temp.getB() >= processors[i].getNeed().getB()
                    && temp.getC() >= processors[i].getNeed().getC()) {
                temp.setA(temp.getA() + processors[i].getAllocation().getA());
                temp.setB(temp.getB() + processors[i].getAllocation().getB());
                temp.setC(temp.getC() + processors[i].getAllocation().getC());
                System.out.println("分配成功的是:" + processors[i].getName());
                assigned[i] = true;
                i = 0;
            } else {
                i++;
            }
        }
        //如果有一个不能分配成功则返回false，否则返回true
        for (i = 0; i < 5; i++) {
            if (assigned[i] == false)
                return false;
        }
        return true;
    }

    /**
     * 显示当前系统和各个进程的资源使用情况
     * @param processors 进程集合
     */
    private void showdata(Process[] processors) {
        // 显示当前可用资源
        System.out.print("当前系统可分配资源为:");
        System.out.println("Available:\t" + Available.getA() + "\t" + Available.getB() + "\t" + Available.getC());
        System.out.println("-----------------进程状态-----------------");
        System.out.println("进程号\tAllocation\t\tNeed\t\t\t");
        System.out.println("\t\tA\tB\tC\t\tA\tB\tC");
        for (int i = 0; i < processors.length; i++) {
            System.out.print(processors[i].getName() + "\t\t");
            System.out.print(processors[i].getAllocation().getA() + "\t"
                    + processors[i].getAllocation().getB() + "\t"
                    + processors[i].getAllocation().getC() + "\t\t");
            System.out.println(processors[i].getNeed().getA() + "\t"
                    + processors[i].getNeed().getB() + "\t"
                    + processors[i].getNeed().getC() + "\t");
        }
        System.out.println("------------------------------------------");
    }
}
