package array.s;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: LeetCode Study
 * @description: 矩阵中的幻方
 * @author: ZengGuangfu
 * @create 2019-08-06 14:59
 *
 * 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。

给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。

    示例：
    输入: [[4,3,8,4],
    [9,5,1,9],
    [2,7,6,2]]
    输出: 1
    解释:
    下面的子矩阵是一个 3 x 3 的幻方：
    438
    951
    276

    而这一个不是：
    384
    519
    762
    总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。

提示
    1 <= grid.length <= 10
    1 <= grid[0].length <= 10
    0 <= grid[i][j] <= 15
 */
public class NumMagicSquaresInside {

    static final Set set = new HashSet<Integer>();

    public static void main(String[] args) {
        int[][] base = {{10,3,5},{1,6,11},{7,9,2}};
        System.out.println(numMagicSquaresInside(base));
    }

    /**
     * 世上无难事，只要肯放弃
     *
     * 我这个答案是错的，做完了才想起来  3 ^ 3 ^ 2 ^ 2 还是等于0
     * 我能想到的改动就是强行判断想等了
     *
     * 还有一种，题目中要求是1-9的数字，那么中间的数必须是5，这样也能减少一些运算时间了。
     */

    public static int numMagicSquaresInside(int[][] base){
        int result = 0;
        for (int i = 2; i<base.length;i++){                    // X
            for (int j = 2;j < base[0].length ;j++){           // Y
                //先判断所有数应该不相等
                if(!res(base,i,j)){
                    break;
                }
                if (((base[i-2][j-2] + base[i-2][j-1] + base[i-2][j])
                        ^ (base[i-1][j-2] + base[i-1][j-1] + base[i-1][j])
                        ^ (base[i][j-2] + base[i][j-1] + base[i][j])
                        ^ (base[i-1][j-2] + base[i-2][j-2] + base[i][j-2])
                        ^ (base[i-1][j-1] + base[i-2][j-1] + base[i][j-1])
                        ^ (base[i-1][j] + base[i-2][j] + base[i][j])
                        ^ (base[i][j-2] + base[i-1][j-1] + base[i-2][j])
                        ^ (base[i-2][j] + base[i-1][j-1] + base[i][j-2])) == 0){
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean res(int[][] base,int i,int j){
        for(int h=0;h<3;h++){
            for(int q=0;q<3;q++){
                set.add(base[i-h][j-q]);
            }
        }
        int size = set.size();
        set.clear();
        return size == 9;
    }


}
