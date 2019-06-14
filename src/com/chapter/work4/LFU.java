package com.chapter.work4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LFU {
    private LinkedList<Integer> list;
    private LinkedList<Node> queue = new LinkedList();
    private int hitRate = 0;
    private int lose = 0;
    private int memory = 16;

    public LFU(LinkedList<Integer> list) {
        this.list = list;
        LFU();
    }

    public void LFU() {
        Iterator<Integer> integerIterator = list.iterator();
        //如果指令不为空就继续打印
        while (integerIterator.hasNext()) {
            boolean hit = false;
            //每次取出指令后删除
            Integer integer = integerIterator.next();
            //判断是否为第一次
            if (queue.isEmpty()) {
                /**
                 * 设置节点的数据和访问次数
                 * 访问次数为当前次数++
                 */
                queue.addFirst(setNode(integer));
                out(queue, hit);
            } else {
                //命中页面的索引
                int index = 0;
                //判断是否能命中
                Iterator<Node> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    Node next = iterator.next();
                    if (integer.equals(next.getData())) {
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
                    if (queue.size() < memory) {
                        queue.addLast(setNode(integer));
                    } else {
                        //如果内存已满则删除头元素并将新元素加入队尾
                        queue.removeLast();
                        queue.addLast(setNode(integer));
                    }
                } else {
                    //如果命中了则判断
                    hitRate++;
                    Node node = queue.get(index);
                    node = getNode(node);
                    if (index == 0) {
                        queue.set(index, node);
                        System.out.println("queue.set\t" + node.getCurrent() + "\thitData\t" + node.getData());
                    } else {
                        queue.remove(index);
                        index--;
                        while (index != 0) {
                            int current = queue.get(index).getCurrent();
                            if (node.getCurrent() < current) {
                                queue.add(index, node);
                                System.out.println("node getCurrent\t" + node.getCurrent() + "\tqueue getCurrent\t" + current + "\thitData\t" + node.getData());
                                break;
                            }
                            index--;
                        }
                        if (index == 0) {
                            queue.addFirst(node);
                            System.out.println("node getCurrent\t" + node.getCurrent() + "\tindex0==\t" + queue.get(1).getCurrent() + "\thitData\t" + node.getData());
                        }
                    }
                }
                out(queue, hit);
            }
        }
        double lo = lose * 1.00 / (hitRate + lose);
        System.out.println("缺失次数为\t" + lose + "\t命中次数为\t" + hitRate + "\t缺页率为\t" + lo);
    }

    private void out(Queue<Node> queue, boolean b) {
        //打印队列
        for (Node i : queue) {
            System.out.print(i.getData() + " ");
        }
        System.out.print(" 页表大小" + queue.size() + " ");
        //如果命中则输出
        if (b)
            System.out.print("hit");
        System.out.println();
    }

    /**
     * 创建一个新的node节点,数据为参数integer,访问次数为当前次数为1
     *
     * @param integer 页面数据
     * @return 返回已创建好的Node节点
     */
    private Node setNode(Integer integer) {
        Node node = new Node();
        node.setData(integer);
        node.setCurrent(1);
        return node;
    }

    /**
     * 将当前node节点的访问次数++
     *
     * @param node 当前node节点
     * @return 返回添加次数后的node节点
     */
    private Node getNode(Node node) {
        int current = node.getCurrent();
        node.setCurrent(++current);
        return node;
    }

}

class Node {
    int current;    //记录当前访问次数
    int data;   //记录当前数据

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
