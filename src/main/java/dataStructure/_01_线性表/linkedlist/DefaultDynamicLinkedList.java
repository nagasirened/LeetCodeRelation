package dataStructure._01_线性表.linkedlist;

import dataStructure._01_线性表.AbstractList;

/**
 * author: ZGF
 * context : 自定义一个单向链表
 */

public class DefaultDynamicLinkedList<E> extends AbstractList<E> {

    private int size;

    private Node<E> head;

    /** 静态内部类 */
    private static class Node<E> {
        /* 存储的数据 */
        E element;

        /* 指向下一个节点 */
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next    = next;
        }
    }

    /**
     * 例子：要分析下标为1，就循环一次，获取element
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        judgeIndex(index);
        Node<E> node = getIndexNode(index);
        return node.element;
    }

    /**
     * 要替换下标为1，就循环一次，替换element并
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        judgeIndex(index);
        Node<E> node = getIndexNode(index);
        // 返回原来的值
        E result = node.element;
        node.element = element;
        return result;
    }

    /**
     * 在index位置插入数据，要找到index前面那个数，也就是需要next (index - 1)次
     */
    @Override
    public void add(int index, E element) {
        if (index == 0) {
            head = new Node<>(element, head);
            size++;
            return;
        }
        judgeIndexForAdd(index);
        // 需要获取
        Node<E> base = getIndexNode(index - 1);
        // 包装后面的新节点
        base.next = new Node<>(element, base.next);
        size++;
    }

    @Override
    public E remove(int index) {
        judgeIndex(index);
        Node<E> node = head;
        if (index == 0) {
            head = head.next;
        } else {
            // index - 1 获取要去除的节点的前一个节点
            Node<E> prev = getIndexNode(index - 1);
            node= prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = this.head;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return -1;
    }

    @Override public void clear() {
        head.next = null;
        size = 0;
    }

    /**
     * 例子：获取下标为2的，需要从head 出发，next两次
     * @param index
     * @return
     */
    private Node<E> getIndexNode(int index) {
        Node<E> base = head;
        for (int i = 0; i < index; i++) {
            base = base.next;
        }
        return base;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = this.head;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断下标超出执行范围 （改、删、查方法）
     * @param index
     */
    protected void judgeIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Array subscript out of bounds");
        }
    }

    protected void judgeIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Array subscript out of bounds");
        }
    }

    /**
     * elements[size++] = element;
     * 需要考虑扩容，统一在下面的方法中处理即可
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    public static void main(String[] args) {
        // 删除链表中的某个节点

    }
}