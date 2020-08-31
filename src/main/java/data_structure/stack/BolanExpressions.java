package data_structure.stack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
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
        String s = switchExpress(switchArray("1+((3+4)*5)-16"));
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
            if (text.matches("\\d+")){
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

    /**
     * 中缀表达式转后缀表达式 1+((3+4)*5)-6   = 30
     * 步骤一：先将中缀表达式转为List集合
     * 步骤二：将集合转为后缀表达式
     */
    public static List switchArray(String s){
        List<String> ls = new ArrayList<String>();
        int i = 0;
        String str;
        char c;
        do {
            c = s.charAt(i);
            // 如果是非数字，直接入数组
            if (((c = s.charAt(i)) < 48) || ((c = s.charAt(i)) > 57)) {
                ls.add("" + c);
                i++;
            } else {
                // 如果是数字，则需要考虑有多位数的情况，遍历过程为了防止其超出界限，需要判断长度
                str = "";
                while ( i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     *
         1.初始化两个栈，运算符栈s1，以及储存中间结果的栈s2;在实际使用中，因为s2只有入栈没有出栈，且最后需要一次翻转的操作，所以这里用List集合替代
         2.从左至右扫描中缀表达式
         3.遇到操作数时，将其直接压入S2
         4.遇到运算符时，比较其余S1栈顶运算符的优先级
             1)如果s1为空，或者栈顶运算符为左括号时，则直接入栈s1
             2)如果优先级比栈顶运算符高，则将运算符压入s1
             3)否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1的步骤中与新的栈顶比较
         5.遇到括号时
             1)如果是左括号，直接压入s1
             2)如果是右括号，则一次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将一对括号丢弃
         6.重复步骤2-5
         7.将s1剩余的依次压入s2即可，s2剩下的即为结果
     * @param ls
     * @return
     */
    public static String switchExpress(List<String> ls){
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();
        for (String item: ls) {
            if (item.matches("\\d+")){
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                // 弹出遇到左括号之前的内容到s2
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();  // 丢弃左括号
            } else {
                // 当遇到运算符时，如果item优先级低于栈顶，则弹出s1到s2        如果遇到括号，不被通过则打断直接入栈s1
                while (s1.size() > 0 && Express.getPriority(item) < Express.getPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return String.join(" ", s2);
    }

}

class Express{
    public static final Integer ADD = 1;
    public static final Integer SUB = 1;
    public static final Integer MUL = 2;
    public static final Integer DIV = 2;

    public static Integer getPriority(String symbol){
        switch (symbol) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            default:
                System.out.println("跳过");
                return 0;
        }
    }

}