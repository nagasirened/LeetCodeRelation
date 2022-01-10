package leetcode;

import java.util.Arrays;

public class _016最接近的三数之和 {

    public static void main(String[] args) {
        int[] nums = {-3,-2,-5,3,-4};
        int target = -1;
        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int length = nums.length;
        Arrays.sort(nums);
        int close = 10000000;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = length - 1;
            while (j < k) {
                int temp = nums[i] + nums[j] + nums[k];

                // 重要啊老弟
                if (Math.abs(temp - target) < Math.abs(close - target)) {
                    close = temp;
                }

                if (temp == target) {
                    return temp;
                } else if (temp > target) {
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else {
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                }
            }
        }
        return close;
    }
}
