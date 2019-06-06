package array.s;

/**
 * @program: LeetCode Study
 * @description: 数组拆分1 ，难度：简单
 * @author: ZengGuangfu
 * @create 2019-06-03 16:05
 */

import java.util.Arrays;

/**
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
 * 使得从1 到 n 的 min(ai, bi) 总和最大。
 *
 示例：
     输入: [1,4,3,2]
     输出: 4
     解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 提示：
     n 是正整数,范围在 [1, 10000].
     数组中的元素范围在 [-10000, 10000].
 */
public class ArrayPairSum {
    public static void main(String[] args) {
        int[] nums = {1,4,3,2};
        int result = arrayPairSum(nums);
        System.out.println(result);
    }

    /**
     * 思路：将数组按照从小到大排序，步进2计算总和即可
     * 执行用时 : 37 ms, 在Array Partition I的Java提交中击败了50.99% 的用户
       内存消耗 : 48.8 MB, 在Array Partition I的Java提交中击败了64.76% 的用户
     */
    public static int arrayPairSum(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length ; i=i+2){
            sum +=  nums[i];
        }
        return sum;
    }
}
