package leetcode;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _022括号生成_动态规划 {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(generateParenthesis(3)));
    }

    /**
     * 首先，面向小白：什么是动态规划？在此题中，动态规划的思想类似于数学归纳法，当知道所有 i<n 的情况时，我们可以通过某种算法算出 i=n 的情况。
     * 本题最核心的思想是，考虑 i=n 时相比 n-1 组括号增加的那一组括号的位置。
     *
     * 思路：
     * 当我们清楚所有 i<n 时括号的可能生成排列后，对与 i=n 的情况，我们考虑整个括号排列中最左边的括号。
     * 它一定是一个左括号，那么它可以和它对应的右括号组成一组完整的括号 "( )"，我们认为这一组是相比 n-1 增加进来的括号。
     *
     * 那么，剩下 n-1 组括号有可能在哪呢？
     *
     * 【这里是重点，请着重理解】
     *
     * 剩下的括号要么在这一组新增的括号内部，要么在这一组新增括号的外部（右侧）。
     *
     * 既然知道了 i<n 的情况，那我们就可以对所有情况进行遍历：
     *
     * "(" + 【i=p时所有括号的排列组合】 + ")" + 【i=q时所有括号的排列组合】
     *
     * 其中 p + q = n-1，且 p q 均为非负整数。
     *
     * 事实上，当上述 p 从 0 取到 n-1，q 从 n-1 取到 0 后，所有情况就遍历完了。
     *
     * 注：上述遍历是没有重复情况出现的，即当 (p1,q1)≠(p2,q2) 时，按上述方式取的括号组合一定不同。
     *
     */
    public static List<String> generateParenthesis(int n) {
        List<List<String>> res = new LinkedList<>();
        res.add(new LinkedList<>(Arrays.asList("")));       // n = 0
        res.add(new LinkedList<>(Arrays.asList("()")));     // n = 1
        for (int i = 2; i <= n; i++) {
            List<String> tmp = new LinkedList<>();      // n = 2 时
            for (int j = 0; j < i; j++) {               // j = 0, str1 = ""  str2 = ()   ()()
                                                        // j = 1, str1 = ()  str2 = ""   (())
                List<String> str1 = res.get(j);
                List<String> str2 = res.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        String str = "(" + s1 + ")" + s2;
                        tmp.add(str);
                    }
                }
            }
            res.add(tmp);
        }
        return res.get(n);
    }
}
