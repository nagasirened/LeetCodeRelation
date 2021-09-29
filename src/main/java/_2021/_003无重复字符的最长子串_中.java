package _2021;

import java.util.HashSet;

public class _003无重复字符的最长子串_中 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 双指针滑动窗口   hash表
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
