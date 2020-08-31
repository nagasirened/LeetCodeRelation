package SwordFingerOffer;

import java.util.Arrays;

/**
 * author: ZGF
 * 08-2020/8/31 : 14:17
 * context :  剑指 Offer 03. 数组中重复的数字
 找出数组中重复的数字。
 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

 示例 1：

 输入：
 [2, 3, 1, 0, 2, 5, 3]
 输出：2 或 3
 */

public class Question03 {

    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]){
                return nums[i];
            }
        }
        return 0;
    }
}
