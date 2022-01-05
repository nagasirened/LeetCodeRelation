package leetcode;

/**
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class _014最长公共前缀 {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        String str = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String item = strs[i];
            while (item.indexOf(str) != 0 && str.length() != 0) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }
}
