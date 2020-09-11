package algorithm.data_structure.ArrayQueue;

/**
 * author: ZGF
 * 05-2020/5/12 : 9:27
 * context :
 */

public interface MyQueue {

    public boolean isFull();

    public boolean isEmpty();

    public void addQueue(int n);

    public int pop();

    public void show();

    public int head();
}
