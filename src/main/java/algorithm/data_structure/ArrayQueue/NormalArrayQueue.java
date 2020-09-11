package algorithm.data_structure.ArrayQueue;

import java.util.Scanner;

/**
 * author: ZGF
 * 05-2020/5/11 : 22:54
 * context : 用数组模拟队列
 *
 * 弊端：数组的每个下标只能用一次，没有达到复用的效果，改进方式；
 * 改进方式：将数组改成 模拟环形队列 CircleArrayQueue
 */

public class NormalArrayQueue{

    public static void main(String[] args) {
        ArrQueue arrQueue = new ArrQueue(3);
        NormalArrayQueue.run(arrQueue);
    }

    public static void run(MyQueue queue){
        char key = ' ';    //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        System.out.println("s(show)：显示队列");
        System.out.println("e(exit)：退出程序");
        System.out.println("a(add)：添加一条数据");
        System.out.println("g(get)：从队列中获取数据");
        System.out.println("h(head)：显示队列头数据");
        while (loop){
            try {
                key = scanner.next().charAt(0);
                switch (key){
                    case 's':
                        queue.show();
                        break;
                    case 'a':
                        System.out.println("输入一个数字");
                        int n = scanner.nextInt();
                        queue.addQueue(n);
                        break;
                    case 'g':
                        System.out.println("取出的数据是：" + queue.pop());
                        break;
                    case 'h':
                        System.out.println("显示头数据是：" + queue.head());
                        break;
                    case 'e':
                        scanner.close();
                        loop = false;
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("程序退出");
    }
}


/**
 * front和rear分别记录队列前后端的下标--双指针标记
 * front随着数据输出而改变，rear随着数据输入而改变
 */
class ArrQueue implements MyQueue {
    /** 表达数组的最大容量 */
    private int maxSize;

    /** 队列头 实际指向队列头的前一个位置*/
    private int front;

    /** 队列尾 实际指向队列尾的当前位置*/
    private int rear;

    /** 用于存放数据，模拟队列 */
    private int[] arr;

    public ArrQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    // 判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n){
        if (isFull()){
            throw new RuntimeException("队列已满，不能加入数据");
        }
        arr[++rear] = n;
    }

    // 数据出列
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有可以获取的数据");
        }
        return arr[++front];
    }

    // 显示队列的所有数据
    public void show(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据展示");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据，不是显示
    public int head(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的喔");
        }
        return arr[front + 1];
    }
}

