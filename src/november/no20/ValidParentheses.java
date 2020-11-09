package november.no20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{{}}";
        String s6 = "}";
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
        System.out.println(isValid(s4));
        System.out.println(isValid(s5));
        System.out.println(isValid(s6));
    }

    private static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (map.containsKey(currentChar)) {
//                当前char为右括号，需要与栈顶元素进行匹配
                if (stack.empty()) {
//                需判空，避免栈空异常
                    return false;
                }
                Character pop = stack.pop();
                if (!pop.equals(map.get(currentChar))) {
                    return false;
                }
            } else {
                stack.push(currentChar);
            }

        }
        return stack.empty();
    }
}

/*
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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */