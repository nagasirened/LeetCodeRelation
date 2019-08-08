package array.s;

import javax.sound.midi.Track;

/**
 * @program: LeetCode Study
 * @description: 第三大的数
 * @author: ZengGuangfu
 * @create 2019-08-07 13:44
 *
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

示例 1:
输入: [3, 2, 1]
输出: 1
解释: 第三大的数是 1.

示例 2:
输入: [1, 2]
输出: 2
解释: 第三大的数不存在, 所以返回最大的数 2 .

示例 3:
输入: [2, 2, 3, 1]
输出: 1
解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。

存在两个值为2的数，它们都排第二。
 */
public class ThirdMax {

    public static void main(String[] args) {
        int[] nums = {1,-2147483648,2};
        int result = thirdMax(nums);
        System.out.println(result);
    }

    /**
     * 第三大的数，从第一个开始数
     */
    public static int thirdMax(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int index = 0;
        boolean flag = true;  // 如果传入的数字中是Integer.MIN_VALUE  那么只处理一次
        for (int i = 0; i < nums.length ; i++){
            int num = nums[i];
            if (num == Integer.MIN_VALUE && flag){
                index++;
                flag = false;
            }

            if (num > max1){
                index++;
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if (num > max2 && num < max1){
                index++;
                max3 = max2;
                max2 = num;
            }else if (num > max3 && num < max2){
                index++;
                max3 = num;
            }
        }
        return index > 2 ? max3 : max1;
    }
}
