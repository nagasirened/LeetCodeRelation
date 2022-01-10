package leetcode;

import java.util.LinkedList;
import java.util.List;

public class _017电话号码的数字组合 {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits.length() == 0) {
            return res;
        }
        String[] param = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder("");
        pack(res, param, sb, digits, 0);
        return res;
    }

    /**
     * 回溯
     */
    public static void pack(List<String> res, String[] param, StringBuilder sb, String digits, int index) {
        if (digits.length() == index) {
            res.add(sb.toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        char[] chars = param[digit].toCharArray();
        for (char ch : chars) {
            sb.append(ch);
            pack(res, param, sb, digits, index+1);
            // 仅移除最后一位，然后拼接下一个可能
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
