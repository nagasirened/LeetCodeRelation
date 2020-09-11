package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 给定数字能组成的最大时间
 * @author: ZengGuangfu
 * @create 2019-08-08 09:38
 *
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
    最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。
    以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。
    示例 1：
    输入：[1,2,3,4]
    输出："23:41"

    示例 2：
    输入：[5,5,5,5]
    输出：""

 */
public class LargestTimeFromDigits {

    public static void main(String[] args) {
        int A[] = {0,0,4,0};
        System.out.println(largestTimeFromDigits(A));
    }

    public static String largestTimeFromDigits(int[] A) {
        //4*3*2 = 24 种排列方式中取最大值
        int result = Integer.MIN_VALUE;
        int base = Integer.MIN_VALUE;
        for (int i=0; i<A.length; i++){
            for (int j=0; j<A.length; j++){
                if (i == j) {  continue; }
                for (int p=0; p<A.length; p++){
                    if (p==j || p==i) { continue; }
                    for (int q=0; q<A.length; q++){
                        if (q==j || q==i || q==p){ continue; }

                        base = A[i]*1000 + A[j]*100 + A[p]*10 + A[q];
                        if (base/100 < 24 && base%100 < 60){
                            result = Math.max(result,base);
                        }
                    }
                }
            }
        }
        if (result < 0) return "";
        String s = String.valueOf(result);
        if(s.length() == 1) s="000"+s;
        if(s.length() == 2) s="00"+s;
        if(s.length() == 3) s="0"+s;
        return s.substring(0,2) + ":" + s.substring(2) ;
    }
}
