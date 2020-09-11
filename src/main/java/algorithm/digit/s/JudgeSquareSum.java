package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 平方数之和
 * @author: ZengGuangfu
 * @create 2019-08-12 09:35
 *
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。

    示例1:
    输入: 5
    输出: True
    解释: 1 * 1 + 2 * 2 = 5
     
    示例2:
    输入: 3
    输出: False
 */
public class JudgeSquareSum {

    public static void main(String[] args) {
        System.out.println(judgeSquareSum2(4));
    }

    /**
     * 双指针对法，a从0 开始遍历，b从max开始遍历
     * 执行用时 ：5 ms , 在所有 Java 提交中击败了86.97% 的用户
       内存消耗 : 34.4 MB, 在所有 Java 提交中击败了81.88% 的用户
     */
    public static boolean judgeSquareSum2(int c) {
        boolean flag = false;
        double sqrt = Math.sqrt(c);
        int b = (int) sqrt;
        int a = 0;
        if (b == sqrt) return true;
        while(a<=b){
            if ((a*a + b*b) <c ){
                a++;
            }else if((a*a + b*b) > c ){
                b--;
            }else{
                flag = true;
                break;
            }
        }

        return flag;
    }

    /**
     * 这种死办法因为耗时过长，被系统pass掉了。
     */
    public static boolean judgeSquareSum(int c) {
        double sqrt = Math.sqrt(c);
        if ((int)sqrt == sqrt) return true;
        boolean flag = false;
        for (int i=0; i<=sqrt; i++){
            for (int j=0; j<=sqrt; j++){
                if ((j*j + i*i) == c){
                    flag = true;
                }
            }
        }
        return flag;
    }


}
