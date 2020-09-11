package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 买卖股票的最佳时机
 * @author: ZengGuangfu
 * @create 2019-09-12 15:05
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
    注意你不能在买入股票前卖出股票。
    示例 1:
    输入: [7,1,5,3,6,4]
    输出: 5
    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

    示例 2:
    输入: [7,6,4,3,1]
    输出: 0
    解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。


 */
public class MaxProfit {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        //int[] prices = {7,6,4,3,1};
        System.out.println( maxProfit(prices) );
    }

    /**
     *  题目要求，即使取数组中的两个数，用后面的数减去前面的数获取非负数的最大值
     *  如果差值小于0 ，那么按0来计算
     *  疯狂一次过
     *
     *  执行用时 :2 ms, 在所有 Java 提交中击败了98.39%的用户
     *  内存消耗 :38.9 MB, 在所有 Java 提交中击败了35.25%的用户
     */

    public static int maxProfit(int[] prices) {
        int result = 0;
        if (prices.length < 2) return result;
        int base = prices[0];
        for(int i=1; i<prices.length; i++){
            if (prices[i] <= base){
                base = prices[i];
            }else{
                if (prices[i] - base > result){
                    result = prices[i] - base;
                }
            }
        }
        return result;
    }
}
