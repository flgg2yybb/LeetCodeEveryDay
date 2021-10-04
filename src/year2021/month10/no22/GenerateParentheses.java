package year2021.month10.no22;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 1;
        System.out.println(generateParenthesis(n1));
        System.out.println(generateParenthesis(n2));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(n, 0, 0, sb, ans);
        return ans;
    }

    private static void backtrack(int n, int leftUsed, int rightUsed, StringBuilder sb, List<String> ans) {
        if (leftUsed == n && rightUsed == n) {
            ans.add(new String(sb));
            return;
        }
        if (leftUsed < n) {
            sb.append('(');
            backtrack(n, leftUsed + 1, rightUsed, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rightUsed < leftUsed) {
            sb.append(')');
            backtrack(n, leftUsed, rightUsed + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

/*
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

有效括号组合需满足：左括号必须以正确的顺序闭合。

 

示例 1：

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：

输入：n = 1
输出：["()"]
 

提示：

1 <= n <= 8

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/generate-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
