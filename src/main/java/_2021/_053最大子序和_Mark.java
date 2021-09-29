package _2021;

public class _053最大子序和_Mark {

    public static void main(String[] args) {
        int[] nums = {6,7,-1,8};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int cnt = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = sum > 0 ? sum + nums[i] : nums[i];
            cnt = Math.max(cnt, sum);
        }
        return cnt;
    }

    public static int maxSubArray2(int[] nums) {
        int result = nums[0];
        int max = result;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] + result >= result) {
                if (result < 0) {
                    result = nums[i];
                } else {
                    result += nums[i];
                }
            } else if (nums[i] + result < result){
                if (nums[i] + result >= 0) {
                    result += nums[i];
                } else {
                    result = nums[i];
                }
            }
            if (result > max) {
                max = result;
            }
        }
        return max;
    }
}
