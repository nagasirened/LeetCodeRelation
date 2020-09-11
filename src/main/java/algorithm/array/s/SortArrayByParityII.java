package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 按奇偶排序数组 II
 * @author: ZengGuangfu
 * @create 2019-06-05 15:11
 */

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 示例：
     输入：[4,2,5,7]
     输出：[4,5,2,7]
     解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 */
public class SortArrayByParityII {
    public static void main(String[] args) {
        int[] A = {4,2,5,7};
        int[] result = sortArrayByParityII(A);
        for(int num : result){
            System.out.println(num + "   ");
        }
    }

    /**
     * 由于不需要大小顺序，我步进二就处理了
     */
    public static int[] sortArrayByParityII(int[] A) {
        int[] B = new int[A.length];
        int j = 0,k = 1;
        for (int i = 0 ; i < A.length ; i ++){
            if ((A[i] & 1) == 1){
                B[k] = A[i];
                k = k+2;
            }else{
                B[j] = A[i];
                j = j+2;
            }
        }
        return B;
    }
}
