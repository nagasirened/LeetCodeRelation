package dataStructure._01_线性表.circlelinkedlist;

import dataStructure._01_线性表.AbstractList;

/**
 * author: ZGF
 * context : 单向循环链表
 */

public class DefaultSingleCircleLinkedList<E> extends AbstractList<E> {

    private int size;

    private Node<E> first;

    /** 静态内部类 */
    private static class Node<E> {
        /* 存储的数据 */
        E element;

        /* 指向下一个节点 */
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     *
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
     * index = 0 的时候，需要将最后一个Node的指针指向当前Node
     */
    @Override
    public void add(int index, E element) {
        judgeIndexForAdd(index);
        if (index == 0) {
            first = new Node<>(element, first);
            Node<E> indexNode = size == 0 ? first : getIndexNode(size);
            indexNode.next = first;
        } else {
            Node<E> prev = getIndexNode(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        judgeIndex(index);
        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = getIndexNode(size - 1);
                first = first.next;
                last.next = first;
            }
        } else {
            Node<E> prev = getIndexNode(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node temp = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (temp.element == null) {
                    return i;
                }
                temp = temp.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(temp.element)) {
                    return i;
                }
                temp = temp.next;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        first = first.next = null;
        size = 0;
    }

    /**
     * 如果索引位于小于索引的一半，从first遍历  反之反着遍历
     */
    private Node<E> getIndexNode(int index) {
        Node<E> base = first;
        for (int i = 0; i < index; i++) {
            base = base.next;
        }
        return base;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(node.element + "_" +node.next.element);
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
        this.add(size, element);
    }

    public static void main(String[] args) {
        DefaultSingleCircleLinkedList<Integer> list = new DefaultSingleCircleLinkedList<>();
        list.add(111);
        list.add(222);
        list.add(333);
        list.clear();
        list.add(444);
        list.add(555);
        System.out.println(list.toString());
    }
}