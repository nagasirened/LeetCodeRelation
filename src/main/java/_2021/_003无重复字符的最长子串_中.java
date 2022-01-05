package _2021;

import java.util.HashSet;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 *
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 */
public class _003无重复字符的最长子串_中 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 🌟🌟  双指针滑动窗口   hash表  🌟🌟
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int currentLength = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(right);
            while (left < right && set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
                currentLength--;
            }
            right++;
            set.add(c);
            currentLength++;
            max = Math.max(max, currentLength);
        }
        return max;
    }
}
