package leetcode;

public class _058最后一个单词的长度 {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
    }

    public static int lengthOfLastWord(String s) {
        // 倒着遍历
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ' && res > 0) {
                return res;
            } else if (chars[i] != ' ') {
                res++;
            }
        }
        return res;
    }
}
