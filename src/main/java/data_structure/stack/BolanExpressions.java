package data_structure.stack;

import java.util.Stack;

/**
 * author: ZGF
 * 08-2020/8/25 : 15:06
 * context : 概念介绍
 *
 *
 * 1.前缀表达式又称波兰式，前缀表达式的运算符位于操作数之前。比如:- × + 3 4 5 6
   2.中缀表达式就是常见的运算表达式，如(3+4)×5-6
   3.后缀表达式又称逆波兰表达式,与前缀表达式相似，只是运算符位于操作数之后,比如:3 4 + 5 × 6 -

 这三个运算结果都是一样的
 人类最熟悉的一种表达式1+2，(1+2)3，3+42+4等都是中缀表示法。
 对于人们来说，也是最直观的一种求值方式，先算括号里的，然后算乘除，最后算加减。
 但是，计算机处理中缀表达式却并不方便。


 例子：
 我们先看一个例子...后缀表达式3 4 + 5 × 6 -的计算
 1.从左至右扫描，将3和4压入堆栈；
 2.遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素，注意与前缀表达式做比较），计算出3+4的值，得7，再将7入栈；
 3.将5入栈；
 4.接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 5.将6入栈；
 6.最后是-运算符，计算出35-6的值，即29，由此得出最终结果。
 */

public class BolanExpressions {

    public static void main(String[] args) {
        String s = "3 4 + 5 * 6 -";
        System.out.println(calculate(s));
    }

    public static int calculate(String s){
        String[] split = s.split(" ");
        if (split.length < 1) {
            throw new RuntimeException("参数问题");
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            String text = split[i];
            if (text.matches("\\d")){
                stack.push(Integer.valueOf(text));
            }else {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                int res = 0;
                switch (text){
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1 ;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num2;
                        break;
                    default:
                        throw new RuntimeException("符号出错");
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }
}
