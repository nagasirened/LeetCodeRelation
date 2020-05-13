package array.s;

/**
 * @program: LeetCode Study
 * @description: 最大子序和
 * @author: ZengGuangfu
 * @create 2019-09-12 13:23
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    示例:
    输入: [-2,1,-3,4,-1,2,1,-5,4],
    输出: 6
    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

进阶:
如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = maxSubArray(nums);
        System.out.println(result);
    }

    /**
     * 只要求最大值即可
     * 从第一个开始遍历，累计最大值。这个最大值始终用一个指针记录
     * 如果遇到相加的结果是非正数，结果就清零重新累加
     * 如果遇到更大的和的值，就替换掉之前的最大值
     *
     * 最大的问题是可能数组所有内容都是负数，记住要将maxSum初始化为第一个数
     *   执行用时 : 2 ms, 在所有 Java 提交中击败了89.70%的用户
       内存消耗 :38.4 MB, 在所有 Java 提交中击败了85.15%的用户
     */
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = 0;
        for (int i=0; i<nums.length; i++){
            sum += nums[i];
            if (sum > maxSum){
                maxSum = sum;
            }
            if (sum <= 0){
                sum = 0;
            }
        }
        return maxSum;
    }
}
