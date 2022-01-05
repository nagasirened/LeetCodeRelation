package leetcode;

public class _026删除有序数组中的重复项 {

    /**
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,3,4,6,6,9};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 1;
        int length = nums.length;
        while (j < length) {
            if (nums[i] == nums[j]) {
                j++;
                continue;
            }
            nums[++i] = nums[j];
            j++;
        }
        return i + 1;
    }
}
