package string.m;

import java.util.HashSet;

/**
 * author: ZGF
 * 06-2020/6/12 : 15:20
 * context : 无重复字符的最长子串
 */

/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

*/

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }

    /*
    思路：
        遍历字符串，每次以 i 值记录，不回溯 i 值，用flag记录遍历过程找到的重复的字符的位置。
        如果遇到重复字符，i-flag 即为子串长度，此时flag重新定位到子串中重复字符的位置，i 继续往后遍历。
        这里length跟result记录长度。我感觉代码可以更简洁一点的，但是好像写懵了？
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int max = 0, i = 0, j = 1;
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(i));
        while (j < s.length()){
            if (set.contains(s.charAt(j))){
                set.remove(s.charAt(i++));
            }else {
                set.add(s.charAt(j++));
            }
            max = Math.max(max, j - i);
        }
        return max;
    }
}
