package dataStructure._01_线性表.arraylist;

import dataStructure._01_线性表.AbstractList;

import java.util.Objects;

/**
 * author: ZGF
 * context : 动态数组的实现，可设定初始大小，默认最小长度为10
 */

public class DefaultDynamicArray<E> extends AbstractList<E> {

    private int size;

    private E[] elements;

    public static final int DEFAULT_CAPACITY = 10;
    /**
     * 构造器
     * @param capaticy 容量，设置为最小为10
     */
    public DefaultDynamicArray(int capaticy) {
        capaticy = Math.max(capaticy, DEFAULT_CAPACITY);
        elements = (E[])new Object[capaticy];
    }

    public DefaultDynamicArray(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 获取对应下标元素
     */
    public E get(int index) {
        judgeIndex(index);
        return elements[index];
    }

    /**
     * 替换对应下标元素，返回原来的元素
     */
    public E set(int index, E element) {
        judgeIndex(index);
        E oldone = elements[index];
        elements[index] = element;
        return oldone;
    }

    /**
     * elements[size++] = element;
     * 需要考虑扩容，统一在下面的方法中处理即可
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * 后面的数往后挪，靠后的先挪，遍历倒序
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        // 判断index的时候，是允许index==size，相当于在尾巴插入
        judgeIndexForAdd(index);
        reDilatation();

        for (int i = size - 1; i >= index ; i--) {
            elements[i + 1] = elements[i];
        }
        size ++;
        elements[index] = element;
    }

    /**
     * 删除了中间数据之后，后面的需要往前挪，先挪靠前的，遍历正序
     * @param index
     * @return
     */
    public E remove(int index) {
        judgeIndex(index);
        E oldElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        // 先减，再清空多余的那个引用对象
        elements[--size] = null;
        return oldElement;
    }

    /**
     * 允许为null, 判断时要注意equals 空指针的情况
     * @param element
     * @return
     */
    public int indexOf(E element) {
        if (Objects.isNull(element)) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * 如果仅仅是 size = 0,
     * E 如果是引用的对象并没有真正的销毁，对象还是存在不被回收器回收
     *
     * 如果使用elements=null, 数组也没有了，下次使用还需要重新创建
     */
    public void clear() {
        // 增加一个情况对象的操作，放弃对引用对象的所有引用
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 满足条件则扩容
     */
    private void reDilatation(){
        int oldCapacity = elements.length;
        if (size + 1 > oldCapacity) {
            // 扩容为2倍  int newCapacity = oldCapacity << 1;
            // 扩容1.5倍, tip下面括号加上
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            E[] newElements =(E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
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
}
