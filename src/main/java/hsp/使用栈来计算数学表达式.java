package hsp;

public class 使用栈来计算数学表达式 {

    /**
     * 7 * 2 * 2 - 5 + 1 - 5 * 3 - 3
     * 没有括号
     * 栈A - numStack数栈，存储数据的
     * 栈B - symbolStack符号栈，存放运算符
     *
     * 1.通过一个index索引遍历表达式
     * 2.如果是一个数字, 直接入A栈
     * 3.如果是一个运算符
     *   * 如果符号栈为空，则直接入栈
     *   * 如果符号栈有内容，如果当前操作符优先级小于等于栈顶的操作符号，就从A栈中pop两个数，
     *                          并pop一个符号运算结果，将结果放入A栈，当前操作符再入B栈
     *                    如果当前操作符优先级大于栈顶的操作符，就直接入B栈
     * 4.扫描完成后，就顺序从A栈和B栈中取出内容计算 (下面的+-上面的)
     * 5.最后A栈只有一个数字。就是表达式的结果
     */
    public static void main(String[] args) {
        System.out.println(7*2*2-5+1-5*3-3);
        // String expression = "7 * 2 * 2 - 5 + 1 - 5 * 3 - 3".trim();
        String expression = "780 - 2 * 3 - 3".trim();
        expression = expression.replaceAll(" ", "");
        if ("".equals(expression)) {
            return;
        }
        // 创建数栈和符号栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack symbolStack = new ArrayStack(10);

        int no1, no2, symbol, res, index = 0;
        char ch; // 指向每次扫描得到的char
        StringBuilder keepNumber = new StringBuilder("");
        do {
            ch = expression.substring(index, index + 1).charAt(0);
            // 如果是数字，直接入数栈
            if (!isSymbol(ch)) {
                keepNumber.append(ch - 48);   // 因为字符 1 在 ascii 表中数字是49
                while (index < expression.length()) {
                    if (++index == expression.length()) {
                        numStack.push(Integer.parseInt(keepNumber.toString()));
                    } else {
                        ch = expression.substring(index, index + 1).charAt(0);
                        if (isSymbol(ch)) {
                            numStack.push(Integer.parseInt(keepNumber.toString()));
                            keepNumber = new StringBuilder("");

                            /** 要走符号的逻辑了，完事儿break */
                            if (symbolStack.isEmpty()) {
                                symbolStack.push(ch);
                            } else {
                                int curPriority = priority(ch);
                                while (curPriority <= priority((char) symbolStack.peek())) {
                                    no1 = numStack.pop();
                                    no2 = numStack.pop();
                                    symbol = symbolStack.pop();
                                    res = calculate(no1, no2, symbol);
                                    // 再次往前追溯符号，如果还是当前符号小于了，才能直接入栈，
                                    // 不然会出现结果 24 - 15 - 3, 计算出错误结果, 因此上面用while
                                    numStack.push(res);
                                }
                                symbolStack.push(ch);
                            }

                            break; // break啊哥哥
                        } else {
                            keepNumber.append(ch - 48);
                        }
                    }
                }
            }

        } while (++index < expression.length());

        // 剩下的 可能是这样 24 - 15 - 3 应该是6，如果从栈顶开始，就成了24-(15-3)了
        // 因此 +- 改为 no1 +- no2
        while (!symbolStack.isEmpty()) {
            no1 = numStack.pop();
            no2 = numStack.pop();
            symbol = symbolStack.pop();
            res = calculate(no1, no2, symbol);
            numStack.push(res);
        }

        System.out.printf("表达式 %s 结果是：%s%n", expression, numStack.pop());
    }

    // 返回符号优先级
    public static int priority(char symbol) {
        if (symbol == '*' || symbol == '/') {
            return 1;
        } else if (symbol == '-' || symbol == '+') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是不是运算法，目前仅支持下面几个
    public static boolean isSymbol(int symbol) {
        return symbol == '+' || symbol == '-' ||
                symbol == '*' || symbol == '/';
    }

    // 数学计算   isEnd 收尾，剑法的计算不一样
    public static int calculate(int no1, int no2, int symbol) {
        if (!isSymbol(symbol)) {
            throw new RuntimeException("表达式错误");
        }
        switch (symbol) {
            case '+':
                return no2 + no1;
            case '-':
                return no2 - no1;
            case '*':
                return no2 * no1;
            case '/':
                return no2 / no1;
            default:
                return 0;
        }
    }

}


/**
 * 使用数组来模拟一个栈
 * top指针代表了栈顶
 * 入栈，top++,  stack[top] = data
 * 出栈，data = stack[top], top--
 */
class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.top = -1;
    }

    // 栈是否满了
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈是否为空
    public boolean isEmpty() {
        return top < 0;
    }

    // 入栈
    public boolean push(int data) {
        if (isFull()) {
            throw new RuntimeException("栈满");
        }
        stack[++top] = data;
        return true;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    // 遍历，不能删除数据，从栈顶开始往下遍历
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.print("遍历数据：" + stack[i] + " \t ");
        }
    }

    // 偷看栈顶
    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return stack[top];
    }

}

/**
 * 使用单向链表来模拟一个栈
 * 添加元素，即添加一个头部Node作为栈顶的元素
 *
 */
class LinkedStack {
    private int maxSize;
    private int count;
    private StackNode top;

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
        count = 0;
    }

    // 栈是否满了
    public boolean isFull() {
        return count == maxSize;
    }

    // 栈是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 入栈
    public boolean push(int data) {
        if (isFull()) {
            throw new RuntimeException("栈满");
        }
        top = new StackNode(data, top);
        count++;
        return true;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int data = top.no;
        top = top.next;
        count--;
        return data;
    }

    // 遍历，不能删除数据，从栈顶开始往下遍历
    public void show() {
        if (isEmpty()) {
            return;
        }
        StackNode curr = top;
        while (curr != null) {
            System.out.print("遍历数据：" + curr.no + " \t ");
            curr = curr.next;
        }
    }

    // 偷看栈顶
    public int peak() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return top.no;
    }

    static class StackNode {
        private int no;
        private StackNode next;
        public StackNode(int no, StackNode next) {
            this.no = no;
            this.next = next;
        }
    }
}

