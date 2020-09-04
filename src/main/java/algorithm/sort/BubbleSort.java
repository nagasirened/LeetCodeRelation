package algorithm.sort;

import java.util.Arrays;

/**
 * <p>
 *
 * @description: 冒泡排序
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/16
 * @return: algorithm.sort.BubbleSort
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arg = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int[] ints = bubbleSort(arg);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 冒泡排序，两两交换
     * 可以优化一下（在一次遍历中，没有任何交换，认定依据完成了排序，可直接返回）
     */
    public static int[] bubbleSort(int[] arr){
        if (arr.length <= 1) {
            return arr;
        }
        int temp;
        boolean flag = false;
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                return arr;
            }else {
                flag = false;
            }
        }
        return arr;
    }
}
