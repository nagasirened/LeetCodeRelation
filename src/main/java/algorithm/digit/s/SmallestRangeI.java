package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 最小差值1
 * @author: ZengGuangfu
 * @create 2019-08-19 13:46
 *
 * 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择任意 x 满足 -K <= x <= K，并将 x 加到 A[i] 中。
   在此过程之后，我们得到一些数组 B。
   返回 B 的最大值和 B 的最小值之间可能存在的最小差值。

    示例 1：
    输入：A = [1], K = 0
    输出：0
    解释：B = [1]

    示例 2：
    输入：A = [0,10], K = 2
    输出：6
    解释：B = [2,8]

    示例 3：
    输入：A = [1,3,6], K = 3
    输出：0
    解释：B = [3,3,3] 或 B = [4,4,4]
 */
public class SmallestRangeI {

    public static void main(String[] args) {
        int[] A = {0,10};
        System.out.println(smallestRangeI(A,2));
    }

    /**
     * 这个题读了半天，蒙圈了。看的评论说大概是  “原来数组的一个或多个元素的值加上-K到K区间的值，使数组最大值和最小值的差值最小”
     *
     * 这样的话好办，就是数组的最小值加上+k,数组的最大值加上-k,让它们差值最小。
     */
    public static int smallestRangeI(int[] A, int K) {
        int max = A[0];
        int min = A[0];
        for (int i=1; i<A.length; i++){
            if (A[i] > max){
                max = Math.max(max,A[i]);
            }
            if (A[i] < min){
                min = Math.min(min,A[i]);
            }
        }
        int result = max - K - (min + K);
        return result <= 0 ? 0 : result;
    }
}
