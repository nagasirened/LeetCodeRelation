package string.s;

/**
 * @program: LeetCode Study
 * @description: 最长公共前缀
 * @author: ZengGuangfu
 * @create 2019-09-16 16:30
 * 编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串 ""。
    示例 1:
    输入: ["flower","flow","flight"]
    输出: "fl"

    示例 2:
    输入: ["dog","racecar","car"]
    输出: ""
    解释: 输入不存在公共前缀。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"",""};
        System.out.println(longestCommonPrefix(strs));
    }

    /**
     * 我的思路：取出第一个单词作为基准，每次加一个字符，看看剩下的有没有这个公共前缀。
     * 如果有一个单词不是，立马打断并返回基准前缀
     *
     * 执行用时 :5 ms, 在所有 Java 提交中击败了24.88%的用户
     * 内存消耗 :36.5 MB, 在所有 Java 提交中击败了85.77%的用户
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)  return "";
        if (strs.length == 1)  return strs[0];
        String baseString = strs[0];
        if (baseString !=null && !"".equals(baseString.trim())){
            StringBuffer stringBuffer = new StringBuffer("");
            boolean isBreak = false;
            for (int i=1; i<=baseString.length(); i++) {
                String base = baseString.substring(0, i);
                for (int j=1; j<strs.length; j++){
                    if (!strs[j].startsWith(base)){
                        isBreak = true;
                        break;
                    }
                }
                if (isBreak){
                    break;
                }
                stringBuffer.append(baseString.substring(i-1,i));
            }
            return stringBuffer.toString();
        }
        return "";
    }
}
