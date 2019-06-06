package array.s;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCode Study
 * @description: 杨辉三角
 * @author: ZengGuangfu
 * @create 2019-06-06 09:19
 */

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
  在杨辉三角中，每个数是它左上方和右上方的数的和。

 示例:
    输入: 3
    输出: [1,3,3,1]
 */
public class YangHuiTriangleII {

    public static void main(String[] args) {
        List<Integer> result = getRow(5);
        result.forEach( num -> {
            System.out.print(num + "  ");
        }  );
    }

    /**
     * 执行用时 : 2 ms, 在Pascal's Triangle II的Java提交中击败了72.87% 的用户
       内存消耗 : 33.1 MB, 在Pascal's Triangle II的Java提交中击败了53.46% 的用户

       利用二维数组辅助，可以快速获取上一行的值。
       就是利用上面那个改了一下
     */
    public static List<Integer> generate(int rowIndex) {
        int[][] nums = new int[rowIndex+1][rowIndex+1];
        int h = 0;  // 代表行数

        ArrayList<Integer> grantArrayList = new ArrayList<>();
        while (h <= rowIndex){
            int q = 0;   // 代表当前行的下标
            while (q <= h){
                if (q == h || q == 0){
                    nums[h][q] = 1;
                    if (h == rowIndex){
                        grantArrayList.add(1);
                    }
                }else{
                    nums[h][q] = nums[h-1][q-1] + nums[h-1][q];
                    if (h == rowIndex){
                        grantArrayList.add(nums[h][q]);
                    }
                }
                q++;
            }
            h++;
        }
        return grantArrayList;
    }



    /**
     {1},
     {1,1},
     {1,2,1},
     {1,3,3,1},
     {1,4,6,4,1},
     {1,5,10,10,5,1},
     {1,6,15,20,15,6,1},
     {1,7,21,35,35,21,7,1},
     {1,8,28,56,70,56,28,8,1},
     * 此外，还在网上看到了杨辉三角的规律，第N行的值是 11^ N
     * 第0行 11^0 = 1
     * 1  11^1 = 11
     * 2  11^2 = 121
     * 3  11^3 = 1331
     */
    // 不知名网友一，这套路真的棒，我日
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list=new ArrayList<>();
        if(rowIndex==0){
            list.add(1);
            return list;
        }
        long index=1;
        for(int i=0;i<=rowIndex;i++){
            list.add((int)index);
            /**
             * 1*(3-0)/1 = 3
             * 3*(3-1)/2 = 3
             * 3*(3-2)/3 = 1
             */
            index=index*(rowIndex-i)/(i+1);
        }
        return list;
    }
}
