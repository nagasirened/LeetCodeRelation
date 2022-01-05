package leetcode;

/**
 * author: ZGF
 * context : 斐波那契数
 * 描述：后面一个数字是前面两个数字的和
 *      0 1 1 2 3 5 8 13...  (下标是从0开始)
 */

public class _509_斐波那契数 {


    public static void main(String[] args) {
        System.out.println(fib3(64));
    }
    /**
     * 1. 求取第n个斐波那契数的值
     * @param n 第几个数（下标是从0开始）
     *
     *      递归： fn = f(n-1) + f(n-2)    （递归性能差）
     *      时间复杂度：f(2^n)
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n -2);
    }

    /**
     * 正序叠加. 性能好，进遍历一次，复杂度 n
     * 时间复杂度O(n)
     * @param n
     * @return
     */
    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int first  = 0;
        int second  = 1;
        /**
         * first 是相加的第一个数，second 是相加的第二个数，
         *   下一次遍历中，上一次的second是下一次的first
         *      相加之后的数，是下一次的second
         */
        int mid;
        for (int i = 0; i < n - 1; i++) {
            mid = first + second;
            first = second;
            second = mid;
        }
        return second;
    }


    /**
     * fib2 的变种，不要中间数
     * @param n
     * @return
     */
    public static int fib3(int n) {
        if (n <= 1) {
            return n;
        }
        int first  = 0;
        int second  = 1;
        // for (int i = 0; i < n - 1; i++)
        while(n-- > 1) {
            second += first;
            first = second - first;
        }
        return second;
    }
}
