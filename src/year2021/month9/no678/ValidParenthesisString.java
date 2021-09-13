package year2021.month9.no678;

import java.util.Deque;
import java.util.LinkedList;

public class ValidParenthesisString {
    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "(*)";
        String s3 = "(*))";
        System.out.println(checkValidString(s1));
        System.out.println(checkValidString(s2));
        System.out.println(checkValidString(s3));
    }

    public static boolean checkValidString(String s) {
        // Stack, time is O(n), space is O(1)
        // 遍历 s，遇到 (、* 分别入栈 leftStack, starStack
        // 遇到 ) 时：
        //      先用 ( 匹配，
        //      若 leftStack 栈为空时，用 * 匹配，
        //      若 starStack 也为空，则匹配失败
        // 遍历完 s 后，可能栈还存在元素，需要用 * 匹配 (
        // 分别对 leftStack 和 starStack 弹栈
        // 若存在 leftIndex > starStack，则意味着 ( 右边没有 * 匹配，匹配失败
        // 最后 leftStack 为空则匹配成功，starStack 可不为空
        Deque<Integer> leftStack = new LinkedList<>();
        Deque<Integer> starStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                starStack.push(i);
            } else {    // c == ')'
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            Integer leftIndex = leftStack.pop();
            Integer starIndex = starStack.pop();
            if (leftIndex > starIndex) {
                return false;
            }
        }
        return leftStack.isEmpty();
    }
}

/*
* 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：

任何左括号 ( 必须有相应的右括号 )。
任何右括号 ) 必须有相应的左括号 ( 。
左括号 ( 必须在对应的右括号之前 )。
* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
一个空字符串也被视为有效字符串。
示例 1:

输入: "()"
输出: True
示例 2:

输入: "(*)"
输出: True
示例 3:

输入: "(*))"
输出: True
注意:

字符串大小将在 [1，100] 范围内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parenthesis-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
