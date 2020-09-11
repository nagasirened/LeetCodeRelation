package algorithm.array.s;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @program: LeetCode Study
 * @description: 数组中的K-fiff数对
 * @author: ZengGuangfu
 * @create 2019-08-06 17:23
 *
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.

示例 1:
输入: [3, 1, 4, 1, 5], k = 2
输出: 2
解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
尽管数组中有两个1，但我们只应返回不同的数对的数量。

示例 2:
输入:[1, 2, 3, 4, 5], k = 1
输出: 4
解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。

示例 3:
输入: [1, 3, 1, 5, 4], k = 0
输出: 1
解释: 数组中只有一个 0-diff 数对，(1, 1)。

注意:
数对 (i, j) 和数对 (j, i) 被算作同一数对。
数组的长度不超过10,000。
所有输入的整数的范围在 [-1e7, 1e7]。
 */
public class FindPairs {

    public static void main(String[] args) {
        int[] nums = {3,1,4,1,5};
        int pairs = findPairs2(nums, 2);
        System.out.println(pairs);
    }

    // 方法一
    public static int findPairs(int[] nums, int k){
        if (k<0) return 0;
        //先给数组排序保证是用大的减去小的，然后双重遍历，后数减前数，决定符合的数量

        //map集合添加时会把相同的给替换掉
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i=0;i<nums.length - 1 ; i++){
            for (int j = i+1; j<nums.length ; j++){
                if ((nums[j] - nums[i]) == k ){
                    map.put(nums[i],nums[j]);
                }
            }
        }
        return map.size();
    }

    //方法二，列举有多少不同的值的数量先
    public static int findPairs2(int[] nums, int k){
        if (k<0) return 0;
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 利用Map来操作
        for (int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i]) + 1);
            }else{
                map.put(nums[i],1);
            }
        }
        // k==0的话，value有几个大于1的就有几个数
        if (k == 0){
            for (Integer value : map.values()) {
                if (value>1){
                    result ++;
                }
            }
        }else{
            for (Integer key :map.keySet()) {
                if (map.containsKey(key+k)){
                    result ++;
                }
            }
        }
        return result;
    }
}
