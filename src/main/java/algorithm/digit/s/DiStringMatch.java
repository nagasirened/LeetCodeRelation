package algorithm.digit.s;

/**
 * @program: LeetCode Study
 * @description: 增减字符串匹配
 * @author: ZengGuangfu
 * @create 2019-08-09 16:09
 *
 * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。

    返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：

    如果 S[i] == "I"，那么 A[i] < A[i+1]
    如果 S[i] == "D"，那么 A[i] > A[i+1]

示例 1：
输出："IDID"
输出：[0,4,1,3,2]

示例 2：
输出："III"
输出：[0,1,2,3]

示例 3：
输出："DDI"
输出：[3,2,0,1]

 */
public class DiStringMatch {

    public static void main(String[] args) {
        int[] idids = diStringMatch2("DDD");
        for (int a: idids) {
            System.out.println(a);
        }
    }

    /**
     * 从示例看来，返回数组的数字是从0开始到s.length的长度
     * 而且遵循的是增减的数据即可  示例1 中可以是[0,4,1,3,2]  也应该可以是[ 1,3,2,4,0 ]等答案
     *
     * 假设初始值为0，遇到 I 就加1，遇到 D 就减 1
     * 如果有负数，就按照这个数字把负数补足即可；
     */
    public static int[] diStringMatch(String s) {
        int leng = s.length();
        int[] ints = new int[leng+1];
        int up = 0;
        int down = 0;
        ints[0] = 0;
        int[] result = new int[leng+1];
        for (int i=0; i<leng; i++){
            if (s.charAt(i) == 'I'){
                up++;
                ints[i+1] = up;
            }else{
                down--;
                ints[i+1] = down;
            }
        }
        for (int j=0; j<result.length; j++){
            result[j] = ints[j] - down;
        }
        return result;
    }

    /**
     *
     * 双指针。遍历S，遇到‘I’，说明下一个数比它大，因此把0~N剩余的数中最小的数压入结果中；
     * 遇到‘D’，说明下一个数比它小，因此把0~N剩余的数中最大的数压入结果中。
     *
     * 遍历完以后不要忘了把最后一个剩下的数也压入结果中即可。
     *
     * 即默认为第一次遇到I的是最小值，第一次遇到D是最大值
     */
    public static int[] diStringMatch2(String s) {
        int[] result = new int[s.length()+1];
        // 设定两个值，一个为最小值，一个是最大值
        int min = 0;
        int max = s.length();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == 'I') {
                result[i] = min++;
            } else {
                result[i] = max--;
            }
        }
        result[s.length()] = min;
        return result;
    }

}
