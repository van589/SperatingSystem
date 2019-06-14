import com.chapter.work1.ProducerConsumerProblem;
import com.chapter.work2.ReaderWriterProblem;
import com.chapter.work3.FCFS;
import com.chapter.work3.Process;
import com.chapter.work3.RR;
import com.chapter.work3.SJF;
import com.chapter.work5.Bank;

import java.util.Random;
import java.util.Scanner;

import static com.chapter.work3.tool.Out;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String select = null;
        while (true) {
            System.out.println("请选择进程调度算法，1：生产者消费者\t2:读者写者\t3:进程管理\t4:存储管理\t5:设备管理");
            select = in.nextLine();
            switch (select) {
                case "1":
                    System.out.println("----------------生产者消费者-------------------");
                    new ProducerConsumerProblem();
                    break;
                case "2":
                    System.out.println("----------------读者写者-------------------");
                    new ReaderWriterProblem();
                    break;
                case "3":
                    System.out.println("----------------进程管理-------------------");
                    new com.chapter.work3.Main();
                    break;
                case "4":
                    System.out.println("----------------存储管理-------------------");
                    new com.chapter.work4.Main();
                    break;
                case "5":
                    System.out.println("----------------设备管理-------------------");
                    new Bank();
                    break;
                default:
                    return;
            }
        }
    }
}