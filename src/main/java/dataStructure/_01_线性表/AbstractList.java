package dataStructure._01_线性表;

/**
 * author: ZGF
 */

public abstract class AbstractList<E> implements DynamicGather<E> {

    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 默认可以有null值
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

}
