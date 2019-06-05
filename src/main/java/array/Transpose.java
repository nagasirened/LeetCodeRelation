package array;

/**
 * @program: LeetCode Study
 * @description: 转置矩阵
 * @author: ZengGuangfu
 * @create 2019-06-05 13:18
 */

/**
 * 给定一个矩阵 A， 返回 A 的转置矩阵。
 * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 示例 1：
     输入：[[1,2,3],[4,5,6],[7,8,9]]
     输出：[[1,4,7],[2,5,8],[3,6,9]]
 示例 2：
     输入：[[1,2,3],[4,5,6]]
     输出：[[1,4],[2,5],[3,6]]

 提示：
     1 <= A.length <= 1000
     1 <= A[0].length <= 1000
 */
public class Transpose {

    public static void main(String[] args) {
        int[][] A = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = transpose(A);
        for (int[] num : result) {
            for (int a : num ) {
                System.out.print(a + "   ");
            }
            System.out.println("");
        }
    }

    /**
     * 执行用时 : 1 ms, 在Transpose Matrix的Java提交中击败了99.62% 的用户
       内存消耗 : 48.3 MB, 在Transpose Matrix的Java提交中击败了68.76% 的用户

       一次成功，这个题就是遍历把值取对就行了
     */
    public static int[][] transpose(int[][] A) {
        int[][] B = new int[A[0].length][A.length];
        for (int j = 0; j< A[0].length ; j++){
            for (int i = 0 ; i < A.length ; i++){
                B[j][i] = A[i][j];
            }
        }
        return B;
    }
}
