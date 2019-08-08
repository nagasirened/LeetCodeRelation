package array.s;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: LeetCode Study
 * @description: 卡牌分组
 * @author: ZengGuangfu
 * @create 2019-08-05 15:29
 * 给定一副牌，每张牌上都写着一个整数。

此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：

每组都有 X 张牌。
组内所有的牌上都写着相同的整数。
仅当你可选的 X >= 2 时返回 true。

示例 1：
输入：[1,2,3,4,4,3,2,1]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]

示例 2：
输入：[1,1,1,2,2,2,3,3]
输出：false
解释：没有满足要求的分组。

示例 3：
输入：[1]
输出：false
解释：没有满足要求的分组。

示例 4：
输入：[1,1]
输出：true
解释：可行的分组是 [1,1]

示例 5：
输入：[1,1,2,2,2,2]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[2,2]

!!!!  [0,0,0,0,0,1,1,2,3,4]  !!!!
 */
public class HasGroupsSizeX {

    public static void main(String[] args) {
        int[] a = {0,0,0,0,0,1,1,2,3,4};
        //int[] a = {1,1,2,2,3,4,3,4};
        System.out.println(hasGroupsSizeX(a));
    }

    /**
     * 重点在于统计每个数出现次数的公约数
     */
    public static boolean hasGroupsSizeX(int[] deck) {
        if(deck.length < 2) return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a: deck) {
            if (map.containsKey(a)){
                map.put(a, map.get(a) + 1);
            }else{
                map.put(a , 1);
            }
        }

        //获得 map中的value的最小值，如果小于2，返回false
        Integer min = Integer.MAX_VALUE;
        for (Integer i : map.values()) {
            min = Math.min(i,min);
        }

        if (min < 2) return false;

        // 遍历从2开始尝试作为公约数，有一个公约数就可以返回true了
        for (int q = 2; q <= min ;q++){
            boolean result = true;
            for (Integer value : map.values()) {
                if (value % q != 0){
                    result = false;
                }
            }
            if (result) return true;
        }
        return false;
    }
}
