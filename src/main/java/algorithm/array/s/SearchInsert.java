package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 搜索插入位置
 * @author: ZengGuangfu
 * @create 2019-09-12 13:11
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
   你可以假设数组中无重复元素。
    示例 1:
    输入: [1,3,5,6], 5
    输出: 2
    示例 2:
    输入: [1,3,5,6], 2
    输出: 1
    示例 3:
    输入: [1,3,5,6], 7
    输出: 4
    示例 4:
    输入: [1,3,5,6], 0
    输出: 0
 */
public class SearchInsert {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 0;
        int result = searchInsert(nums, target);
        System.out.println(result);
    }

    /**
     * 给定的数组是已经被排序了的，这样可以用单指针比较即可吧
     */
    public static int searchInsert(int[] nums, int target) {
        for(int i=0; i<nums.length ; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        // 如果所有数字都比target小，那么就返回长度的下标
        return nums.length;
    }
}
