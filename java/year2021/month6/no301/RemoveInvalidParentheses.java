package year2021.month6.no301;

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
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRemove++;
            }
            if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }
        Set<String> ans = new HashSet<>();
        backtrack(s, ans, new StringBuilder(), leftRemove, rightRemove, 0, 0, 0);
        return new ArrayList<>(ans);
    }

    private static void backtrack(String s, Set<String> ans, StringBuilder sb, int leftRemove, int rightRemove, int leftCount, int rightCount, int pos) {
        // 使用 leftCount 和 rightCount 的目的是为了剪枝 --> 只有当 leftCount > rightCount 时，才会添加右括号
        if (pos == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                ans.add(new String(sb));
            }
            return;
        }
        char c = s.charAt(pos);
        if (c == '(' && leftRemove > 0) {
            backtrack(s, ans, sb, leftRemove - 1, rightRemove, leftCount, rightCount, pos + 1);
        }
        if (c == ')' && rightRemove > 0) {
            backtrack(s, ans, sb, leftRemove, rightRemove - 1, leftCount, rightCount, pos + 1);
        }
        sb.append(c);
        if (c != '(' && c != ')') {
            backtrack(s, ans, sb, leftRemove, rightRemove, leftCount, rightCount, pos + 1);
        } else if (c == '(') {
            backtrack(s, ans, sb, leftRemove, rightRemove, leftCount + 1, rightCount, pos + 1);
        } else if (leftCount > rightCount) {
            // 剪枝，只有合法情况才会添加右括号
            backtrack(s, ans, sb, leftRemove, rightRemove, leftCount, rightCount + 1, pos + 1);
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
