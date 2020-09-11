package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 计算质数
 * @author: ZengGuangfu
 * @create 2019-08-07 16:53
 *
 * 统计所有小于非负整数 n 的质数的数量。
    示例:
    输入: 10
    输出: 4
    解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class CountPrimes {

    public static void main(String[] args) {
        System.out.println(countPrimes2(2));
    }

    // 我写的这破玩意儿超时了
    public static int countPrimes(int n) {
        int result = n - 2;
        if(n <= 2) return 0;
        for (int i = 3; i < n ; i++){
            for (int j = 2; j < i ; j++){
                if (i % j == 0){
                    result--;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 如求10之内的质数，首先列出2~N-1的所有数，如果当前数为质数，则其倍数就是质数，如

     第一个质数为2，在2上画圈，其倍数4/6/8不是质数，划掉4/6/8，继续遍历
     下一个质数为3，在3上画圈，其倍数6/9不是质数，划掉6/9，继续遍历
     下一个质数为5，在5上画圈，没有倍数，继续遍历
     下一个质数为7，在7上画圈，没有倍数，继续遍历。
     最后再次遍历整个数组，画圈的数字就是质数，即2,3,5,7

     转换为代码就是如果需要求<n的所有质数个数，则创建一个长度为n的整数数组，所有元素值变为1，1表示对应的索引值为质数，0表示对应的索引值为非质数。从2开始遍历，如果当前数字值为1，则获取其所有倍数，将元素值变为0（标记为非质数）。遍历完成后再次遍历数组，从2开始，记录元素为1的个数，即为对应的质数个数。
     */
    public static int countPrimes2(int n) {
        if (n<=2) return 0;
        int[] base = new int[n];
        // 全部的数都当做质数变成1 先
        for (int i = 0 ; i<base.length; i++){
            base[i] = 1;
        }

        // 从2开始，如果当前数是质数，那么就把它的倍数全部变成非质数
        for (int i= 2; i < n; i++){
            // 只判断质数，第一个数2肯定是质数
            if (base[1] == 1){
                // j 作为质数的乘数
                for (int j = 2; i*j <n ; j++){
                    base[i*j] = 0;
                }
            }
        }

        // 数一下base里面有多少个1就行
        int count = 0;
        for (int num : base){
            if (num == 1){
                count++;
            }
        }

        return count -2;
    }


}
