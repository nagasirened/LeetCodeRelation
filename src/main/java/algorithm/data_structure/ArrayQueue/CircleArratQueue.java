package algorithm.data_structure.ArrayQueue;

/**
 * author: ZGF
 * 05-2020/5/12 : 8:50
 * context : 数组模拟环形队列
 *
 * 1. front变量的含义调整：front就只想队列的第一个U安苏，也就是说arr[front]就是队列的第一个元素，front的初始值为0
 * 2. rear变量的含义调整： rear指向队列的最后一个元素的后一个位置（希望空出一个空间作为约定），rear的初始值为0
 * 3. 当队列满时的条件是 (rear + 1) % maxSize = front TODO maxSize = 4的时候，front=2，rear = 1(第二圈) 有数据的是0,2,3   公式的值true
 * 4. 队列为空时的条件是： rear ==  front  TODO 因为出栈后front会+1
 * 5. 队列中的有效个数  (rear + maxSize - front) % maxSize
 * 6. 以上
 */

public class CircleArratQueue {

    public static void main(String[] args) {
        CirCleQueue cirCleQueue = new CirCleQueue(4);
        NormalArrayQueue.run(cirCleQueue);
    }
}


/**
 * front和rear分别记录队列前后端的下标--双指针标记
 * front随着数据输出而改变，rear随着数据输入而改变
 * front和rear的后移需要考虑取模
 */
class CirCleQueue implements MyQueue{
    /** 表达数组的最大容量 */
    private int maxSize;

    /** 队列头 实际指向队列头元素的当前位置*/
    private int front;

    /** 队列尾 实际指向队列尾元素的后一个位置*/
    private int rear;

    /** 用于存放数据，模拟队列 */
    private int[] arr;

    public CirCleQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    // 判断队列是否满
    public boolean isFull(){
        return ((rear + 1) % maxSize) == front;
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
        arr[rear] = n;
        // 此时rear需要后移，可能因为取模到前面来
        rear++;
        rear = rear % maxSize;
        System.out.println("rear的值是：" + rear);
    }

    // 数据出列
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有可以获取的数据");
        }
        int i = arr[front];
        // 修改 front的值
        front++;
        front = front % maxSize;
        System.out.println("front 的值是：" + front);
        return i;
    }

    // 因为可以循环使用，因此显示队列的所有数据是从front开始，遍历剩下的所有元素
    public void show(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据展示");
            return;
        }
        // 遍历有效数据的个数那么多个，如下面的方法
        for (int i = front; i < (front + effective()); i++) {
            int num = i %  maxSize;
            System.out.printf("arr[%d] = %d\n", num, arr[num]);
        }
    }

    // 求出有效数据的个数
    public int effective(){
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据，不是显示
    public int head(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的喔");
        }
        return arr[front];
    }
}
