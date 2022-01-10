package leetcode;

import java.util.*;

public class _015三数之和 {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }

    /**
     * 暴力拆解,超时，漂亮
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        // 还是正排一下
        Arrays.sort(nums);
        for (int i = 0; i < length - 2; i++) {
            // 第一个必须为非正数
            if (nums[i] > 0)
                return res;
            // 如果i和它前面那一位一样，跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 双指针，一个从后，一个从前
            int j = i + 1;
            int k = length - 1;
            while (j < k) {
                int tem = nums[i] + nums[j] + nums[k];
                if (tem == 0) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[j]);
                    item.add(nums[k]);
                    res.add(item);
                    // 继续执行，防止重复结果
                    j++; k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (tem > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }


    /**
     * 暴力拆解,超时，漂亮
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // j << k
                        if (map.get(nums[i] + "_" + nums[j]) != null && map.get(nums[i] + "_" + nums[j]).equals(nums[k])) {
                            continue;
                        }
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[i]);
                        item.add(nums[j]);
                        item.add(nums[k]);
                        map.put(nums[i] + "_" + nums[j], nums[k]);
                        res.add(item);
                    }
                }
            }
        }
        return res;
    }
}
