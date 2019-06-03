package array;

import java.util.Arrays;

/**
 * @program: LeetCode Study
 * @description: Summation of two numbers  两数求和为目标数,难度：简单
 * @author: ZengGuangfu
 * @create 2019-04-04 16:59
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

    示例:
    给定 nums = [2, 7, 11, 15], target = 9
    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]
 */
public class SummationOfTwoNumbers {

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{1, 2, 7, 34,23,45}, 25);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    /**
     * 我是用双重循环做的
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int sum;

        flag: for (int i=0,leng = nums.length  ; i< leng ; i++) {
            int j = i + 1;
            for (; j < leng ; j++){
                sum = nums[i] + nums[j];
                if (sum == target){
                    result[0] = i;
                    result[1] = j;
                    break flag;
                }
            }
        }
        return result;
    }

    /**
     * 这是我第一次执行的方法，不过忘了题目数组不是排序的，如果是有序数组我倒是觉得可行
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Arrays.sort(nums);
        int end = nums.length - 1,start = 0, base, sum;

        while(end > start){
            base = nums[start];
            sum = nums[end] + base;
            if ( sum > target){
                end--;
            }else if (sum < target ){
                start++;
            }else{
                result[0] = start;
                result[1] = end;
                break;
            }
        }
        return result;
    }
}
