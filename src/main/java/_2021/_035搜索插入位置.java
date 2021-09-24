package _2021;

public class _035搜索插入位置 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(searchInsert(nums, 3));
    }

    public static int searchInsert(int[] nums, int target) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] < target) {
                i++;
                continue;
            }
            break;
        }
        return i;
    }
}
