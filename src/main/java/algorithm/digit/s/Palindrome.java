package algorithm.digit.s;


/**
 * @program: LeetCode Study
 * @description: 判断是否回文数
 * @author: ZengGuangfu
 * @create 2019-04-04 19:51
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
    输入: 121
    输出: true
    示例 2:

    输入: -121
    输出: false
    解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    示例 3:

    输入: 10
    输出: false
    解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class Palindrome {
    public static void main(String[] args) {
        boolean palindrome = isPalindrome2(121);
        System.out.println(palindrome);
    }

    /**
     *  刚好做了一个题就是反序，于是就直接拿出来使了，不过这样做稍显麻烦，我为啥要转换回Integer呢，直接字符串判断不就得了
     */
    public static boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }else {
            StringBuilder builder = new StringBuilder();
            builder.append(x);
            builder.reverse();
            try{
                Integer integer = Integer.valueOf(builder.toString());
                if (integer == x){
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }
    }

    public static boolean isPalindrome2(int x) {
        StringBuilder builder = new StringBuilder();
        builder.append(x);
        if (builder.reverse().toString().equals(String.valueOf(x)))
            return true;
        return false;
    }

}
