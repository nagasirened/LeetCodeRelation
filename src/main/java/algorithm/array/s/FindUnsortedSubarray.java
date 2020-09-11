package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 最短无序连续子数组
 * @author: ZengGuangfu
 * @create 2019-08-07 14:41
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
    你找到的子数组应是最短的，请输出它的长度。

    示例 1:
    输入: [2, 6, 4, 8, 10, 9, 15]
    输出: 5
    解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
    说明 :
    输入的数组长度范围在 [1, 10,000]。
    输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
public class FindUnsortedSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        //int[] nums = {1, 3, 2, 3, 3};
        //int[] nums = {1, 2, 1, 1, 1};
        //int[] nums = {1, 2, 3, 4};
        //int[] nums= {2,1};
        int result = findUnsortedSubarray(nums);
        System.out.println(result);
    }

    /**
     * 使用一个指针记录第一次有减数的时候的下标
     * 再使用一个指针，记录最后一次有减数的下标
     */
    public static int findUnsortedSubarray(int[] nums) {
        if (nums.length < 2) return 0;
        int start = 0;
        int end = 1;
        int startNum = Integer.MIN_VALUE;
        int endNum = Integer.MAX_VALUE;

        for (int i=0; i<nums.length; i++){
            if (i==0){
                startNum = nums[i];
            }else{
                if (nums[i] < startNum){
                    start = i;
                }
                startNum = Math.max(startNum,nums[i]);
            }

        }

        for (int j= nums.length - 1; j >= 0; j--){
            if (j == nums.length -1){
                endNum = nums[j];
            }else{
                if (nums[j] > endNum){
                    end = j;
                }
                endNum = Math.min(endNum,nums[j]);
            }

        }

        return start - end + 1;
    }
}
