package array.s;

/**
 * @program: LeetCode Study
 * @description: 重塑矩阵
 * @author: ZengGuangfu
 * @create 2019-06-05 15:40
 */

/**
 * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
   给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
   重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
   如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 */

import org.springframework.util.StopWatch;

/**
 * 示例 1:
 输入:
 nums =[[1,2],[3,4]]  r = 1, c = 4
 输出: [[1,2,3,4]]
 解释: 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。

 示例 2:
 输入:
 nums =[[1,2],[3,4]]    r = 2, c = 4
 输出:
 [[1,2],[3,4]]
 解释: 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
 */
public class MatrixReshape {

    public static void main(String[] args) {
        int[][] nums = {{1,2},{3,4}};
        int[][] result = matrixReshape(nums, 4, 1);
        for (int[] array : result){
            for (int num : array){
                System.out.print(num + "  ");
            }
        }
    }

    /**
     * 执行用时 : 3 ms, 在Reshape the Matrix的Java提交中击败了94.97% 的用户
       内存消耗 : 48.9 MB, 在Reshape the Matrix的Java提交中击败了68.55% 的用户

       我的思路: 首先是要判断是否能够转换，即计算元素总数，不匹配则直接返回原来的内容
                然后元素还是双重for循环去遍历
                遍历出来的数据填充到新的二维数组中，用两个数字辅助代表二维数组的第一个下表和第二个下标
     */
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length  != r*c){
            System.out.println("wrong");
            return nums;
        }
        int[][] result = new int[r][c];
        int k = 0,q = 0;
        for (int i=0; i< nums.length ; i++){
            for (int j=0;j< nums[0].length; j++){
                if (k < c){
                    result[q][k] = nums[i][j];
                    k++;
                }else{
                    k = 0; q++;

                    result[q][k] = nums[i][j];
                    k++;
                }
            }
        }
        return result;
    }


    /**
     * 这是其他人写的东西
     * TODO 利用除法的商值和取余的值，逻辑上需要有一定的理解，值得学习
     */
    public static int[][] matrixReshape2(int[][] nums, int r, int c) {
        int row = nums.length;
        if (row == 0) {
            return nums;
        }
        int col = nums[0].length;
        if (row * col != r * c) {
            return nums;
        }

        int total = r * c;
        int[][] res = new int[r][c];
        for (int t = 0; t < total; t++) {
            res[t / c][t % c] = nums[t / col][t % col];
        }

        return res;
    }
}
