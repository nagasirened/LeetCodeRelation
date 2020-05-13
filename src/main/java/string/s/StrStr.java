package string.s;

/**
 * @program: LeetCode Study
 * @description: 实现strStr()
 * @author: ZengGuangfu
 * @create 2019-09-16 17:06
 *
 * 实现 strStr() 函数。
   给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
   如果不存在，则返回  -1。

    示例 1:
    输入: haystack = "hello", needle = "ll"
    输出: 2

    示例 2:
    输入: haystack = "aaaaa", needle = "bba"
    输出: -1
 */
public class StrStr {
    public static void main(String[] args) {
        String haystack = "mississippi";
        String needld = "pi";
        System.out.println(strStr(haystack, needld));
    }

    /**
     * 利用subString 去匹配
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.40%的用户
     * 内存消耗 :36.2 MB, 在所有 Java 提交中击败了83.48%的用户
     */
    public static int strStr(String haystack, String needle) {
        if (haystack.equals(needle)) return 0;
        int result = -1;
        int length = haystack.length();
        int needleLength = needle.length();
        if (needleLength > length) return result;
        for(int i=0; i+needleLength <= length; i++){
            if (haystack.substring(i, i+needleLength).equals(needle)){
                result = i;
                break;
            }
        }
        return result;
    }
}
