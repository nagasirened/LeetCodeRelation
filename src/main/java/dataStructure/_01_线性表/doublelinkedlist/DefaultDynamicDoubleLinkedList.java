package dataStructure._01_线性表.doublelinkedlist;

import dataStructure._01_线性表.AbstractList;

/**
 * author: ZGF
 * context : 自定义一个单向链表
 */

public class DefaultDynamicDoubleLinkedList<E> extends AbstractList<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;

    /** 静态内部类 */
    private static class Node<E> {
        /* 存储的数据 */
        E element;

        /* 指向上一个节点 */
        Node<E> prev;

        /* 指向下一个节点 */
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     *
     */
    @Override
    public E get(int index) {
        judgeIndex(index);
        Node<E> indexNode = getIndexNode(index);
        return indexNode.element;
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
        Node<E> indexNode = getIndexNode(index);
        E oldElement = indexNode.element;
        indexNode.element = element;
        return oldElement;
    }

    /**
     * 在index位置插入数据，要找到index前面那个数，也就是需要next (index - 1)次
     */
    @Override
    public void add(int index, E element) {
        judgeIndexForAdd(index);
        // 如果index=size，如果原始没有数据，可能会造成oldlast == null的情况
        if (index == size) {
            Node<E> oldLast = this.last;
            last = new Node<>(element, oldLast, null);
            if (oldLast == null) {
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = getIndexNode(index);
            Node<E> prev = next.prev;
            Node<E> eNode = new Node<>(element, prev, next);
            next.prev = eNode;
            // 如果index=0， prev==null
            if (prev == null) {
                first = eNode;
            } else {
                prev.next = eNode;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        judgeIndex(index);
        Node<E> eNode = getIndexNode(index);
        Node<E> prev = eNode.prev;
        Node<E> next = eNode.next;
        if (prev == null) {     // index==0
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {     // index == (size - 1)
            last = prev;
        } else {
            next.prev = prev;
        }
        size--;
        return eNode.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> temp = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (temp.element == null) {
                    return i;
                }
                temp = temp.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (temp.element.equals(element)) {
                    return i;
                }
                temp = temp.next;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        // 前端这段非必要，但是可以防止有迭代器的指针引用导致内存不被回收
        Node temp;
        for (Node<E> item = first; item != null; item = temp) {
            temp = item.next;
            item.prev = null;
            item.element = null;
            item.next = null;
        }
        size  = 0;
        first = last = null;
    }

    /**
     * 如果索引位于小于索引的一半，从first遍历  反之反着遍历
     */
    private Node<E> getIndexNode(int index) {
        if (index < (size >> 1)) {
            Node node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node node = last;
            // 1-2-3-4-5  取2的话，从4开始前移两次
            for (int i = size - 1; i > index ; i++) {
                node = node.prev;
            }
            return node;
        }
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
        this.add(size, element);
    }

}