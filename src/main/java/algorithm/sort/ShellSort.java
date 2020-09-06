package algorithm.sort;

import java.util.Arrays;

/**
 * author: ZGF
 * 09-2020/9/3 : 11:11
 * context : 希尔排序 （优化插入排序）
 */

public class ShellSort {

    public static void main(String[] args) {
        int[] arg = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int[] ints = shellSort2(arg);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 交换法排序  效率低下，因为数据交换耗时，效率类似冒泡排序，很慢
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return arr;
        }
        // 步进
        int step = length / 2;
        int base;
        while (step != 0) {
            for (int i = step; i < length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        base = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = base;
                    }
                }
            }
            step = step / 2;
        }
        return arr;
    }

    /**
     * 移位法  优化希尔排序，80000个数据1秒钟左右
     */
    public static int[] shellSort2(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return arr;
        }
        // 步进
        int base;
        // 最外层的for类似于上面的while
        for (int step = length / 2; step > 0 ; step /= 2) {
            // 从第step个元素开始，逐个对其所在的组直接进行插入排序
            for (int i = step; i < length; i++) {
                int j = i;
                int temp = arr[j];  // 临时变量

                // 这里类似于插入排序
                while (j - step >= 0 && temp < arr[j - step]) {
                    // 开始移动
                    arr[j] = arr[j - step];
                    j -= step;
                }
                // 退出while循环代表找到了插入的位置
                arr[j] = temp;
            }
        }
        return arr;
    }
}
