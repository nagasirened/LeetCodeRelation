package algorithm.array.s;

import java.util.Arrays;

/**
 * @program: LeetCode Study
 * @description: 高度检查器
 * @author: ZengGuangfu
 * @create 2019-06-03 14:
 *
 * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
 * 请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
 *
示例：
输入：[1,1,4,2,1,3]
输出：3
解释：
高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。

提示：
1 <= heights.length <= 100
1 <= heights[i] <= 100
 */
public class HeightChecker {

    public static void main(String[] args) {
        int[] heights = {1,1,4,2,1,3};
        int result = heightChecker(heights);
        System.out.println(result);
    }

    /**
     * 一次成功唉，先排序，再遍历比较不一样的数
     * 执行用时 : 3 ms, 在Height Checker的Java提交中击败了80.31% 的用户
       内存消耗 : 34.2 MB, 在Height Checker的Java提交中击败了100.00% 的用户
     * @param heights
     * @return
     */

    public static int heightChecker(int[] heights) {
        int leng = heights.length;
        int[] line = Arrays.copyOf(heights, leng);
        Arrays.sort(heights);
        int num = 0;
        for (int i=0;i< leng ; i++){
            if (line[i] != heights[i]){
                num++ ;
            }
        }
        return num;
    }
}
