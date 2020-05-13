package array.s;

/**
 * @program: LeetCode Study
 * @description: 加一
 * @author: ZengGuangfu
 * @create 2019-09-12 13:33
 *  给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
    最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
    你可以假设除了整数 0 之外，这个整数不会以零开头。

    示例 1:
    输入: [1,2,3]
    输出: [1,2,4]
    解释: 输入数组表示数字 123。

    示例 2:
    输入: [4,3,2,1]
    输出: [4,3,2,2]
    解释: 输入数组表示数字 4321。
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] digits = {9,9,7,9};
        int[] result = plusOne(digits);
        for (int i : result) {
            System.out.println(i);
        }
    }

    /**
     * 啥意思，数字+1，然后返回结果？
     * 如果输入[9]，返回应该是[1,0]
     *
     * 一次过，状态不错嘛
     * 执行用时 :1 ms, 在所有 Java 提交中击败了96.60%的用户
     内存消耗 :36.2 MB, 在所有 Java 提交中击败了36.58%的用户
     */
    public static int[] plusOne(int[] digits) {
        for (int i= digits.length-1; i>=0; i--){
            digits[i] += 1;
            if (digits[i] <= 9){
                return digits;
            }else{
                digits[i] = 0;
                if ( i == 0){
                    int[] base = new int[digits.length + 1];
                    base[0] = 1;
                    System.arraycopy(base,1,digits,0,digits.length-1);
                    return base;
                }
            }
        }
        return null;
    }
}
