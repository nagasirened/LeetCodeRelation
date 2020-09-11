package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 第N个数字
 * @author: ZengGuangfu
 * @create 2019-08-12 10:18
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
    注意:
    n 是正数且在32为整形范围内 ( n < 231)。

    示例 1:
    输入: 3
    输出: 3

    示例 2:
    输入: 11
    输出: 0
    说明:
    第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 */
public class FindNthDigit {

    public static void main(String[] args) {
        System.out.println(findNthDigit(13));
    }

    /**
     * 这题出得真的有点上头
        1 * 9 * 1    = 9；
        2 * 9 * 10   = 180；
        3 * 9 * 100  = 2700；
     */
    public static int findNthDigit(int n) {
        // 先判断n属于哪一个区间
        int q = 1;
        int base = 1;
        int m = n;
        while(q*9*base < m){
            m -= q*9*base;
            base *= 10;
            q++;
        }
        int curNum = (int) (base + (m - 1) / q);
        return (int)((curNum + "").charAt(  (int)((m - 1) % q)) - '0');
    }
}
