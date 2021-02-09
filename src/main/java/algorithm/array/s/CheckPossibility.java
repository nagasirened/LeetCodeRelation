package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 非递减数列
 * @author: ZengGuangfu
 * @create 2019-08-05 13:18
 */

/**
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 algorithm.arraylist[i] <= algorithm.arraylist[i + 1]。

 示例：
 输入: [4,2,3]
 输出: True
 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。

 输入: [4,2,1]
 输出: False
 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 */
public class CheckPossibility {

    public static void main(String[] args) {
        int[] nums = {3,4,2,3};
        System.out.println(checkPossibility(nums));
    }

    /**
     * 这个题是简单数组中胜率最低的，没有想象的简单。第一次以为每相邻的数比较，如果有两次前数大于后数就行，但是输给了3，4，2，3
     *
     * 判断思路：如果只有两个及以下的元素，一定是true;
     * 无论什么情况，都要给一次改过自新的机会，如果有多个元素，那么当 n > n+1 时，如果 n-1 也大于n+1 ，那么先把n+1 改成 n ，计数加一
     *                                                 当 n > n+1 时，如果 n-1 小于 n+1 ，那么把 n 换成 n-1。
     *
     *                                                这样这次机会就给完了。如果还有n > n+1 ，计数再加一肯定就返回false了。
     */
    public static boolean checkPossibility(int[] nums){
        if (nums.length < 2){
            return true;
        }
        int count = 0;
        if(nums[0] > nums[1]){
            count++;
            nums[0] = nums[1];
        }
        for (int i = 1; i< nums.length - 1; i++){
            if (nums[i] > nums[i+1]){
                count++;
                if(count > 1) return false;

                if(nums[i-1] > nums[i+1]){
                    nums[i+1] = nums[i];
                }else{
                    nums[i] = nums[i-1];
                }
            }
        }
        return count < 2;
    }

}
