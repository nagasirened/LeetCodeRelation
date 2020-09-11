package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 斐波那契数，难度：简单
 * @author: ZengGuangfu
 * @create 2019-06-03 15:11
 */

/**
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     F(0) = 0,   F(1) = 1
     F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     给定 N，计算 F(N)。

   示例：
     输入：2
     输出：1
     解释：F(2) = F(1) + F(0) = 1 + 0 = 1.

     输入：3
     输出：2
     解释：F(3) = F(2) + F(1) = 1 + 1 = 2.

     输入：4
     输出：3
     解释：F(4) = F(3) + F(2) = 2 + 1 = 3.

   提示：
     0 ≤ N ≤ 30
 */


public class Fib {
    public static void main(String[] args) {
        int result = fib2(4);
        System.out.println(result);
    }

    /**
     * 递归算法，一次成功，但是效率低
     * 执行用时 : 16 ms, 在Fibonacci Number的Java提交中击败了29.41% 的用户
     * 内存消耗 : 32.6 MB, 在Fibonacci Number的Java提交中击败了78.68% 的用户
     */
    public static int fib(int N) {
        if (N == 0 || N == 1){
            return N;
        }
        int result = fib(N - 1) + fib(N - 2);
        return result;
    }

    /**
     * 这是抄袭的方法，值得学习
     * 替换法(0ms,效率最高,内存消耗都差不多)
     */
    public static int fib2(int N){
        if (N == 0 || N == 1) {
            return N;
        }
        int x = 0,y = 1,z = 0,i = 0,end = N-2;
        while (i <= end) {
            z = x + y;
            x = y;
            y = z;
            i++;
        }
        return z;
    }
}
