package array.s;

/**
 * @program: LeetCode Study
 * @description: 买股票的最佳时机II
 * @author: ZengGuangfu
 * @create 2019-09-12 15:25
 */
public class MaxProfit_II {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        //int[] prices = {7,6,4,3,1};
        System.out.println( maxProfit(prices) );
    }

    /**
     * 这里引入一段大佬的思想：
     * [7, 1, 5, 6] 第二天买入，第四天卖出，收益最大（6-1），所以一般人可能会想，
     * 怎么判断不是第三天就卖出了呢? 这里就把问题复杂化了，
     * 根据题目的意思，当天卖出以后，当天还可以买入，所以其实可以第三天卖出，第三天买入，
     * 第四天又卖出（（5-1）+ （6-5） === 6 - 1）。
     * 所以算法可以直接简化为只要今天比昨天大，就卖出。
     *
     * 那这样的话，只要两个相邻的数，后面的数比前面的大，通通加起来就行了
     * 执行用时 :2 ms, 在所有 Java 提交中击败了97.36%的用户
     * 内存消耗 :38.7 MB, 在所有 Java 提交中击败了27.52%的用户
     */

    public static int maxProfit(int[] prices) {
        int maxSum = 0;
        for (int i=1; i<prices.length; i++){
            if (prices[i] > prices[i-1]){
                maxSum += prices[i] - prices[i-1];
            }
        }
        return maxSum;
    }
}
