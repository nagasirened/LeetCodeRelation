package leetcode;

public class _012数字专罗马数字 {

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }

    /**
     * 直接贪心
     */
    public static String intToRoman(int num) {
        if (num < 1) {
            return "";
        }
        int[] numArr = new int[] {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strArr = new String[] {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (num > 0) {
            int tmp = numArr[index];
            if (num >= tmp) {
                sb.append(strArr[index]);
                num -= tmp;
                continue;
            }
            index++;
        }
        return sb.toString();
    }


}
