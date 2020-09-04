package algorithm.sort;

import java.util.Arrays;

/**
 * author: ZGF
 * 09-2020/9/3 : 10:20
 * context : 插入排序
 * 分割成一个有序列表和一个无序列表，开始有需要列表中只包含一个元素，无序列表中有n-1个元素
 * 排序过程中一次从无序列表中取出第一个元素与有序元素表进行比较，将之插入到有序表的适当位置，成为一个新的有序表
 */

public class InsertSort {

    public static void main(String[] args) {
        int[] arg = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int[] ints = insertSort(arg);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] insertSort(int[] arr) {
        int length = arr.length;
        if (length < 2) return arr;

        // i是外层无序列表，往有序列表里插入数据
        for (int i = 1; i < length; i++) {
            // 待插入的数
            int base = arr[i];
            // 待插入的数前面那个数的下标
            int index = i - 1;
            while (index >= 0 && base < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            /**
             * 挖坑实际上是给index + 1 挖的，我们新加入的数的坑挖出来（index + 1）
             * 如果比前面的小，就会让出来给前面的数，index -- 之后留下的坑还是index + 1
             */
            arr[index + 1] = base;
        }
        return arr;
    }
}
