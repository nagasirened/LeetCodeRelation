package leetcode;

import com.alibaba.fastjson.JSON;

public class _066加一 {

    public static void main(String[] args) {
        int[] result = plusOne(new int[]{4, 3, 2, 9});
        System.out.println(JSON.toJSONString(result));
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            int tmp = digits[i] + 1;
            if (tmp <= 9) {
                digits[i] = tmp;
                return digits;
            }
            digits[i] = 0;
        }

        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return  res;
        // 草，写多了
        /*if (jin > 0) {
            int[] res = new int[digits.length + 1];
            res[0] = jin;
            for (int i = 1; i < digits.length; i++) {
                res[i] = digits[i - 1];
            }
            return res;
        }
        return digits;*/
    }
}
