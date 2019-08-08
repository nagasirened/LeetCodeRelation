package string.s;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @program: LeetCode Study
 * @description: 有效的括号
 * @author: ZengGuangfu
 * @create 2019-08-06 13:57
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:
输入: "()"
输出: true

示例 2:
输入: "()[]{}"
输出: true

示例 3:
输入: "(]"
输出: false

示例 4:
输入: "([)]"
输出: false

示例 5:
输入: "{[]}"
输出: true
 */
public class IsValid {

    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid(s));
    }

    /**
     * 这样做效率很低，但是有效，替换所有括号直到不能替换了，看剩下的字符串是不是空的即可
     */
    public static boolean isValid(String s){
        if ((s.length() & 1 ) != 1 ) return false;
        while(s.indexOf("()") != -1 || s.indexOf("[]") != -1 || s.indexOf("{}") != -1){
            s = s.replace("()","");
            s = s.replace("[]","");
            s = s.replace("{}","");
        }
        return s.length() == 0;
    }

    // 栈指针原理
    public boolean isValid2(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0;i<s.length();i++) {
            switch (s.charAt(i)) {
                case '(':
                    stack.push("(");
                    break;
                case ')':
                    if (stack.empty()) {
                        return false;
                    }
                    if (!stack.pop().equals("(")) {
                        return false;
                    }
                    break;
                case '[':
                    stack.push("[");
                    break;
                case ']':
                    if (stack.empty()) {
                        return false;
                    }
                    if (!stack.pop().equals("[")) {
                        return false;
                    }
                    break;
                case '{':
                    stack.push("{");
                    break;
                case '}':
                    if (stack.empty()) {
                        return false;
                    }
                    if (!stack.pop().equals("{")) {
                        return false;
                    }
                    break;
            }
        }
        return stack.empty();
    }
}
