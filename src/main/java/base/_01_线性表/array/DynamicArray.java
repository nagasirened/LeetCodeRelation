package base._01_线性表.array;

/**
 * author: ZGF
 * context : 动态数组
 *
 * 通常创建数组是无法扩容的，我们可以自定义一个动态数组
 */

public interface DynamicArray<E> {

    int size();

    boolean isEmpty();

    boolean contains(E element);

    void add(E element);

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(E element);

    void clear();
}
