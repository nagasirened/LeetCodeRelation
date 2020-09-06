package algorithm.sort;

import java.util.Arrays;

/**
 * author: ZGF
 * 09-2020/9/4 : 22:54
 * context : 归并排序
 *
 * 思想：分而治之
 */

public class MergeSort {

    public static void main(String[] args) {
        int[] arg = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int[] temp = new int[arg.length];
        System.out.println(Arrays.toString(split(arg, 0, arg.length - 1, temp)));
    }

    /**
     * 相当于拆分为很小单元的                                                                      0000000000000000000000
     * @param arr
     * @param left
     * @param right
     * @param temp
     * @return
     */
    public static int[] split(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            split(arr, left, mid, temp);
            split(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
        return arr;
    }

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转的数组
     * @return
     */
    public static int[] merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;  // 初始化i
        int j = mid + 1;    // 初始化j，右边 有序序列的初始索引
        int t =  0;   // 指向temp表的当前索引

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        // 可能仅剩前面的和仅剩后面的
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        // (三) 将temp数组的元素拷贝到arr, 并不是每次都拷贝所有 0-1， 2-3 --->  0-3
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft += 1;
        }


        return null;
    }
}
