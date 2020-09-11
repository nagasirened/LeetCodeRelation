package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 删除排序数组中的重复项
 * @author: ZengGuangfu
 * @create 2019-08-08 09:04
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例 1:

给定数组 nums = [1,1,2],

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

你不需要考虑数组中超出新长度后面的元素。
示例 2:

给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。
 *
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums ={0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    /**
     * 题目要求返回长度的时候，数组的前几个数字应该就是我们去重之后的元素标签，利用一个result作为下标去辅助
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int base = nums[0];
        int result = 1;
        for (int i=1 ; i<nums.length; i++){
            if (nums[i] != base){
                nums[result] = nums[i];
                result++;
                base = nums[i];
            }
        }
        return result;
    }
}
