package com.chapter.work4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LRU {

    private LinkedList<Integer> list;
    private LinkedList<Integer> queue = new LinkedList<Integer>();
    private int hitRate = 0;
    private int lose = 0;
    private int memory = 16;

    public LRU(LinkedList<Integer> list) {
        this.list = list;
        LRU();
    }

    public void LRU() {
        Iterator<Integer> integerIterator = list.iterator();
        //如果指令不为空就继续打印
        while (integerIterator.hasNext()) {
            boolean hit = false;
            //每次取出指令后删除
            Integer integer = integerIterator.next();
            //判断是否为第一次
            if (queue.isEmpty()) {
                queue.addFirst(integer);
                out(queue, hit);
            } else {
                //命中页面的索引
                int index = 0;
                //判断是否能命中
                Iterator<Integer> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    Integer next = iterator.next();
                    if (integer.equals(next)) {
                        hit = true;
                        break;
                    } else
                        index++;
                }
                /**
                 * 如果队列遍历到最后没有命中,且页面没满则考虑将页面加入内存
                 */
                if (!hit) {
                    lose++;
                    //内存没放满,将新页面加入队尾
                    if (queue.size() < memory)
                        queue.addLast(integer);
                    else {
                        //如果内存已满则删除头元素并将新元素加入队尾
                        queue.removeFirst();
                        queue.addLast(integer);
                    }
                } else {
                    //如果命中了则输出
                    hitRate++;
                    Integer move = queue.get(index);
                    queue.remove(index);
                    queue.addFirst(move);
                }
                out(queue, hit);
            }
        }
        double lo = lose * 1.00 / (hitRate + lose);
        System.out.println("缺失次数为 " + lose + " 命中次数为 " + hitRate + " 缺页率为 " + lo);
    }

    private void out(Queue<Integer> queue, boolean b) {
        //打印队列
        for (int i : queue) {
            System.out.print(i + " ");
        }
        System.out.print(" 页表大小" + queue.size() + " ");
        //如果命中则输出
        if (b)
            System.out.print("hit");
        System.out.println();
    }
}
