package _2021;

public class _013罗马数字转整数 {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    /**
     * 思路：VII  =  5 + 1 + 1
     *      IV   =  -1 + 5
     *      定义pre，后面的大于前面的，就减去前面的
     *              后面的小雨前面的，就加上前面的
     *              返回结果前加上pre
     */
    public static int romanToInt(String s) {
        int sum = 0;
        int pre = convertNumber(s.charAt(0));
        int length = s.length();
        for (int i = 1; i < length; i++) {
            int cnt = convertNumber(s.charAt(i));
            if (cnt > pre) {
                sum -= pre;
            } else {
                sum += pre;
            }
            pre = cnt;
        }
        return sum + pre;
    }

    /**
     * I 1 V 5 X 10 L 50 C 100 D 500 M 1000
     */
    public static int convertNumber(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }
}
