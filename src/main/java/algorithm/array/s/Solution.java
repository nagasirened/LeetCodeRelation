package algorithm.array.s;

import java.util.Arrays;

/**
 * @program: LeetCode Study
 * @description: 求众数,难度：简单
 * @author: ZengGuangfu
 * @create 2019-04-04 16:26
 * 题目：给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *      你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
    示例 1:
    输入: [3,2,3]
    输出: 3

    示例 2:
    输入: [2,2,1,1,1,2,2]
    输出: 2

    这是我刷题的第一道题，刚开始做题的时候会想到利用Map，key作为数组的内容，value为计数。这样做可行但是效率太低
    于是在评论中拜大佬，学习了摩尔投票法，如下
 */
public class Solution {

    public static void main(String[] args) {
        int result = majorityElement(new int[]{1, 23, 23, 1, 7, 1, 49, 1, 1});
        int result2 = majorityElement2(new int[]{1, 1, 1, 4, 4, 4, 4, 2, 2, 4, 4});
        System.out.println(result + "    " +result2);
    }

    /**
     * 摩尔投票法：将数组第一个数作为base基础值，如果后面遇到一个和它一样的数，默认的count计数器加1，遇到不一样的数则减1
     * 减一到0的时候，摒弃掉前面所有的数，将下一个数作为base基础。
     * 到最后计数器>0剩下的base一定是众数
     */
    public static int majorityElement(int[] nums){
        int base = nums[0];
        int count = 1;
        int length = nums.length;
        for (int i = 1 ; i < length ; i++){
            if (nums[i] != base){
                if ( --count == 0 && i < length){
                    base = nums[i+1];
                }
            }else{
                count++;
            }
        }
        return base;
    }

    /**
     * 数组排序，因为众数的数量超过数组的一般，最中间的数一定是众数
     */
    public static int majorityElement2(int[] nums){
        Arrays.sort(nums);
        int length = nums.length;
        return nums[length/2];
    }
}









