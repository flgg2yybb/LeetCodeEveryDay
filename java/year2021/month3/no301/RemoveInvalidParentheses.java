package year2021.month3.no301;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        String s1 = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";
        System.out.println(removeInvalidParentheses(s1));
        System.out.println(removeInvalidParentheses(s2));
        System.out.println(removeInvalidParentheses(s3));
    }

    public static List<String> removeInvalidParentheses(String s) {
        char[] chars = s.toCharArray();
        int leftRemove = 0;
        int rightRemove = 0;
        for (char c : chars) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }
        Set<String> resultSet = new HashSet<>();
        backtrack(resultSet, new StringBuilder(), chars, 0, 0, 0, leftRemove, rightRemove);
        return new ArrayList<>(resultSet);
    }

    private static void backtrack(Set<String> resultSet, StringBuilder result, char[] chars, int index,
                                  int leftCount, int rightCount, int leftRemove, int rightRemove) {
        if (index == chars.length) {
            if (leftRemove == 0 && rightRemove == 0) {
                resultSet.add(result.toString());
            }
            return;
        }
        char c = chars[index];
        if (c == '(' && leftRemove > 0) {
            //移除当前左括号
            backtrack(resultSet, result, chars, index + 1, leftCount, rightCount, leftRemove - 1, rightRemove);
        }
        if (c == ')' && rightRemove > 0) {
            //移除当前右括号
            backtrack(resultSet, result, chars, index + 1, leftCount, rightCount, leftRemove, rightRemove - 1);
        }
        //将当前字符加入结果中
        result.append(c);
        if (c != '(' && c != ')') {
            //当前为字符（非括号）
            backtrack(resultSet, result, chars, index + 1, leftCount, rightCount, leftRemove, rightRemove);
        } else if (c == '(') {
            //当前为左括号
            backtrack(resultSet, result, chars, index + 1, leftCount + 1, rightCount, leftRemove, rightRemove);
        } else if (rightCount < leftCount) {
            //当前为右括号，则当且仅当右括号数量比左括号数量小时进行递归
            backtrack(resultSet, result, chars, index + 1, leftCount, rightCount + 1, leftRemove, rightRemove);
        }
        result.deleteCharAt(result.length() - 1);
    }
}

/*
* 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。

说明: 输入可能包含了除 ( 和 ) 以外的字符。

示例 1:

输入: "()())()"
输出: ["()()()", "(())()"]
示例 2:

输入: "(a)())()"
输出: ["(a)()()", "(a())()"]
示例 3:

输入: ")("
输出: [""]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
