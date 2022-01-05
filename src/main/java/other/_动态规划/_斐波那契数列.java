package other._动态规划;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

public class _斐波那契数列 {

    public static void main(String[] args) {
        StopWatch stopWatch = StopWatch.createStarted();
        System.out.println(olb(30));
        stopWatch.stop();
        System.out.println(stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    /**
     * 暴力递归 复杂度 2^N
     * 问题：有很多重复算子
     */
    public static int diGui(int n) {
        if (n == 1 || n == 2) return 1;
        return diGui(n - 1) + diGui(n - 2);
    }

    /**
     * 优化1：防止重复算子
     * 将计算的结果放进数组中 =====================================================================================================================
     */
    public static int flb(int n) {
        int[] vector = new int[n + 1];
        return helper(vector, n);
    }

    private static int helper(int[] vector, int n) {
        if (n == 1 || n == 2) return 1;
        if (vector[n] != 0) {
            return vector[n];
        }
        vector[n] = helper(vector, n - 1) + helper(vector, n - 2);
        return vector[n];
    }

    /** ===================================================================================================================== */

    /**
     * 优化2，从低到高的计算存储数组
     */
    public static int olb(int n) {
        // 第0位暂时没用
        int[] tem = new int[n + 1];
        tem[1] = 1; tem[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            tem[i] = tem[i - 1] + tem[i - 2];
        }
        return tem[n];
    }
}
