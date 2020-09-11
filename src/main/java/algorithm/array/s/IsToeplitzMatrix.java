package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 托普利茨矩阵
 * @author: ZengGuangfu
 * @create 2019-06-06 10:51
 */

/**
 * 如果一个矩阵的每一方向由左上到右下的对角线上具有相同元素，那么这个矩阵是托普利茨矩阵。
   给定一个 M x N 的矩阵，当且仅当它是托普利茨矩阵时返回 True。
 示例 1:
     输入:matrix = [ [1,2,3,4],
                    [5,1,2,3],
                    [9,5,1,2]]
     输出: True
     解释: 在上述矩阵中, 其对角线为:
     "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     各条对角线上的所有元素均相同, 因此答案是True。
 示例 2:
     输入: matrix = [[1,2],
                    [2,2]]
     输出: False
     解释:对角线"[1, 2]"上的元素不同。
 */
public class IsToeplitzMatrix {

    public static void main(String[] args) {
        // M * N 的矩阵
        int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        //int[][] matrix = {{1,2},{2,2}};
        System.out.println(isToeplitzMatrix(matrix));
    }

    /**
     * 执行用时 : 4 ms, 在Toeplitz Matrix的Java提交中击败了93.62% 的用户
       内存消耗 : 41.4 MB, 在Toeplitz Matrix的Java提交中击败了87.28% 的用户

       主要是遍历的时候注意 p,q 的值
     */
    public static boolean isToeplitzMatrix(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        // p
        int q,p,base;
        // 从第一行开始查找
        for (int i= 0; i< N; i++){
            p=0; q=i;
            if (p<M && q<N){
                base = matrix[p][q];
                while (p<M && q<N){
                    if (matrix[p][q] != base) return false;
                    else p++;q++;
                }
            }
        }
        // 从第一列开始查找
        for (int j=1; j< M; j++){
            p = j; q = 0 ;
            if (p<M && q<N){
                base = matrix[p][q];
                while (p<M && q<N){
                    if (matrix[p][q] != base) return false;
                    else p++;q++;
                }
            }
        }
        return true;
    }

}
