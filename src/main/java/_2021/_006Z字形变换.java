package _2021;

import com.alibaba.fastjson.JSON;

public class _006Z字形变换 {

    public static void main(String[] args) {
        convert("PAYPALISHIRING", 3);
    }

    /**
     * 找规律
     * 二维数组为例，如果仅有三行，数据落的下标分别是
     * (0,0)
     * (1,0)
     * (2,0)
     * (1,1)
     * (0,2)
     * (1,0)
     * (2,0)
     */
    public static String convert(String s, int numRows) {
        if (numRows == 0 || s.length() == 0) {
            return "";
        }
        if (numRows == 1) {
            return s;
        }
        // 先计算二维数组的长度
        int unitNum = numRows + (numRows - 2);
        int unitLen = 1 + (numRows - 2);
        int yLen = unitLen * (s.length() % unitNum == 0 ? s.length() / unitNum : s.length() / unitNum + 1);
        String[][] arr = new String[numRows][yLen];
        boolean isDown = true;
        int x = -1;     // 补第一个数
        int y = 0;
        int index = 0;
        while (index < s.length()) {
            String c = s.charAt(index) + "";
            if (isDown) {
                x++;
                arr[x][y] = c;
                if (x + 1 == numRows) {
                    isDown = false;
                }
            } else {
                x--; y++;
                arr[x][y] = c;
                if (x == 0) {
                    isDown = true;
                }
            }
            index++;
        }

        // 其他的所有空白填充 " " 空字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (String s1 : arr[i]) {
                if (s1 != null) {
                    sb.append(s1);
                }
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
