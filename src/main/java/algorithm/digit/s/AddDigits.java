package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 各位相加
 * @author: ZengGuangfu
 * @create 2019-08-19 14:09
 *
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
    示例:
    输入: 38
    输出: 2
    解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。

    假设一个数
 */
public class AddDigits {

    public static void main(String[] args) {
        addDigits(38);
    }

    /**
     * f(100*z+x*10+y)=f(z*99+x*9+z+x+y)=f(z+x+y)
     * 99*z + 9*y  差值可以背9整除，余下的就是 X+Y+Z的值，如果大于9还要再除一次即可
     *
     */
    public static int addDigits(int num) {
        if (num > 9){
            num = num % 9;
            if (num == 0)
            return 9;
        }
        return num;
    }

    public static int addDigits2(int num){
        //先减一后加一是为了防止 数字9的影响
        return (num - 1) % 9 + 1;
    }
}
