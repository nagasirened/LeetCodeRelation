package string;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @program: LeetCode Study
 * @description: 罗马数字转为数字
 * @author: ZengGuangfu
 * @create 2019-04-04 20:24
 *  I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

示例 1:
输入: "III"
输出: 3

示例 2:
输入: "IV"
输出: 4

示例 3:
输入: "IX"
输出: 9

示例 4:
输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.

示例 5:
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class RomanToInt {

    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static void main(String[] args) {
        System.out.println(romanToInt2("MCMXCIV"));
    }

    /**
     *  抄大佬的，这是他的原话，有点骚
     *  一群人不懂罗马数字在那叫唤。 根本没有什么 CM = 1000-100 = 900 的过程，900 就是 CM，无任何复杂过程，纯文本操作就行了。
     */

    public static int romanToInt(String s) {
        s = s.replace("CM","+900").replace("CD","+400")
                .replace("XC","+90").replace("XL","+40")
                .replace("IX","+9").replace("IV","+4");
        s = s.replace("I","+1").replace("V","+5")
                .replace("X","+10").replace("L","+50")
                .replace("C","+100").replace("D","+500")
                .replace("M", "+1000");
        try {
            return (Integer) jse.eval(s);
        } catch (ScriptException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 这是根据题意编写的
     */
    public static int romanToInt2(String s) {
        int sum = 0;
        for (int i=0;i<s.length() ;i++ ){
            switch (s.charAt(i)){
                case 'M': sum += 1000;break;
                case 'D': sum += 500;break;
                case 'C': sum += 100;break;
                case 'L': sum += 50;break;
                case 'X': sum += 10;break;
                case 'V': sum += 5;break;
                case 'I': sum += 1;break;
            }

            if( i != 0){
                if(((s.charAt(i)=='V')||(s.charAt(i)=='X'))&&(s.charAt(i-1)=='I'))
                    sum -= 1*2;
                if(((s.charAt(i)=='L')||(s.charAt(i)=='C'))&&(s.charAt(i-1)=='X'))
                    sum -= 10*2;
                if(((s.charAt(i)=='D')||(s.charAt(i)=='M'))&&(s.charAt(i-1)=='C'))
                    sum -= 100*2;
            }
        }

        return sum;
    }
}
