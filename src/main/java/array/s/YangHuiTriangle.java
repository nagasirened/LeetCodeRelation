package array.s;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCode Study
 * @description: 杨辉三角
 * @author: ZengGuangfu
 * @create 2019-06-06 09:19
 */
public class YangHuiTriangle {

    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        result.forEach( intList -> {
            intList.forEach(num ->{
                System.out.print(num + "  ");
            });
            System.out.println("");
        }  );
    }

    /**
     * 执行用时 : 1 ms, 在Pascal's Triangle的Java提交中击败了98.33% 的用户
       内存消耗 : 33.6 MB, 在Pascal's Triangle的Java提交中击败了37.59% 的用户

       利用二维数组辅助，可以快速获取上一行的值。但是我这样遍历，每一行都新创建了一个新的List集合。
     */
    public static List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> list = new ArrayList<>();

        int[][] nums = new int[numRows][numRows];
        int h = 0;  // 代表行数

        while (h < numRows){
            int q = 0;   // 代表当前行的下标
            ArrayList<Integer> grantArrayList = new ArrayList<>();
            while (q <= h){
                if (q == h || q == 0){
                    nums[h][q] = 1;
                    grantArrayList.add(1);
                }else{
                    nums[h][q] = nums[h-1][q-1] + nums[h-1][q];
                    grantArrayList.add(nums[h][q]);
                }
                q++;
            }
            list.add(grantArrayList);
            h++;
        }
        return list;
    }
}
