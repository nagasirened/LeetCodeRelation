package array.s;

/**
 * @program: LeetCode Study
 * @description: 按奇偶排序数组,难度：简单
 * @author: ZengGuangfu
 * @create 2019-06-03 14:20
 */

import java.util.Arrays;

/**
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
   你可以返回满足此条件的任何数组作为答案。

 示例：
 输入：[3,1,2,4]
 输出：[2,4,3,1]
 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。

 提示：
 1 <= A.length <= 5000
 0 <= A[i] <= 5000
 */

public class SortArrayByParity {

    public static void main(String[] args) {
        int[] A = {3,1,2,4};
        int[] results = sortArrayByParity(A);
        for (int result: results) {
            System.out.print(result + "   ");
        }
    }


    /**
     * 执行用时 : 3 ms, 在Sort Array By Parity的Java提交中击败了98.32% 的用户
       内存消耗 : 43.1 MB, 在Sort Array By Parity的Java提交中击败了79.71% 的用户

       一次成功；题目即让所有的偶数排在前面，奇数排在后面即可。
       我的方法是遍历数组，用位运算判断奇偶，然后将偶数排在前面start++，奇数排在后面limit--
     * @param A
     * @return
     */

    public static int[] sortArrayByParity(int[] A) {
        int limit = A.length - 1;
        int start = 0;
        int[] B = Arrays.copyOf(A, A.length);
        for (int i=0; i < A.length; i++){
            if ((A[i] & 1 )!=0 ){
                B[limit] = A[i];
                limit--;
            }else{
                B[start] = A[i];
                start++;
            }
        }
        return B;
    }
}
