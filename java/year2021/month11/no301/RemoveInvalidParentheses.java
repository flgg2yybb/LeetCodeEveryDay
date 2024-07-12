package year2021.month11.no301;

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
        int leftRemoved = 0;
        int rightRemoved = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRemoved++;
            } else if (c == ')') {
                if (leftRemoved == 0) {
                    rightRemoved++;
                } else {
                    leftRemoved--;
                }
            }
        }
        Set<String> ans = new HashSet<>();
        backtrack(s, 0, new StringBuilder(), ans, leftRemoved, rightRemoved, 0, 0);
        System.out.println(ans.size());
        return new ArrayList<>(ans);
    }

    private static void backtrack(String s, int pos, StringBuilder sb, Set<String> ans, int leftRemoved, int rightRemoved, int leftCount, int rightCount) {
        if (pos == s.length()) {
            if (leftCount == rightCount) {
                ans.add(new String(sb));
            }
            return;
        }
        char c = s.charAt(pos);
        if (c == '(' && leftRemoved > 0) {
            backtrack(s, pos + 1, sb, ans, leftRemoved - 1, rightRemoved, leftCount, rightCount);
        }
        if (c == ')' && rightRemoved > 0) {
            backtrack(s, pos + 1, sb, ans, leftRemoved, rightRemoved - 1, leftCount, rightCount);
        }
        sb.append(c);
        if (c != '(' && c != ')') {
            backtrack(s, pos + 1, sb, ans, leftRemoved, rightRemoved, leftCount, rightCount);
        }
        if (c == '(') {
            backtrack(s, pos + 1, sb, ans, leftRemoved, rightRemoved, leftCount + 1, rightCount);
        }
        if (c == ')' && leftCount > rightCount) {   // 剪枝，只有当 leftCount > rightCount 才会添加右括号
            backtrack(s, pos + 1, sb, ans, leftRemoved, rightRemoved, leftCount, rightCount + 1);
        }
        sb.deleteCharAt(sb.length() - 1);
    }

}

/*
* 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。

 

示例 1：

输入：s = "()())()"
输出：["(())()","()()()"]
示例 2：

输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
示例 3：

输入：s = ")("
输出：[""]
 

提示：

1 <= s.length <= 25
s 由小写英文字母以及括号 '(' 和 ')' 组成
s 中至多含 20 个括号

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
