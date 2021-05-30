package year2021.month5.no227;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculatorII {

    public static void main(String[] args) {
        String s1 = "3+2*2";
        String s2 = " 3/2 ";
        String s3 = " 3+5 / 2 ";
        String s4 = " 42 / 2 ";
        String s5 = "2-3+4";
        System.out.println(calculate(s1));
        System.out.println(calculate(s2));
        System.out.println(calculate(s3));
        System.out.println(calculate(s4));
        System.out.println(calculate(s5));
    }

    public static int calculate(String s) {
        /*
         * 遍历字符串 s，并用变量 preSign 记录每个数字之前的运算符，
         * 对于第一个数字，其之前的运算符视为加号。
         * 每次遍历到数字末尾时，根据 preSign 来决定计算方式：
         * 加号：将数字压入栈；
         * 减号：将数字的相反数压入栈；
         * 乘除号：计算数字与栈顶元素，并将栈顶元素替换为计算结果。
         * */
        Deque<Integer> numberStack = new LinkedList<>();
        int num = 0;
        char preSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 字符串末尾的空格不可跳过，防止当前 num 丢失
            if (c == ' ' && i < s.length() - 1) {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            // 当前为数组末尾元素 或 为运算符时，开始压入栈
            if (i == s.length() - 1 || !Character.isDigit(c)) {
                switch (preSign) {
                    case '+':
                        numberStack.push(num);
                        break;
                    case '-':
                        numberStack.push(-num);
                        break;
                    case '*':
                        numberStack.push(numberStack.pop() * num);
                        break;
                    case '/':
                        numberStack.push(numberStack.pop() / num);
                        break;
                }
                preSign = c;
                num = 0;
            }
        }
        int ans = 0;
        while (!numberStack.isEmpty()) {
            ans += numberStack.pop();
        }
        return ans;
    }

}

/*
* 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

 

示例 1：

输入：s = "3+2*2"
输出：7
示例 2：

输入：s = " 3/2 "
输出：1
示例 3：

输入：s = " 3+5 / 2 "
输出：5
 

提示：

1 <= s.length <= 3 * 105
s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
s 表示一个 有效表达式
表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
题目数据保证答案是一个 32-bit 整数

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/basic-calculator-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
